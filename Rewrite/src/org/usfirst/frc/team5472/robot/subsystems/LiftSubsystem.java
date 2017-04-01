package org.usfirst.frc.team5472.robot.subsystems;

import org.usfirst.frc.team5472.robot.Map;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LiftSubsystem extends Subsystem {
	private CANTalon liftMotor;
	private Solenoid liftSolenoid0;

	private double lift = 1.0;

	public LiftSubsystem() {
		super("Lift");

		liftMotor = new CANTalon(Map.liftCAN);
		liftSolenoid0 = new Solenoid(Map.liftSolenoid);

		new Thread(() -> {
			double current;
			while (DriverStation.getInstance().isEnabled()) {
				current = liftMotor.getOutputCurrent();
				SmartDashboard.putNumber("Lift Current", current);

				try {
					this.wait(200L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				if (liftSolenoid0.get())
					SmartDashboard.putString("LiftSolenoid", "LOCKED");
				else
					SmartDashboard.putString("LiftSolenoid", "UNWIND");// check

				SmartDashboard.putNumber("Lift Motor Current", current);

				if (current > 20)
					SmartDashboard.putString("LiftMonitor", "CURRENT TOO HIGH");
				else
					SmartDashboard.putString("LiftMonitor", "EVERYTHING IS OKAY");
			}
		}).start();
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(null);

	}

	public void stop() {
		liftSolenoid0.set(true);// make sure it's locked and can't unwind
		liftMotor.set(0.0);

	}

	public void upLift() {
		if (!liftSolenoid0.get()) {
			liftSolenoid0.set(true);
			Timer.delay(0.1);
		}
		liftMotor.set(lift);
	}

	public void downLift() {
		if (liftSolenoid0.get()) {
			liftSolenoid0.set(false);
			Timer.delay(0.1);
		}
		liftMotor.set(-1 * lift);
	}

	public void shift() {
		liftSolenoid0.set(!liftSolenoid0.get());
	}
}
