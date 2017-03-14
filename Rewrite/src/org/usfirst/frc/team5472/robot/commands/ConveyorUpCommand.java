package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ConveyorUpCommand extends Command {

	private boolean finished;

	public ConveyorUpCommand() {
		finished = false;
	}

	@Override
	public void execute() {
		Robot.getInstance().getFeedSubsystem().enableConveyor();
		finished = true;
	}

	@Override
	public void end() {
		Robot.getInstance().getFeedSubsystem().disableConveyor();
	}

	@Override
	public void interrupted() {
		end();
	}

	@Override
	public boolean isFinished() {
		return finished;
	}
}
