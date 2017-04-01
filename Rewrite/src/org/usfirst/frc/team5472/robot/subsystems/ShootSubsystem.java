package org.usfirst.frc.team5472.robot.subsystems;

import org.usfirst.frc.team5472.robot.Map;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class ShootSubsystem extends Subsystem {

	private CANTalon shoot;
	private CANTalon susan;

	public ShootSubsystem() {
		super("Shoot");
		shoot = new CANTalon(Map.shootCAN);
		susan = new CANTalon(Map.susanCAN);

		shoot.setInverted(false);
		susan.setInverted(false);

		shoot.enableBrakeMode(true);
		shoot.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		shoot.changeControlMode(TalonControlMode.Speed);
		shoot.reverseOutput(true);
		shoot.reverseSensor(true);
		shoot.configPeakOutputVoltage(3, -12);
		shoot.setPID(Map.shooterP, Map.shooterI, Map.shooterD, Map.shooterF, Map.shooterIZone, 0.0, 0);

		susan.enableBrakeMode(true);
		susan.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		susan.changeControlMode(TalonControlMode.Position);
		susan.reverseOutput(false);
		susan.reverseSensor(false);
		susan.configPeakOutputVoltage(3, -3);
		susan.setPID(0.5, 0.0, 0.0); // TODO: Configure this
		susan.setEncPosition(0);
	}

	public void PIDSpool(double rpm) {
		shoot.changeControlMode(TalonControlMode.Speed);
		shoot.enable();
		shoot.setSetpoint(rpm);
		shoot.enableBrakeMode(false);
	}

	public void setSusanPosition(double pos) {
		susan.changeControlMode(TalonControlMode.Position);
		susan.enable();
		susan.setSetpoint(pos);
	}

	public double getSusanPosition() {
		return susan.get();
	}

	public void setSusan(double d) {
		susan.changeControlMode(TalonControlMode.PercentVbus);
		susan.set(d);
	}

	public void setShooter(double d) {
		shoot.changeControlMode(TalonControlMode.PercentVbus);
		shoot.set(d);
	}

	public void stopFlywheel() {
		shoot.disable();
		shoot.set(0);
	}

	public void stopSusan() {
		susan.disable();
		susan.set(0);
	}

	public void stop() {
		susan.disable();
		shoot.disable();
		susan.set(0);
		shoot.set(0);
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(null);
	}

}
