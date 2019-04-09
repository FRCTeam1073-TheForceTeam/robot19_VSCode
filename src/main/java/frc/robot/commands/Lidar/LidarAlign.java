package frc.robot.commands.Lidar;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.commands.Lidar.*;
import edu.wpi.first.networktables.NetworkTableInstance;
import java.lang.Math;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LidarAlign extends Command {
	edu.wpi.first.networktables.NetworkTable netTable;
	NetworkTableInstance netTableInst;

	/** Output for Motor Power */
	private double leftMotorOutput, rightMotorOutput;

	/** Just a delay */
	private double executes = 0;

	private double distance, trigger, speed, difSpeed, lidarLeft, lidarRight, lidarAngle, crossMeasure, leftAngle, rightAngle, speedLeft, speedRight;

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
	 * @author Nathaniel
	 */
	public LidarAlign(double speed) {
		requires(Robot.drivetrain);
		this.speed = speed;
		netTableInst = NetworkTableInstance.getDefault();
		netTable = netTableInst.getTable("1073Table");
	}

	/** 
	 * @author Cam
	 */
	public LidarAlign() {
		requires(Robot.drivetrain);
		// speedLeft = (-2.57*Math.pow(10, -7)*Math.pow(lidarLeft, 2)+(8.43*Math.pow(10,-4)*lidarLeft)-.4);
		// speedRight = (-2.57*Math.pow(10, -7)*Math.pow(lidarRight, 2)+(8.43*Math.pow(10,-4)*lidarRight)-.4);
		speedLeft = (-3.95*Math.pow(10, -8)*Math.pow(lidarLeft, 2)+(5.95*Math.pow(10,-4)*lidarLeft)-.203);
		speedRight = (-3.95*Math.pow(10, -8)*Math.pow(lidarLeft, 2)+(5.95*Math.pow(10,-4)*lidarLeft)-.203);
		netTableInst = NetworkTableInstance.getDefault();
		netTable = netTableInst.getTable("1073Table");

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		
		if(netTable.getEntry("point1").getDouble(0) != -1){
			lidarLeft = netTable.getEntry("point1").getDouble(0);
			}
		else{
			lidarLeft = lidarLeft;
		}
		if(netTable.getEntry("point2").getDouble(0) != -1){
			lidarRight = netTable.getEntry("point2").getDouble(0);
		}
		else{
			lidarRight = lidarRight;
		}
		if(netTable.getEntry("point2").getDouble(0) != -1){
			lidarAngle = netTable.getEntry("lidarAngle").getDouble(0);
		}	
		else{
			lidarAngle = lidarAngle;
		}
}
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
		if(netTable.getEntry("point1").getDouble(0) != -1){
			lidarLeft = netTable.getEntry("point1").getDouble(0);
			}
		else{
			lidarLeft = lidarLeft;
		}
		if(netTable.getEntry("point2").getDouble(0) != -1){
			lidarRight = netTable.getEntry("point2").getDouble(0);
		}
		else{
			lidarRight = lidarRight;
		}
		if(netTable.getEntry("point2").getDouble(0) != -1){
			lidarAngle = netTable.getEntry("lidarAngle").getDouble(0);
		}	
		else{
			lidarAngle = lidarAngle;
		}
		crossMeasure = Math.sqrt((Math.pow(lidarLeft, 2)+Math.pow(lidarRight, 2))-(2*lidarLeft*lidarRight*Math.cos(Math.toRadians(lidarAngle))));
		leftAngle = Math.toDegrees(Math.asin(lidarRight*(Math.sin(Math.toRadians(lidarAngle))/crossMeasure)));
		rightAngle = Math.toDegrees(Math.asin(lidarLeft*(Math.sin(Math.toRadians(lidarAngle))/crossMeasure)));
		//totalDist = 
		
		if((lidarLeft <= stopDistance) || (lidarRight <=stopDistance)){
			Robot.drivetrain.tank(0, 0);
			SmartDashboard.putString("LidarAlign", "Straight");
		}
		else if(lidarLeft > (lidarRight + legError)){
			Robot.drivetrain.tank(speedLeft, speedRight);
		}
		else if(lidarRight > (lidarLeft + legError)){
			Robot.drivetrain.tank(speedLeft, speedRight);
		}

		}

	private void getAligned(double x) {
		if (x < -validZone) Robot.drivetrain.tank(speed, speed * .5);
		else if (x > validZone) Robot.drivetrain.tank(speed * .5, speed);
	}

	private void stayCentered() {
		Robot.drivetrain.tank(speed);
	}
	private boolean straightCheck() {
		if(leftAngle == rightAngle ){
			return true;
		}
		else{
			return false;
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if(lidarLeft == stopDistance && lidarRight == stopDistance){
			return true;
		}
		else{
			return false;
		}
		
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
	