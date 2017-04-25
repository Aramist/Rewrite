package org.usfirst.frc.team5472.robot.autonomous;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class DriveDistance extends Command {

	private boolean finished;
	private double distance;

	public DriveDistance(double d) {
		requires(Robot.getInstance().getDriveSubsystem());
		distance = d;
	}

	@Override
	public void initialize() {
		Robot.getInstance().getDriveSubsystem().resetEncoders();
		Robot.getInstance().getDriveSubsystem().getNavx().reset();
		Robot.getInstance().getLogger().log("Starting DriveDistance Command with distance " + distance);
	}

	@Override
	public void execute() {
		double val = Robot.getInstance().getDriveSubsystem().getLeftEncoderCount() - Robot.getInstance().getDriveSubsystem().getRightEncoderCount();
		while ((Robot.getInstance().isAutonomous()) && (Math.abs(Robot.getInstance().getDriveSubsystem().getLeftEncoderDistance()) < Math.abs(distance))) {
			Robot.getInstance().getDriveSubsystem().driveStraight(val, 0.3 * Math.signum(distance));
			Robot.getInstance().getLogger().log("Distance: " + Robot.getInstance().getDriveSubsystem().getLeftEncoderDistance());
			System.out.println("Distance: " + Robot.getInstance().getDriveSubsystem().getLeftEncoderDistance());
		}
		finished = true;
	}

	@Override
	public void end() {
		// double[] vals = Robot.getInstance().getDriveSubsystem().get();
		// Robot.getInstance().getDriveSubsystem().manualDrive(-0.5 * vals[0],
		// -0.5 * vals[1]);
		// Timer.delay(0.05);
		Robot.getInstance().getDriveSubsystem().stop();
		Timer.delay(0.08);
	}

	@Override
	public void interrupted() {
		Robot.getInstance().getLogger().log("DriveDistance Command interrupted");
		end();
	}

	@Override
	protected boolean isFinished() {
		return finished;
	}

}

// package org.usfirst.frc.team5472.robot.autonomous;
//
// import org.usfirst.frc.team5472.robot.Robot;
//
// import edu.wpi.first.wpilibj.Timer;
// import edu.wpi.first.wpilibj.command.Command;
//
// public class DriveDistance extends Command {
//
// private double distance;
//
// public DriveDistance(double d) {
// requires(Robot.getInstance().getDriveSubsystem());
// distance = d;
// }
//
// @Override
// public void initialize() {
// Robot.getInstance().getDriveSubsystem().resetEncoders();
// Robot.getInstance().getDriveSubsystem().getNavx().reset();
// Robot.getInstance().getLogger().log("Starting DriveDistance Command with
// distance " + distance);
// }
//
// @Override
// public void execute() {
// // double val =
// // Robot.getInstance().getDriveSubsystem().getLeftEncoderCount() -
// // Robot.getInstance().getDriveSubsystem().getRightEncoderCount();
// Robot.getInstance().getDriveSubsystem().driveStraight(0.3 *
// Math.signum(distance));
// while (!isFinished())
// Timer.delay(0.01);
// }
//
// @Override
// public void end() {
// Robot.getInstance().getDriveSubsystem().stop();
// }
//
// @Override
// public void interrupted() {
// Robot.getInstance().getLogger().log("DriveDistance Command interrupted");
// end();
// }
//
// @Override
// protected boolean isFinished() {
// return (!Robot.getInstance().isAutonomous()) ||
// (Math.abs(Robot.getInstance().getDriveSubsystem().getLeftEncoderDistance()) <
// Math.abs(distance));
// }
//
// }
