package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ConveyorDownCommand extends Command {

	private boolean finished;

	public ConveyorDownCommand() {
		finished = false;
	}

	@Override
	public void execute() {
		Robot.getInstance().getFeedSubsystem().enableReverseConveyor();
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
	protected boolean isFinished() {
		return finished;
	}

}
