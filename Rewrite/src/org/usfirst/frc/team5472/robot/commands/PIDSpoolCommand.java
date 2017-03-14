package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class PIDSpoolCommand extends Command {

	private boolean finished;
	private double rpm;

	public PIDSpoolCommand() {
		finished = false;
		rpm = Robot.getInstance().getConfig().getShooterSpeed();
	}

	@Override
	public void execute() {
		Robot.getInstance().getShootSubsystem().PIDSpool(rpm);
	}

	@Override
	public void end() {
		Robot.getInstance().getShootSubsystem().stopFlywheel();
	}

	@Override
	protected boolean isFinished() {
		return finished;
	}

}
