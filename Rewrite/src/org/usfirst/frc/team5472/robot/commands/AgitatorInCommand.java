package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AgitatorInCommand extends Command {

	private boolean finished;

	public AgitatorInCommand() {
		finished = false;
	}

	@Override
	public void execute() {
		Robot.getInstance().getFeedSubsystem().enableAgitator();
		finished = true;
	}

	@Override
	public void end() {
		Robot.getInstance().getFeedSubsystem().disableAgitator();
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