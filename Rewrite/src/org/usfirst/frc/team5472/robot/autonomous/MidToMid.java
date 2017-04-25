package org.usfirst.frc.team5472.robot.autonomous;

import org.usfirst.frc.team5472.robot.commands.ConveyorOffCommand;
import org.usfirst.frc.team5472.robot.commands.ConveyorUpCommand;
import org.usfirst.frc.team5472.robot.commands.PIDSpoolCommand;
import org.usfirst.frc.team5472.robot.commands.PIDSpoolOffCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MidToMid extends CommandGroup {

	public MidToMid(int shoot) {
		addSequential(new DriveDistance(1.7526));
		addSequential(new DelayCommand(3.0));
		addSequential(new DriveDistance(-0.18));
		addSequential(new DelayCommand(0.2));
		addSequential(new DriveDistance(0.18));
		if (shoot == 1) {
			addSequential(new ConveyorUpCommand());
			addSequential(new PIDSpoolCommand());
			addSequential(new DelayCommand(4));
			addSequential(new PIDSpoolOffCommand());
			addSequential(new ConveyorOffCommand());
		}
	}
}
