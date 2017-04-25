package org.usfirst.frc.team5472.robot.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
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
	private SendableChooser<Integer> shooting = new SendableChooser<>();
	private Command autoCommand = null;

	public Autonomous() {
		autoEnabled.addDefault("Run autonomous", new Boolean(true));
		autoEnabled.addObject("Do nothing", new Boolean(false));

		location.addDefault("Middle", Location.MIDDLE);
		location.addObject("Left", Location.LEFT);
		location.addObject("Right", Location.RIGHT);

		shooting.addDefault("Just Gear", new Integer(0));
		shooting.addObject("Gear and Shoot", new Integer(1));
		shooting.addObject("Just hopper and shooting", new Integer(2));
		shooting.addObject("Just sit there and shoot", new Integer(-1));

		SmartDashboard.putData("AutonomousEnabled", autoEnabled);
		SmartDashboard.putData("Position", location);
		SmartDashboard.putData("Shooting", shooting);
	}

	public void initAuto() {
		boolean enabled = autoEnabled.getSelected().booleanValue();
		Alliance all = DriverStation.getInstance().getAlliance();
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
				autoCommand = new MidToMid(shooting.getSelected().intValue());
			else
				autoCommand = new SideToSide(all, pos, shooting.getSelected().intValue());
		}
		if (autoCommand != null)
			autoCommand.start();
	}

	public void initTeleop() {
		if (autoCommand != null)
			autoCommand.cancel();
	}
}
