package org.usfirst.frc.team5472.robot.autonomous;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class DriveDistance extends Command {

	private boolean finished;
	private double distance;

	public DriveDistance(double d) {
		requires(Robot.getInstance().getDriveSubsystem());
		finished = false;
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
		Robot.getInstance().getDriveSubsystem().driveStraight(0.3 * Math.signum(distance));
		while (Math.abs(Robot.getInstance().getDriveSubsystem().getLeftEncoderDistance()) < Math.abs(distance)) {
			Robot.getInstance().getLogger().log("DriveDistance:\n\tDistance: " + Robot.getInstance().getDriveSubsystem().getLeftEncoderDistance());
			if (!Robot.getInstance().isAutonomous())
				break;
			Timer.delay(0.001);
		}
		finished = true;

	}

	@Override
	public void end() {
		Robot.getInstance().getDriveSubsystem().stop();
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
