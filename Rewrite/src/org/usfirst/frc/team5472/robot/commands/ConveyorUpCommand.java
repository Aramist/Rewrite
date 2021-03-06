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
		Robot.getInstance().getFeedSubsystem().enableAgitator();
		finished = true;
	}

	@Override
	public void end() {
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
