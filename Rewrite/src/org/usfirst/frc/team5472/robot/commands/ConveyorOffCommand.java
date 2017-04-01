package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ConveyorOffCommand extends Command {

	private boolean finished;

	public ConveyorOffCommand() {
		finished = false;
	}

	@Override
	public void execute() {
		Robot.getInstance().getFeedSubsystem().disableConveyor();
		Robot.getInstance().getFeedSubsystem().disableAgitator();
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
	protected boolean isFinished() {
		return finished;
	}

}
