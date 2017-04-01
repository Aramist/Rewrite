package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class JoystickDriveCommand extends Command {

	private Joystick stick;

	public JoystickDriveCommand(Joystick j) {
		requires(Robot.getInstance().getDriveSubsystem());
		stick = j;
	}

	@Override
	public void initialize() {
		/**
		 * I don't do much - you do a lot, don't say that - "I" refers to the
		 * method, it doesn't do much
		 */
	}

	@Override
	public void execute() {
		double throttle = stick.getY();
		double twist = stick.getTwist();

		boolean expo = Robot.getInstance().getConfig().useExponentialControls();

		throttle = expo ? applyExponentialTransform(throttle) : throttle;
		twist = expo ? applyExponentialTransform(twist) : twist;

		Robot.getInstance().getDriveSubsystem().manualDrive(throttle - twist, throttle + twist);
	}

	private double applyExponentialTransform(double d) {
		return Math.signum(d) * (Math.exp(d * d) - 1) / (Math.E - 1.0);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
