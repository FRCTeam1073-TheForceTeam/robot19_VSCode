package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Vision {
	
	public double xDelta, xWidth, yDelta, yWidth, blockCount, width;
	public static edu.wpi.first.networktables.NetworkTable visionTable;
	
	public Vision() {
		System.out.println("\"Where we droppin' boys?\"");
	}
	
	public void init() {
		visionTable = Robot.netTableInst.getTable("TurretTable");
	}

	/** Checks if vision sees anything */
	public double blockCheck() {
		if (blockCount > 0) return 1;
		return 0;
	}
	
	/** Pulls variables from Network Tables */
	public void refresh() {
		xDelta = visionTable.getEntry("centerDistX").getDouble(0);
		xWidth = visionTable.getEntry("AverageWidth").getDouble(0);
		yDelta = visionTable.getEntry("centerDistY").getDouble(0);
		yWidth = visionTable.getEntry("AverageHeight").getDouble(0);
		blockCount = visionTable.getEntry("Blocks").getDouble(0);
		
		SmartDashboard.putNumber("xDelta", xDelta);
		SmartDashboard.putNumber("xWidth", xWidth);
		SmartDashboard.putNumber("yDelta", yDelta);
		SmartDashboard.putNumber("yWidth", yWidth);
		SmartDashboard.putNumber("Block Count", blockCount);
	}
}