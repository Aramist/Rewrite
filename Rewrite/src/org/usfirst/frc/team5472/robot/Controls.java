package org.usfirst.frc.team5472.robot;

import org.usfirst.frc.team5472.robot.commands.ConveyorDownCommand;
import org.usfirst.frc.team5472.robot.commands.ConveyorOffCommand;
import org.usfirst.frc.team5472.robot.commands.ConveyorUpCommand;
import org.usfirst.frc.team5472.robot.commands.DriveShiftCommand;
import org.usfirst.frc.team5472.robot.commands.FeedCommand;
import org.usfirst.frc.team5472.robot.commands.FeedOffCommand;
import org.usfirst.frc.team5472.robot.commands.FeedReverseCommand;
import org.usfirst.frc.team5472.robot.commands.LiftDownCommand;
import org.usfirst.frc.team5472.robot.commands.LiftOffCommand;
import org.usfirst.frc.team5472.robot.commands.LiftShiftCommand;
import org.usfirst.frc.team5472.robot.commands.LiftUpCommand;
import org.usfirst.frc.team5472.robot.commands.PIDSpoolCommand;
import org.usfirst.frc.team5472.robot.commands.PIDSpoolOffCommand;

import com.ahschool.bd543491.frcutils.OrButton;
import com.ahschool.bd543491.frcutils.xbox.TriggerButton;
import com.ahschool.bd543491.frcutils.xbox.XBOXController;
import com.ahschool.bd543491.frcutils.xbox.XBOXController.XBOXButton;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class Controls {

	private Joystick stick;
	private XBOXController xbox;

	public Controls() {
		stick = new Joystick(0);
		xbox = new XBOXController(1);

		Button feed = new OrButton(new JoystickButton(stick, Map.feedButton), xbox.getButton(XBOXButton.RIGHT_BUMPER));
		Button reverseFeed = new OrButton(new JoystickButton(stick, Map.feedReverseButton), new TriggerButton(xbox, 3));
		Button lift = new JoystickButton(stick, Map.liftButton);
		Button lowPowerLift = new JoystickButton(stick, Map.liftLowPowerButton);
		Button reverseLift = new JoystickButton(stick, Map.liftReverseButton);
		Button shiftLift = new JoystickButton(stick, Map.shiftLiftSolenoidButton);
		Button shiftGear = new JoystickButton(stick, Map.shiftDriveSolenoidButton);

		Button spool = xbox.getButton(Map.shootButton);

		Button conveyor = xbox.getButton(Map.conveyorButton);
		Button conveyorReverse = xbox.getButton(Map.conveyorReverseButton);

		feed.whenPressed(new FeedCommand());
		reverseFeed.whenPressed(new FeedReverseCommand());
		feed.whenReleased(new FeedOffCommand());
		reverseFeed.whenReleased(new FeedOffCommand());

		lift.whenPressed(new LiftUpCommand(true));
		lift.whenReleased(new LiftOffCommand());

		lowPowerLift.whenPressed(new LiftUpCommand(false));
		lowPowerLift.whenReleased(new LiftOffCommand());

		reverseLift.whenPressed(new LiftDownCommand());
		reverseLift.whenReleased(new LiftOffCommand());

		shiftLift.whenPressed(new LiftShiftCommand());
		shiftGear.whenPressed(new DriveShiftCommand());
		shiftGear.whenReleased(new DriveShiftCommand());

		spool.whenPressed(new PIDSpoolCommand());
		spool.whenReleased(new PIDSpoolOffCommand());

		conveyor.whenPressed(new ConveyorUpCommand());
		conveyor.whenReleased(new ConveyorOffCommand());

		conveyorReverse.whenPressed(new ConveyorDownCommand());
		conveyorReverse.whenReleased(new ConveyorOffCommand());

	}

	public Joystick getJoystick() {
		return stick;
	}

	public XBOXController getXBOX() {
		return xbox;
	}

}
