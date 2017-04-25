package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class DriveShiftCommand extends Command {

	private boolean finished;

	public DriveShiftCommand() {
		finished = false;
	}

	@Override
	public void execute() {
		Robot.getInstance().getDriveSubsystem().shift();
		Robot.getInstance().getDriveSubsystem().manualDrive(0, 0);
		Timer.delay(0.25);
		finished = true;
	}

	@Override
	protected boolean isFinished() {
		return finished;
	}

}
