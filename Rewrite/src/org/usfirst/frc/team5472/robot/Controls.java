package org.usfirst.frc.team5472.robot;

import org.usfirst.frc.team5472.robot.commands.FeedCommand;

import com.ahschool.bd543491.frcutils.POVButton;
import com.ahschool.bd543491.frcutils.xbox.XBOXController;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class Controls {

	private Joystick stick;
	private XBOXController xbox;

	@SuppressWarnings("unused")
	public Controls() {
		stick = new Joystick(0);
		xbox = new XBOXController(1);

		Button feed = new JoystickButton(stick, Map.feedButton);
		Button reverseFeed = new JoystickButton(stick, Map.feedReverseButton);
		Button lift = new JoystickButton(stick, Map.liftButton);
		Button reverseLift = new JoystickButton(stick, Map.liftReverseButton);
		Button shiftLift = new JoystickButton(stick, Map.shiftLiftSolenoidButton);
		Button shiftGear = new JoystickButton(stick, Map.shiftDriveSolenoidButton);

		Button susanLeft = new POVButton(xbox, Map.susanLeftButton);
		Button susanRight = new POVButton(xbox, Map.susanRightButton);

		feed.whileActive(new FeedCommand());
	}

	public Joystick getJoystick() {
		return stick;
	}

	public XBOXController getXBOX() {
		return xbox;
	}

}
