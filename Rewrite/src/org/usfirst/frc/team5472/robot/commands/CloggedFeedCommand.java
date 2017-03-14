package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class CloggedFeedCommand extends Command {

	private boolean finished = false;

	public CloggedFeedCommand(Joystick j) {
		requires(Robot.getInstance().getFeedSubsystem());
	}

	@Override
	public void initialize() {
		// I'm the one who doesn't do enough
	}

	@Override
	public void execute() {
		Robot.getInstance().getFeedSubsystem().reverseFeed();
		finished = true;
	}

	@Override
	public void end() {
		Robot.getInstance().getFeedSubsystem().disableFeeder();
	}

	@Override
	protected boolean isFinished() {
		return finished;
	}

}
