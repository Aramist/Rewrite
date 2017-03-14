package org.usfirst.frc.team5472.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AgitatorWaveCommand extends CommandGroup {

	public AgitatorWaveCommand() {
		addSequential(new AgitatorInCommand(), 4.0);
		Timer.delay(0.2);
		addSequential(new AgitatorOutCommand(), 1.5);
		Timer.delay(0.2);
	}

}
