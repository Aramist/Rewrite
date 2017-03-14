package org.usfirst.frc.team5472.robot;

import com.ahschool.bd543491.frcutils.POVButton.POVButtonType;
import com.ahschool.bd543491.frcutils.xbox.XBOXController.XBOXButton;

public class Map {

	// Motor IDs
	public static final int frontLeftPWM = 2;
	public static final int frontRightPWM = 0;
	public static final int backLeftPWM = 3;
	public static final int backRightPWM = 1;

	public static final int agitatorPWM = 7;
	public static final int conveyorPWM = 4;
	public static final int feederPWM = 5;

	// These are CAN bus IDs
	public static final int liftCAN = 4;
	public static final int shootCAN = 2;
	public static final int susanCAN = 5;

	public static final int encoderDIOA = 0;
	public static final int encoderDIOB = 1;

	// Pneumatics IDs
	public static final int shiftGearSolenoidL = 4;
	public static final int shiftGearSolenoidR = 5;
	public static final int liftSolenoid = 2;

	// Controls
	public static final int feedButton = 1;
	public static final int feedReverseButton = 5;
	public static final int liftButton = 6;
	public static final int liftReverseButton = 4;
	public static final int shiftLiftSolenoidButton = 3;
	public static final int shiftDriveSolenoidButton = 2;

	public static final XBOXButton shootButton = XBOXButton.LEFT_BUMPER;
	public static final XBOXButton conveyorButton = XBOXButton.A;
	public static final XBOXButton conveyorReverseButton = XBOXButton.B;
	public static final POVButtonType susanLeftButton = POVButtonType.EAST;
	public static final POVButtonType susanRightButton = POVButtonType.WEST;

	// Constants
	public static final double agitatorSpeed = 1.0;
	public static final double conveyorSpeed = 0.6;
	public static final double susanSpeed = 0.1;

	public static final double shooterRPM = 3165;

	// PID
	public static final double shooterP = 0.2000;
	public static final double shooterI = 0.0000;
	public static final double shooterD = 0.4000;
	public static final double shooterF = 0.0265;
	public static final int shooterIZone = 40;

	public static final double driveAngleP = 0.0500;
	public static final double driveAngleI = 0.1500;
	public static final double driveAngleD = 0.3000;

	public static final double driveVelocityP = 0.1500;
	public static final double driveVelocityI = 0.0000;
	public static final double driveVelocityD = 0.3000;

	public static final double driveStraightP = 0.1500;
	public static final double driveStraightI = 0.0000;
	public static final double driveStraightD = 0.3000;
}
