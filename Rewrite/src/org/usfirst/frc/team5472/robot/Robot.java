
package org.usfirst.frc.team5472.robot;

import org.usfirst.frc.team5472.robot.autonomous.Autonomous;
import org.usfirst.frc.team5472.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team5472.robot.subsystems.FeedSubsystem;
import org.usfirst.frc.team5472.robot.subsystems.LiftSubsystem;
import org.usfirst.frc.team5472.robot.subsystems.ShootSubsystem;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	private static Robot instance;
	private Logger logger;
	private Config configuration;
	private Controls controls;

	private DriveSubsystem drive;
	private ShootSubsystem shoot;
	private LiftSubsystem lift;
	private FeedSubsystem feed;

	private AnalogInput pressure;

	private Autonomous auto;

	/**
	 * This method is called once when the code is loaded by the Java VM
	 */
	@Override
	public void robotInit() {
		instance = this;
		logger = new Logger();
		configuration = new Config();

		drive = new DriveSubsystem();
		shoot = new ShootSubsystem();
		lift = new LiftSubsystem();
		feed = new FeedSubsystem();

		controls = new Controls();

		auto = new Autonomous();

		pressure = new AnalogInput(0);

		CameraServer.getInstance().startAutomaticCapture();

		logger.log("Robot Initialized");
	}

	/**
	 * This method is called once when the robot is disabled
	 */
	@Override
	public void disabledInit() {
		auto.initTeleop();
		logger.disable();
	}

	/**
	 * This method is called periodically while the robot is disabled
	 */
	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Left Encoder", getDriveSubsystem().getLeftEncoderCount());
		SmartDashboard.putNumber("Right Encoder", getDriveSubsystem().getRightEncoderCount());
		SmartDashboard.putNumber("Pressure", calculatePressureValue());
	}

	/**
	 * This method is called once when the robot enters autonomous mode
	 */
	@Override
	public void autonomousInit() {
		if (logger.isDisabled())
			logger = new Logger();
		logger.log("Autonomous Program Started");
		getDriveSubsystem().resetEncoders();
		auto.initAuto();
	}

	/**
	 * This method is called periodically while the robot is enabled and in
	 * autonomous mode
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Left Encoder", getDriveSubsystem().getLeftEncoderCount());
		SmartDashboard.putNumber("Right Encoder", getDriveSubsystem().getRightEncoderCount());
		SmartDashboard.putNumber("Pressure", calculatePressureValue());
	}

	/**
	 * This method is called once at the initiation of the teleoperated period
	 */
	@Override
	public void teleopInit() {
		if (logger.isDisabled())
			logger = new Logger();

		auto.initTeleop();

		logger.log("Teleop Program Started");
	}

	/**
	 * This method is called periodically during the teleoperated period
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Left Encoder", getDriveSubsystem().getLeftEncoderCount());
		SmartDashboard.putNumber("Right Encoder", getDriveSubsystem().getRightEncoderCount());
		SmartDashboard.putNumber("Pressure", calculatePressureValue());
		SmartDashboard.putNumber("Lift Motor 1 Current", getLiftSubsystem().getLiftCurrentA());
		SmartDashboard.putNumber("Lift Motor 2 Current", getLiftSubsystem().getLiftCurrentB());
	}

	@Override
	public void testInit() {
		if (logger.isDisabled())
			logger = new Logger();
	}

	/**
	 * This method is called periodically while the robot is enabled and in TEST
	 * mode
	 */
	@Override
	public void testPeriodic() {
		logger.log("Drive: ", false);
		logger.log(drive.get());
	}

	public Logger getLogger() {
		return logger;
	}

	public Config getConfig() {
		return configuration;
	}

	public Controls getControls() {
		return controls;
	}

	public DriveSubsystem getDriveSubsystem() {
		return drive;
	}

	public ShootSubsystem getShootSubsystem() {
		return shoot;
	}

	public LiftSubsystem getLiftSubsystem() {
		return lift;
	}

	public FeedSubsystem getFeedSubsystem() {
		return feed;
	}

	public static Robot getInstance() {
		return instance;
	}

	public double calculatePressureValue() {
		return (250 * (pressure.getVoltage() / 4.95)) - 29;
	}

}
