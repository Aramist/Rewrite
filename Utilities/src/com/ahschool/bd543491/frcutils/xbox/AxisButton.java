package com.ahschool.bd543491.frcutils.xbox;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

public class AxisButton extends Button {

	private Joystick joystick;
	private int axis;
	private double threshold;
	private boolean abs;

	public AxisButton(Joystick stick, int axis, double threshold) {
		this(stick, axis, threshold, false);
	}

	public AxisButton(Joystick stick, int axis, double threshold, boolean absoluteValue) {
		super();
		this.joystick = stick;
		this.axis = axis;
		this.threshold = threshold;
		this.abs = absoluteValue;
	}

	@Override
	public boolean get() {
		double d = joystick.getRawAxis(axis);
		d = abs ? Math.abs(d) : d;
		return d >= threshold;
	}
}
