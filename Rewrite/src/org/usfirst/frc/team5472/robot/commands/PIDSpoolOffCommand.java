package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class PIDSpoolOffCommand extends Command {

	private boolean finished;

	public PIDSpoolOffCommand() {
		finished = false;
	}

	@Override
	public void execute() {
		Robot.getInstance().getShootSubsystem().stopFlywheel();
		finished = true;
	}

	@Override
	public void end() {
	}

	@Override
	protected boolean isFinished() {
		return finished;
	}

}
