package com.ahschool.bd543491.frcutils;

import edu.wpi.first.wpilibj.buttons.Button;

public class OrButton extends Button {

	private Button one;
	private Button two;

	public OrButton(Button b1, Button b2) {
		this.one = b1;
		this.two = b2;
	}

	@Override
	public boolean get() {
		return one.get() || two.get();
	}
}
