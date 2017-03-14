package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LiftDownCommand extends Command {

	private boolean finished = false;

	public LiftDownCommand() {
		requires(Robot.getInstance().getLiftSubsystem());
	}

	@Override
	public void initialize() {
		// I'm the one who doesn't do enough
	}

	@Override
	public void execute() {
		Robot.getInstance().getLiftSubsystem().downLift();
		finished = true;
	}

	@Override
	public void end() {
		Robot.getInstance().getLiftSubsystem().stop();
	}

	@Override
	protected boolean isFinished() {
		return finished;
	}

}