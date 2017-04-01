package org.usfirst.frc.team5472.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Config {

	private boolean useExpo;
	private double shooterRPM;
	private double agitatorSpeed;
	private double conveyorSpeed;
	private double feederSpeed;
	private double susanSpeed;

	public Config() {
		updateValues();
	}

	public void updateValues() {
		useExpo = SmartDashboard.getBoolean("Use Exponential", false);
		shooterRPM = SmartDashboard.getNumber("Shooter Speed (rpm)", 3165.0);
		agitatorSpeed = SmartDashboard.getNumber("Agitator Speed", 1.0);
		conveyorSpeed = SmartDashboard.getNumber("Conveyor Speed", 0.5);
		feederSpeed = SmartDashboard.getNumber("Feeder Speed", 1.0);
		susanSpeed = SmartDashboard.getNumber("Susan Speed", 0.1);
	}

	public boolean useExponentialControls() {
		return useExpo;
	}

	public double getShooterSpeed() {
		return shooterRPM;
	}

	public double getAgitatorSpeed() {
		return agitatorSpeed;
	}

	public double getFeederSpeed() {
		return feederSpeed;
	}

	public double getConveyorSpeed() {
		return conveyorSpeed;
	}

	public double getSusanSpeed() {
		return susanSpeed;
	}
}
