package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LiftShiftCommand extends Command {

	private boolean finished = false;

	public LiftShiftCommand() {
		requires(Robot.getInstance().getLiftSubsystem());
	}

	@Override
	public void initialize() {
	}

	@Override
	public void execute() {
		Robot.getInstance().getLiftSubsystem().shift();
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