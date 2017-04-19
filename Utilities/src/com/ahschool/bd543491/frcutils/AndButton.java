package com.ahschool.bd543491.frcutils;

import edu.wpi.first.wpilibj.buttons.Button;

public class AndButton extends Button {

	private Button one;
	private Button two;

	public AndButton(Button b1, Button b2) {
		this.one = b1;
		this.two = b2;
	}

	@Override
	public boolean get() {
		return one.get() && two.get();
	}
}
