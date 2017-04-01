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
import org.usfirst.frc.team5472.robot.commands.SusanLeftCommand;
import org.usfirst.frc.team5472.robot.commands.SusanRightCommand;

import com.ahschool.bd543491.frcutils.POVButton;
import com.ahschool.bd543491.frcutils.xbox.XBOXController;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class Controls {

	private Joystick stick;
	private XBOXController xbox;

	public Controls() {
		stick = new Joystick(0);
		xbox = new XBOXController(1);

		Button feed = new JoystickButton(stick, Map.feedButton);
		Button reverseFeed = new JoystickButton(stick, Map.feedReverseButton);
		Button lift = new JoystickButton(stick, Map.liftButton);
		Button reverseLift = new JoystickButton(stick, Map.liftReverseButton);
		Button shiftLift = new JoystickButton(stick, Map.shiftLiftSolenoidButton);
		Button shiftGear = new JoystickButton(stick, Map.shiftDriveSolenoidButton);

		Button susanLeft = new POVButton(xbox, Map.susanLeftButton);
		Button susanRight = new POVButton(xbox, Map.susanRightButton);

		Button spool = xbox.getButton(Map.shootButton);

		Button conveyor = xbox.getButton(Map.conveyorButton);
		Button conveyorReverse = xbox.getButton(Map.conveyorReverseButton);

		feed.whenPressed(new FeedCommand());
		reverseFeed.whenPressed(new FeedReverseCommand());
		feed.whenReleased(new FeedOffCommand());
		reverseFeed.whenReleased(new FeedOffCommand());

		lift.whenPressed(new LiftUpCommand());
		lift.whenReleased(new LiftOffCommand());

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

		susanLeft.whileHeld(new SusanLeftCommand());
		susanRight.whileHeld(new SusanRightCommand());

	}

	public Joystick getJoystick() {
		return stick;
	}

	public XBOXController getXBOX() {
		return xbox;
	}

}
