package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SusanLeftCommand extends Command {

	private boolean finished = false;

	public SusanLeftCommand() {
	}

	@Override
	public void initialize() {
		// I'm the one who doesn't do enough
	}

	@Override
	public void execute() {
		Robot.getInstance().getShootSubsystem().setSusanPosition(Robot.getInstance().getShootSubsystem().getSusanPosition() + 0.05);
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
