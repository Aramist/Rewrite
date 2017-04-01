package org.usfirst.frc.team5472.robot.autonomous;

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
		Timer.delay(delay);
		finished = true;
	}

	@Override
	protected boolean isFinished() {
		return finished;
	}

}
