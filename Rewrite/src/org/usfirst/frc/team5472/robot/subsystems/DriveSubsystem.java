package org.usfirst.frc.team5472.robot.subsystems;

import org.usfirst.frc.team5472.robot.Map;
import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.commands.JoystickDriveCommand;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Trajectory.FitMethod;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

public class DriveSubsystem extends Subsystem {

	private VictorSP frontLeft, frontRight, backLeft, backRight;
	private AHRS navx;
	private PIDOutput angleOutput, velocityOutput;
	private PIDSource velocitySource, driveStraightSource;
	private PIDController anglePID, drivePID, velocityPID;
	private Encoder leftEnc;
	private Encoder rightEnc;

	private Solenoid left;
	private Solenoid right;

	public DriveSubsystem() {
		super("Drive");

		navx = new AHRS(Port.kMXP);

		frontLeft = new VictorSP(Map.frontLeftPWM);
		frontRight = new VictorSP(Map.frontRightPWM);
		backLeft = new VictorSP(Map.backLeftPWM);
		backRight = new VictorSP(Map.backRightPWM);

		leftEnc = new Encoder(Map.leftEncoderDIOA, Map.leftEncoderDIOB);
		rightEnc = new Encoder(Map.rightEncoderDIOA, Map.rightEncoderDIOB);

		leftEnc.setReverseDirection(true);
		rightEnc.setReverseDirection(false);

		leftEnc.setDistancePerPulse(0.00017733);
		rightEnc.setDistancePerPulse(0.00017733);

		left = new Solenoid(Map.shiftGearSolenoidL);
		right = new Solenoid(Map.shiftGearSolenoidR);

		frontLeft.setInverted(true);
		backLeft.setInverted(true);
		frontRight.setInverted(false);
		backRight.setInverted(false);

		angleOutput = (double d) -> {
			double[] motorValues = get();
			manualDrive(motorValues[0] - d, motorValues[1] + d);
		};

		velocityOutput = (double d) -> {
			manualDrive(d, d);
		};

		velocitySource = new PIDSource() {

			private PIDSourceType type = null;

			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {
				type = pidSource;
			}

			@Override
			public PIDSourceType getPIDSourceType() {
				return type;
			}

			@Override
			public double pidGet() {
				double x = navx.getVelocityX(), y = navx.getVelocityY(), z = navx.getVelocityZ();
				return Math.sqrt(x * x + y * y + z * z);
			}
		};

		driveStraightSource = new PIDSource() {
			private PIDSourceType stype = PIDSourceType.kDisplacement;

			@Override
			public void setPIDSourceType(PIDSourceType type) {
				stype = type;
			}

			@Override
			public PIDSourceType getPIDSourceType() {
				return stype;
			}

			@Override
			public double pidGet() {
				return leftEnc.get() - rightEnc.get();
			}
		};

		anglePID = new PIDController(Map.driveAngleP, Map.driveAngleI, Map.driveAngleD, navx, angleOutput);
		drivePID = new PIDController(Map.driveStraightP, Map.driveStraightI, Map.driveStraightD, driveStraightSource, angleOutput);
		velocityPID = new PIDController(Map.driveVelocityP, Map.driveVelocityI, Map.driveVelocityD, velocitySource, velocityOutput);

		anglePID.setInputRange(-180, 180);
		anglePID.setOutputRange(-0.5, 0.5);
		anglePID.setContinuous(true);
		anglePID.setAbsoluteTolerance(2);

		drivePID.setInputRange(-1800, 1800);
		drivePID.setOutputRange(-0.25, 0.25);
		drivePID.setContinuous(false);
		drivePID.setAbsoluteTolerance(40);

		velocityPID.setInputRange(0, 20);
		velocityPID.setOutputRange(0, 1.0);
		velocityPID.setContinuous(false);
		velocityPID.setAbsoluteTolerance(0.1);

		left.set(false);
		right.set(false);
	}

	public void manualDrive(double left, double right) {
		frontLeft.set(left);
		frontRight.set(right);
		backLeft.set(left);
		backRight.set(right);
	}

	public double[] get() {
		return new double[] { frontLeft.get(), frontRight.get(), backLeft.get(), backRight.get() };
	}

	public void driveWithHeading(double throttle, double heading) {

	}

	public void driveStraight(double throttle) {
		double left = throttle, right = throttle;
		double error = leftEnc.get() - rightEnc.get();
		left -= error * 0.01;
		right += error * 0.01;
		manualDrive(left, right);
	}

	public void turnToHeading(double heading) {
		while (Math.abs(heading - navx.getAngle()) > 1) {
			double left = 0, right = 0;
			double error = heading - navx.getAngle();
			if (error > 180)
				error = 360 - error;
			left -= error / 40.0;
			right += error / 40.0;
			manualDrive(left, right);
			if (!Robot.getInstance().isAutonomous())
				break;
			Timer.delay(0.01);
		}
	}

	public void turnToHeading(double heading, boolean autoStop) {
		stop();
		getNavx().reset();
		anglePID.setSetpoint(heading);
		anglePID.enable();
		if (autoStop)
			while (!anglePID.onTarget())
				Timer.delay(0.005);
	}

	public boolean doneTurning() {
		return anglePID.isEnabled() && anglePID.onTarget();
	}

	public void driveWithVelocity(double velocity) {
		stop();
		velocityPID.setSetpoint(velocity);
		velocityPID.enable();
	}

	public void stop() {
		manualDrive(0.0, 0.0);
		anglePID.disable();
		drivePID.disable();
		velocityPID.disable();
	}

	public AHRS getNavx() {
		return navx;
	}

	public int getLeftEncoderCount() {
		return leftEnc.get();
	}

	public double getLeftEncoderDistance() {
		return leftEnc.getDistance();
	}

	public int getRightEncoderCount() {
		return rightEnc.get();
	}

	public double getRightEncoderDistance() {
		return rightEnc.getDistance();
	}

	public void resetEncoders() {
		leftEnc.reset();
		rightEnc.reset();
	}

	public void shift() {
		if (left.get()) {
			left.set(false);
			right.set(false);
		} else {
			left.set(true);
			right.set(true);
		}
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new JoystickDriveCommand(Robot.getInstance().getControls().getJoystick()));
	}

	public void followTrajectory(Waypoint[] points) {
		double delta = 0.05;

		resetEncoders();
		getNavx().reset();

		Trajectory.Config config = new Trajectory.Config(FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 1, 2, 30);
		Trajectory t = Pathfinder.generate(points, config);
		Robot.getInstance().getLogger().saveTrajectory(t);
		TankModifier mod = new TankModifier(t).modify(0.6);
		Trajectory left = mod.getLeftTrajectory();
		Trajectory right = mod.getRightTrajectory();

		EncoderFollower leftFollower = new EncoderFollower(left);
		EncoderFollower rightFollower = new EncoderFollower(right);

		leftFollower.configurePIDVA(1.0, 0, 0.1, 1.82882, 0);
		rightFollower.configurePIDVA(1.0, 0, 0.1, 1.82882, 0);

		leftFollower.configureEncoder(0, 1800, 0.1016);
		rightFollower.configureEncoder(0, 1800, 0.1016);

		while (!leftFollower.isFinished() && !rightFollower.isFinished()) {
			double nextLeft = leftFollower.calculate(getLeftEncoderCount());
			double nextRight = rightFollower.calculate(getRightEncoderCount());

			double angle = getNavx().getAngle();
			double target = Pathfinder.r2d(leftFollower.getHeading());

			double diff = Pathfinder.boundHalfDegrees(target - angle);

			double turn = 0.8 * (-1.0 / 80.0) * diff;

			manualDrive(nextLeft + turn, nextRight - turn);

			Timer.delay(delta);
		}
		stop();
	}
}
