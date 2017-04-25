package org.usfirst.frc.team5472.robot.autonomous;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class DelayCommand extends Command {

	private boolean finished;
	private double delay;

	public DelayCommand(double d) {
		delay = d;
		finished = false;
	}

	@Override
	public void execute() {
		for (double d = 0; d < delay; d += delay / 1000.0) {
			Timer.delay(delay / 1000.0);
			if (!Robot.getInstance().isAutonomous())
				break;
		}
		finished = true;
	}

	@Override
	protected boolean isFinished() {
		return finished;
	}

}
