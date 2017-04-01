package org.usfirst.frc.team5472.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MidToMid extends CommandGroup {

	public MidToMid() {
		addSequential(new DriveDistance(1.7526));
	}
}
