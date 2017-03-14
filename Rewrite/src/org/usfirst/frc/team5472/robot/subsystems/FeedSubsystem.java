package org.usfirst.frc.team5472.robot.subsystems;

import org.usfirst.frc.team5472.robot.Map;
//import org.usfirst.frc.team5472.robot.commands.FeedCommand;
import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class FeedSubsystem extends Subsystem {

	private VictorSP feeder;
	private VictorSP agitator;
	private VictorSP conveyor;

	private double feedValue = 0.6;

	public FeedSubsystem() {
		super("Feed");
		this.feeder = new VictorSP(Map.feederPWM);
		this.agitator = new VictorSP(Map.agitatorPWM);
		this.conveyor = new VictorSP(Map.conveyorPWM);
		feeder.setInverted(true);
		agitator.setInverted(false);
		conveyor.setInverted(false);
	}

	public void enableFeeder() {
		feeder.set(feedValue);
	}

	public void disableFeeder() {
		feeder.set(0.0);
	}

	public void reverseFeed() {
		feeder.set(-1 * feedValue);
	}

	public void enableAgitator() {
		agitator.set(Robot.getInstance().getConfig().getAgitatorSpeed());
	}

	public void enableReverseAgitator() {
		agitator.set(-Robot.getInstance().getConfig().getAgitatorSpeed());
	}

	public void disableAgitator() {
		agitator.set(0.0);
	}

	public void enableConveyor() {
		conveyor.set(Robot.getInstance().getConfig().getConveyorSpeed());
	}

	public void enableReverseConveyor() {
		conveyor.set(-Robot.getInstance().getConfig().getConveyorSpeed());
	}

	public void disableConveyor() {
		conveyor.set(0.0);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(null);
	}
}
