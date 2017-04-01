package org.usfirst.frc.team5472.robot.autonomous;

import org.usfirst.frc.team5472.robot.commands.ConveyorOffCommand;
import org.usfirst.frc.team5472.robot.commands.ConveyorUpCommand;
import org.usfirst.frc.team5472.robot.commands.PIDSpoolCommand;
import org.usfirst.frc.team5472.robot.commands.PIDSpoolOffCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MidToMid extends CommandGroup {

	public MidToMid(boolean shoot) {
		addSequential(new DriveDistance(1.7526));
		if (shoot) {
			addSequential(new ConveyorUpCommand());
			addSequential(new PIDSpoolCommand());
			addSequential(new DelayCommand(4));
			addSequential(new PIDSpoolOffCommand());
			addSequential(new ConveyorOffCommand());
		}
	}
}
