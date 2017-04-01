package org.usfirst.frc.team5472.robot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;

public class Logger {

	/**
	 * My interpretation of the logging industry:
	 */

	private BufferedWriter bw;
	private FileWriter fw;

	private boolean disabled;

	public Logger() {
		/**
		 * Create a file on which logging will be done
		 */

		disabled = false;

		try {
			String dt = getDateTimeString();
			Paths.get("/home/lvuser/logs").toFile().mkdirs();
			Paths.get("/home/lvuser/logs/log" + dt + ".log").toFile().createNewFile();
			fw = new FileWriter("/home/lvuser/logs/log" + dt + ".log");
			bw = new BufferedWriter(fw);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getDateTimeString() {
		return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
	}

	public void log(String s) {
		log(s, true);
	}

	public void log(String s, boolean linebreak) {
		if (fw == null || bw == null) {
			System.out.println("Buffered and/or FileWriter is/are null, cannot log.");
			return;
		}
		try {
			bw.write(s + (linebreak ? "\n" : ""));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void log(double d) {
		log(String.valueOf(d));
	}

	public void log(int i) {
		log(String.valueOf(i));
	}

	public void log(long l) {
		log(String.valueOf(l));
	}

	public void log(char c) {
		log(String.valueOf(c));
	}

	public <T> void log(T[] tArr) {
		StringBuilder r = new StringBuilder();
		r.append("[");
		for (int i = 0; i < tArr.length; i++) {
			if (i == tArr.length - 1)
				r.append(String.valueOf(tArr[i]));
			else
				r.append(String.valueOf(tArr[i]) + ", ");
		}
		r.append("]");
		log(r.toString());
	}

	public void log(int... ints) {
		StringBuilder r = new StringBuilder();
		for (int i : ints)
			r.append(i + ", ");
		log(r.substring(0, r.length() - 2));
	}

	public void log(double... doubles) {
		StringBuilder r = new StringBuilder();
		for (double d : doubles)
			r.append(d + ", ");
		log(r.substring(0, r.length() - 2));
	}

	public void saveTrajectory(Trajectory trajectory) {
		try {
			Paths.get("/home/lvuser/trajectories/").toFile().mkdirs();
			File trajectoryFile = Paths.get("/home/lvuser/trajectories/trajectory.csv").toFile();
			Pathfinder.writeToCSV(trajectoryFile, trajectory);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void disable() {
		try {
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			disabled = true;
		}
	}

	public boolean isDisabled() {
		return this.disabled;
	}
}
