package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FeedShooterCommand extends CommandGroup {

	public FeedShooterCommand() {
		requires(Robot.getInstance().getShootSubsystem());
		this.addParallel(new ConveyorUpCommand());
		this.addParallel(new AgitatorWaveCommand());
	}

}
