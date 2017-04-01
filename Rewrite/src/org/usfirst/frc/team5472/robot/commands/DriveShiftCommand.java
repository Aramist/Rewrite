package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveShiftCommand extends Command {

	private boolean finished;

	public DriveShiftCommand() {
		finished = false;
	}

	@Override
	public void execute() {
		Robot.getInstance().getDriveSubsystem().shift();
		finished = true;
	}

	@Override
	protected boolean isFinished() {
		return finished;
	}

}
