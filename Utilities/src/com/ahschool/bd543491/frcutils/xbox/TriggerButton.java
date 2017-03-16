package com.ahschool.bd543491.frcutils.xbox;

import edu.wpi.first.wpilibj.Joystick;

public class TriggerButton extends AxisButton {

	public TriggerButton(Joystick stick, int axis) {
		super(stick, axis, 0.8);
	}

	// That's all folks
}
