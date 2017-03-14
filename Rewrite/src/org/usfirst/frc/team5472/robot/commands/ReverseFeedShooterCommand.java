package org.usfirst.frc.team5472.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ReverseFeedShooterCommand extends CommandGroup {

	public ReverseFeedShooterCommand() {
		addSequential(new ConveyorDownCommand());
		addSequential(new AgitatorOutCommand());
	}

}
