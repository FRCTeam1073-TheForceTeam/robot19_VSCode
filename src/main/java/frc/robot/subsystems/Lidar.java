package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.*;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Lidar extends Subsystem {

	public double lidarDistance, lidarLeft, lidarRight, lidarAngle;
	
	public Lidar() {
	}
	
	@Override
	protected void initDefaultCommand() {
	}
	/** Pulls variables from Network Tables */
	public void refresh() {	
		lidarDistance = (Robot.networktable.table.getEntry("point1").getDouble(0) + Robot.networktable.table.getEntry("point2").getDouble(0)) / 2;
		lidarLeft = Robot.networktable.table.getEntry("point1").getDouble(0);
		lidarRight =Robot.networktable.table.getEntry("point2").getDouble(0);
		lidarAngle = Robot.networktable.table.getEntry("LidarAngle").getDouble(0);
		SmartDashboard.putNumber("LeftVal", lidarLeft);
		SmartDashboard.putNumber("RightVal", lidarRight);

		Robot.networktable.table.getEntry("LidarReadout").setString("LiDAR Distance: " + lidarDistance);
	}
}