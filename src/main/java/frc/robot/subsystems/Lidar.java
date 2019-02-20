package frc.robot.subsystems;

import frc.robot.*;

public class Lidar {

	public boolean stop;

	public double degrees, distance;
	
	public Lidar() {
		Robot.debugPrint("\"Where we droppin' boys?\"");
	}
	
	/** Pulls variables from Network Tables */
	public void refresh() {
		stop = Robot.networktable.table.getEntry("Stop").getBoolean(false);
		degrees = Robot.networktable.table.getEntry("degree").getDouble(0);
		distance = Robot.networktable.table.getEntry("distance").getDouble(0);
		
		if (degrees <= 5) Robot.networktable.table.getEntry("LidarReadout").setString("Degrees: " + degrees + "\tDistance: " + distance);
		
	}
}