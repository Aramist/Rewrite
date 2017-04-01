package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LiftOffCommand extends Command {

	private boolean finished = false;

	public LiftOffCommand() {
		requires(Robot.getInstance().getLiftSubsystem());
	}

	@Override
	public void initialize() {
		// I'm the one who doesn't do enough
	}

	@Override
	public void execute() {
		Robot.getInstance().getLiftSubsystem().stop();
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