package com.ahschool.bd543491.frcutils;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

public class POVButton extends Button {

	private Joystick joystick;
	private POVButtonType type;

	public POVButton(Joystick j, POVButtonType t) {
		joystick = j;
		type = t;
	}

	@Override
	public boolean get() {
		int angle = joystick.getPOV();

		switch (type) {
			case EAST:
				return angle == POVButtonType.EAST.getAngle();
			case NORTH:
				return angle == POVButtonType.NORTH.getAngle();
			case NORTHEAST:
				return angle == POVButtonType.NORTHEAST.getAngle();
			case NORTHWEST:
				return angle == POVButtonType.NORTHWEST.getAngle();
			case SOUTH:
				return angle == POVButtonType.SOUTH.getAngle();
			case SOUTHEAST:
				return angle == POVButtonType.SOUTHEAST.getAngle();
			case SOUTHWEST:
				return angle == POVButtonType.SOUTHWEST.getAngle();
			case WEST:
				return angle == POVButtonType.WEST.getAngle();
			default:
				return false;
		}
	}

	public enum POVButtonType {
		NORTH(0), NORTHWEST(45), WEST(90), SOUTHWEST(135), SOUTH(180), SOUTHEAST(225), EAST(270), NORTHEAST(315);

		private int angle;

		private POVButtonType(int i) {
			angle = i;
		}

		public int getAngle() {
			return angle;
		}
	}
}
