package frc.robot.commands.Lidar;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.commands.Lidar.*;
import edu.wpi.first.networktables.NetworkTableInstance;
import java.lang.Math;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class LidarPrint extends Command {

	/** Output for Motor Power */
	private double leftMotorOutput, rightMotorOutput;

	/** Just a delay */
	private double executes = 0;

	private double distance, trigger, speed, difSpeed, lidarLeft, lidarRight, lidarAngle, crossMeasure, leftAngle, rightAngle, speedLeft, speedRight;

	private double distAway;

	private double stopDistance = 450.0;

	// private double tri = 203.2;

	// private double in = 25.4;
	
	private double legError = 50;

	// private double halfTri = 101.6;

	// private double posError = 10;

	// private double negError = -10;

	private final double validZone = 10;

	



	/**
	 * @category Autonomous
	 * @author Cam
	 */

	/** 
	 * @author Cam
	 */
	public LidarPrint() {

	}

	// Called just before this Command runs the first time
	protected void initialize() {
}
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		//Sets up the variable and ignores the -1 (error) values
		if(Robot.networktable.table.getEntry("point1").getDouble(0) != -1){
			lidarLeft = Robot.networktable.table.getEntry("point1").getDouble(0);
			}
		else{
			lidarLeft = lidarLeft;
		}
		if(Robot.networktable.table.getEntry("point2").getDouble(0) != -1){
			lidarRight = Robot.networktable.table.getEntry("point2").getDouble(0);
		}
		else{
			lidarRight = lidarRight;
		}
		if(Robot.networktable.table.getEntry("point2").getDouble(0) != -1){
			lidarAngle = Robot.networktable.table.getEntry("lidarAngle").getDouble(0);
		}	
		else{
			lidarAngle = lidarAngle;
		}

		distAway = (lidarLeft +lidarRight)/2;
		//Robot.networktable.table.setEntry("distanceAway");
		}
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
			return false;
		}
	
	protected void end() {
		Robot.drivetrain.tank(0,0);
	}










	/**************************************Backup Drive*******************************************/
	/**
   	 * Arcade drive method for differential drive platform.
  	 *
  	 * @param fwd The robot's speed along the X axis [-1.0..1.0]. Forward is positive.
  	 * @param rot The robot's rotation rate around the Z axis [-1.0..1.0]. Clockwise is positive.
  	 */
	public void arcaderDrive(double fwd, double rot) {
		double maxInput = Math.copySign(Math.max(Math.abs(fwd), Math.abs(rot)), fwd);
			if (fwd >= 0.0) {
			if (rot >= 0.0) {
				leftMotorOutput = maxInput;
				rightMotorOutput = fwd - rot;
			} else {
				leftMotorOutput = fwd + rot;
				rightMotorOutput = maxInput;
			}
		} else {
			if (rot >= 0.0) {
				leftMotorOutput = fwd + rot;
				rightMotorOutput = maxInput;
			} else {
				leftMotorOutput = maxInput;
				rightMotorOutput = fwd - rot;
			}
		}
		Robot.drivetrain.tank(leftMotorOutput, rightMotorOutput);
	}
}
	
	/** Affects the style of output to motors */
	