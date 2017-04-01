package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class FeedOffCommand extends Command {

	private boolean finished = false;

	public FeedOffCommand() {
	}

	@Override
	public void initialize() {
	}

	@Override
	public void execute() {
		Robot.getInstance().getFeedSubsystem().disableFeeder();
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
