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
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSubsystem extends Subsystem {

	private VictorSP frontLeft, frontRight, backLeft, backRight;
	private AHRS navx;
	private PIDOutput angleOutput, velocityOutput;
	private PIDSource velocitySource;
	private PIDController anglePID, drivePID, velocityPID;
	private Encoder encoder;

	public DriveSubsystem() {
		super("Drive");

		navx = new AHRS(Port.kMXP);

		frontLeft = new VictorSP(Map.frontLeftPWM);
		frontRight = new VictorSP(Map.frontRightPWM);
		backLeft = new VictorSP(Map.backLeftPWM);
		backRight = new VictorSP(Map.backRightPWM);

		encoder = new Encoder(Map.encoderDIOA, Map.encoderDIOB);

		frontLeft.setInverted(true);
		backLeft.setInverted(true);
		frontRight.setInverted(false);
		backRight.setInverted(false);

		angleOutput = (double d) -> {
			double[] motorValues = get();
			manualDrive(motorValues[0] + d, motorValues[1] - d);
		};

		velocityOutput = (double d) -> {
			manualDrive(d, d);
		};

		velocitySource = new PIDSource() {

			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {
			}

			@Override
			public PIDSourceType getPIDSourceType() {
				return null;
			}

			@Override
			public double pidGet() {
				double x = navx.getVelocityX(), y = navx.getVelocityY(), z = navx.getVelocityZ();
				return Math.sqrt(x * x + y * y + z * z);
			}
		};

		anglePID = new PIDController(Map.driveAngleP, Map.driveAngleI, Map.driveAngleD, navx, angleOutput);
		drivePID = new PIDController(Map.driveStraightP, Map.driveStraightI, Map.driveStraightD, navx, angleOutput);
		velocityPID = new PIDController(Map.driveVelocityP, Map.driveVelocityI, Map.driveVelocityD, velocitySource, velocityOutput);

		anglePID.setInputRange(-180, 180);
		anglePID.setOutputRange(-0.5, 0.5);
		anglePID.setContinuous(true);
		anglePID.setAbsoluteTolerance(2);

		drivePID.setInputRange(-180, 180);
		drivePID.setOutputRange(-0.25, 0.25);
		drivePID.setContinuous(true);
		drivePID.setAbsoluteTolerance(2);

		velocityPID.setInputRange(0, 20);
		velocityPID.setOutputRange(0, 1.0);
		velocityPID.setContinuous(false);
		velocityPID.setAbsoluteTolerance(0.1);
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
		anglePID.setSetpoint(heading);
		manualDrive(throttle, throttle);
		anglePID.enable();
	}

	public void turnToHeading(double heading) {
		turnToHeading(heading, false);
	}

	public void turnToHeading(double heading, boolean autoStop) {
		stop();
		anglePID.setSetpoint(heading);
		anglePID.enable();
		if (autoStop)
			while (!anglePID.onTarget())
				Timer.delay(0.005);
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

	public int getEncoderCount() {
		return encoder.get();
	}

	public double getEncoderDistance() {
		return encoder.getDistance();
	}

	public void resetEncoder() {
		encoder.reset();
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new JoystickDriveCommand(Robot.getInstance().getControls().getJoystick()));
	}
}
