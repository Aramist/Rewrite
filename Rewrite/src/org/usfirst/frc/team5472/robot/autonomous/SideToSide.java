package org.usfirst.frc.team5472.robot.autonomous;

import org.usfirst.frc.team5472.robot.commands.ConveyorOffCommand;
import org.usfirst.frc.team5472.robot.commands.ConveyorUpCommand;
import org.usfirst.frc.team5472.robot.commands.PIDSpoolCommand;
import org.usfirst.frc.team5472.robot.commands.PIDSpoolOffCommand;

import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class SideToSide extends CommandGroup {

	private boolean isBlue = false; // Blue: left Red: right
	private boolean isRed = false;
	private int position = 0; // 1: near boiler, 3: far

	public SideToSide(Alliance all, int pos, int shoot) {
		isBlue = all.equals(Alliance.Blue);
		isRed = all.equals(Alliance.Red);
		position = pos;

		if (isBlue && position == 1) {
			// Blue near boiler
			if (shoot == 2) {
				// TODO: get to the hopper
				addSequential(new DriveDistance(3.124));
				addSequential(new Turn(90));
				// TODO: dependant on where robot starts
				addSequential(new DriveDistance(0.863));
				addSequential(new PIDSpoolCommand());
				addSequential(new DelayCommand(0.75));
				addSequential(new ConveyorUpCommand());
				addSequential(new DelayCommand(4));
				addSequential(new PIDSpoolOffCommand());
				addSequential(new ConveyorOffCommand());
			} else {
				addSequential(new DriveDistance(1.860));
				addSequential(new Turn(60));
				addSequential(new DriveDistance(1.3215));
				// TODO: AIM TURRET
				if (shoot == 1) {
					addSequential(new PIDSpoolCommand());
					addSequential(new DelayCommand(0.75));
					addSequential(new ConveyorUpCommand());
					addSequential(new DelayCommand(4));
					addSequential(new PIDSpoolOffCommand());
					addSequential(new ConveyorOffCommand());
				}
			}
		} else if (isBlue && position == 3) {
			// Blue far boiler
			addSequential(new DriveDistance(1.86));
			addSequential(new Turn(-60));
			addSequential(new DriveDistance(1.3215));
		} else if (isRed && position == 1) {
			// Red far boiler
			addSequential(new DriveDistance(1.810));
			addSequential(new Turn(60));
			addSequential(new DriveDistance(1.0215));
		} else if (isRed && position == 3) {
			// Red near boiler
			if (shoot == 2) {
				// TODO: get to the hopper
				addSequential(new DriveDistance(3.124));
				addSequential(new Turn(90));
				// TODO: dependant on where robot starts
				addSequential(new DriveDistance(0.863));
				addSequential(new PIDSpoolCommand());
				addSequential(new DelayCommand(0.75));
				addSequential(new ConveyorUpCommand());
				addSequential(new DelayCommand(4));
				addSequential(new PIDSpoolOffCommand());
				addSequential(new ConveyorOffCommand());
			} else {
				addSequential(new DriveDistance(1.84));
				addSequential(new Turn(-60));
				addSequential(new DriveDistance(1.3215));
				// TODO: AIM TURRET
				if (shoot == 1) {
					addSequential(new PIDSpoolCommand());
					addSequential(new DelayCommand(0.75));
					addSequential(new ConveyorUpCommand());
					addSequential(new DelayCommand(4));
					addSequential(new PIDSpoolOffCommand());
					addSequential(new ConveyorOffCommand());
				}
			}
		} else if (shoot == -1) {
			addSequential(new PIDSpoolCommand());
			addSequential(new DelayCommand(0.75));
			addSequential(new ConveyorUpCommand());
			addSequential(new DelayCommand(4));
			addSequential(new PIDSpoolOffCommand());
			addSequential(new ConveyorOffCommand());
		}
	}
}
