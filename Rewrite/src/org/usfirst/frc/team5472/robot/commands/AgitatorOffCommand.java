package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AgitatorOffCommand extends Command {

	private boolean finished;

	public AgitatorOffCommand() {
		finished = false;
	}

	@Override
	public void execute() {
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
	public boolean isFinished() {
		return finished;
	}
}