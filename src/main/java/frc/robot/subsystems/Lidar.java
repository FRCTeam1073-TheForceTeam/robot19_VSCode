package frc.robot.subsystems;

import frc.robot.*;

public class Lidar {

	public double lidarDistance;
	
	public Lidar() {
	}
	
	/** Pulls variables from Network Tables */
	public void refresh() {	
		lidarDistance = (Robot.networktable.table.getEntry("point1").getDouble(0) + Robot.networktable.table.getEntry("point2").getDouble(0)) / 2;
		
		Robot.networktable.table.getEntry("LidarReadout").setString("LiDAR Distance: " + lidarDistance);
	}
}