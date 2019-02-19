package frc.robot.subsystems;

import frc.robot.*;

public class Lidar {

	public boolean stop;
	
	public Lidar() {
		Robot.debugPrint("\"Where we droppin' boys?\"");
	}
	
	/** Pulls variables from Network Tables */
	public void refresh() {
		stop = Robot.networktable.table.getEntry("Stop").getBoolean(false);
		
		Robot.networktable.table.getEntry("LidarReadout").setString("");
	}
}