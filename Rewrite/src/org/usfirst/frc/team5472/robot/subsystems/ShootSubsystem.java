package org.usfirst.frc.team5472.robot.subsystems;

import org.usfirst.frc.team5472.robot.Map;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class ShootSubsystem extends Subsystem {

	private CANTalon shoot;

	public ShootSubsystem() {
		super("Shoot");
		shoot = new CANTalon(Map.shootCAN);

		shoot.setInverted(false);

		shoot.enableBrakeMode(true);
		shoot.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		shoot.changeControlMode(TalonControlMode.Speed);
		shoot.reverseOutput(true);
		shoot.reverseSensor(true);
		shoot.configPeakOutputVoltage(3, -12);
		shoot.setPID(Map.shooterP, Map.shooterI, Map.shooterD, Map.shooterF, Map.shooterIZone, 0.0, 0);
	}

	public void PIDSpool(double rpm) {
		shoot.changeControlMode(TalonControlMode.Speed);
		shoot.enable();
		shoot.setSetpoint(rpm);
		shoot.enableBrakeMode(false);
	}

	public void setShooter(double d) {
		shoot.changeControlMode(TalonControlMode.PercentVbus);
		shoot.set(d);
	}

	public void stopFlywheel() {
		shoot.disable();
		shoot.set(0);
	}

	public void stop() {
		shoot.disable();
		shoot.set(0);
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(null);
	}

}
