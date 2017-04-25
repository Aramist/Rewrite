package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LiftUpCommand extends Command {

	private boolean finished = false;
	private boolean highPower = false;

	public LiftUpCommand(boolean high) {
		requires(Robot.getInstance().getLiftSubsystem());
		highPower = high;
	}

	@Override
	public void initialize() {
	}

	@Override
	public void execute() {
		if (highPower)
			Robot.getInstance().getLiftSubsystem().upLift();
		else
			Robot.getInstance().getLiftSubsystem().upLiftLowPower();
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
