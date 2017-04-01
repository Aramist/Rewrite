package org.usfirst.frc.team5472.robot.autonomous;

import org.usfirst.frc.team5472.robot.commands.ConveyorOffCommand;
import org.usfirst.frc.team5472.robot.commands.ConveyorUpCommand;
import org.usfirst.frc.team5472.robot.commands.PIDSpoolCommand;
import org.usfirst.frc.team5472.robot.commands.PIDSpoolOffCommand;

import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class SideToSide extends CommandGroup {

	private boolean isBlue = false; // Blue: left Red: right
	private int position = 0; // 1: near boiler, 3: far

	public SideToSide(Alliance all, int pos, boolean shoot) {
		isBlue = all.equals(Alliance.Blue);
		position = pos;

		if (isBlue && position == 1) {
			// Blue near boiler
			addSequential(new DriveDistance(1.96));
			addSequential(new Turn(-51));
			addSequential(new DriveDistance(1.0715));
			// TODO: AIM TURRET
			if (shoot) {
				addSequential(new ConveyorUpCommand());
				addSequential(new PIDSpoolCommand());
				addSequential(new DelayCommand(4));
				addSequential(new PIDSpoolOffCommand());
				addSequential(new ConveyorOffCommand());
			}
		} else if (isBlue && position == 3) {
			// Blue far boiler
			addSequential(new DriveDistance(2.005));
			addSequential(new Turn(51));
			addSequential(new DriveDistance(1.0715));
		} else if (!isBlue && position == 1) {
			// Red near boiler
			addSequential(new DriveDistance(2.005));
			addSequential(new Turn(51));
			addSequential(new DriveDistance(1.0715));
			// TODO: AIM TURRET
			if (shoot) {
				addSequential(new ConveyorUpCommand());
				addSequential(new PIDSpoolCommand());
				addSequential(new DelayCommand(4));
				addSequential(new PIDSpoolOffCommand());
				addSequential(new ConveyorOffCommand());
			}
		} else if (!isBlue && position == 3) {
			// Red far boiler
			addSequential(new DriveDistance(1.96));
			addSequential(new Turn(-51));
			addSequential(new DriveDistance(1.0715));
		} else {
			// Do nothing.
		}
	}
}
