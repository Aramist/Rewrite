package com.ahschool.bd543491.frcutils.xbox;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class XBOXController extends Joystick {

	public XBOXController(int port) {
		super(port);
	}

	public double getLeftX() {
		return getRawAxis(0);
	}

	public double getRightX() {
		return getRawAxis(4);
	}

	public double getLeftY() {
		return getRawAxis(1);
	}

	public double getRightY() {
		return getRawAxis(5);
	}

	public double getRightTrigger() {
		return getRawAxis(3);
	}

	public double getLeftTrigger() {
		return getRawAxis(2);
	}

	public boolean isPressed(XBOXButton b) {
		return getRawButton(b.getID());
	}

	public boolean getRawButton(XBOXButton b) {
		return isPressed(b);
	}

	public JoystickButton getButton(XBOXButton b) {
		return new JoystickButton(this, b.getID());
	}

	public void setRumble(boolean enabled) {
		this.setRumble(RumbleType.kRightRumble, enabled ? 1.0 : 0.0);
	}

	public enum XBOXButton {
		A(1), B(2), X(3), Y(4), START(8), BACK(7), LEFT_BUMPER(5), RIGHT_BUMPER(6), LEFT_STICK(9), RIGHT_STICK(10);

		private int id;

		private XBOXButton(int i) {
			id = i;
		}

		public int getID() {
			return id;
		}
	}
}
