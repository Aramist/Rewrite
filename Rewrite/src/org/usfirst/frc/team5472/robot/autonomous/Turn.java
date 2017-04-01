package org.usfirst.frc.team5472.robot.autonomous;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Turn extends Command {

	private boolean finished;
	private double degrees;

	public Turn(double deg) {
		requires(Robot.getInstance().getDriveSubsystem());
		finished = false;
		degrees = deg;
	}

	@Override
	public void execute() {
		Robot.getInstance().getDriveSubsystem().turnToHeading(degrees);
		finished = true;
	}

	@Override
	public void end() {
		finished = true;
		Robot.getInstance().getDriveSubsystem().stop();
	}

	@Override
	public void interrupted() {
		Robot.getInstance().getLogger().log("Turn Command interrupted");
		end();
	}

	@Override
	public boolean isFinished() {
		return finished;
	}
}
