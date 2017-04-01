package org.usfirst.frc.team5472.robot.autonomous;

import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Autonomous {

	enum Location {
		LEFT, MIDDLE, RIGHT;
	}

	private SendableChooser<Boolean> autoEnabled = new SendableChooser<>();
	private SendableChooser<Location> location = new SendableChooser<>();
	private SendableChooser<Alliance> alliance = new SendableChooser<>();
	private SendableChooser<Boolean> shooting = new SendableChooser<>();
	private Command autoCommand = null;

	public Autonomous() {
		autoEnabled.addDefault("Run autonomous", new Boolean(true));
		autoEnabled.addObject("Do nothing", new Boolean(false));

		location.addDefault("Middle", Location.MIDDLE);
		location.addObject("Left", Location.LEFT);
		location.addObject("Right", Location.RIGHT);

		alliance.addDefault("Blue", Alliance.Blue);
		alliance.addObject("Red", Alliance.Red);

		shooting.addDefault("Not shooting", new Boolean(false));
		shooting.addObject("Shooting", new Boolean(true));

		SmartDashboard.putData("AutonomousEnabled", autoEnabled);
		SmartDashboard.putData("Alliance", alliance);
		SmartDashboard.putData("Position", location);
		SmartDashboard.putData("Shooting", shooting);
	}

	public void initAuto() {
		boolean enabled = autoEnabled.getSelected().booleanValue();
		Alliance all = alliance.getSelected();
		Location loc = location.getSelected();

		int pos = 0;

		switch (loc) {
			case LEFT:
				pos = 1;
				break;
			case MIDDLE:
				pos = 2;
				break;
			case RIGHT:
				pos = 3;
				break;
			default:
				pos = 2;
		}

		if (enabled) {
			if (pos == 0)
				autoCommand = null;
			else if (pos == 2)
				autoCommand = new MidToMid(shooting.getSelected().booleanValue());
			else
				autoCommand = new SideToSide(all, pos, shooting.getSelected().booleanValue());
		}
		if (autoCommand != null)
			autoCommand.start();
	}

	public void initTeleop() {
		if (autoCommand != null)
			autoCommand.cancel();
	}
}
