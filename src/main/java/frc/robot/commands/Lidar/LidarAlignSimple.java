package frc.robot.commands.Lidar;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.*;

public class LidarAlignSimple extends Command {

	/** Output for Motor Power */
	private double leftMotorOutput, rightMotorOutput;

	/** Just a delay */
	private double executes = 0;

	private double distance, trigger, speed, difSpeed, crossMeasure, leftAngle, rightAngle;
	
	private double lidarLeft = Robot.lidar.lidarLeft;
	private double lidarRight = Robot.lidar.lidarRight;
	private double lidarAngle = Robot.lidar.lidarAngle;
	private double lidarLeftUse, lidarRightUse, lidarAngleUse;

	private double speedLeft = (-3.95*Math.pow(10, -8)*Math.pow(lidarLeftUse, 2)+(5.95*Math.pow(10,-4)*lidarLeftUse)-.203),
	speedRight = (-3.95*Math.pow(10, -8)*Math.pow(lidarRightUse, 2)+(5.95*Math.pow(10,-4)*lidarRightUse)-.203);
	
		// speedLeft = (-2.57*Math.pow(10, -7)*Math.pow(lidarLeft, 2)+(8.43*Math.pow(10,-4)*lidarLeft)-.4);
		// speedRight = (-2.57*Math.pow(10, -7)*Math.pow(lidarRight, 2)+(8.43*Math.pow(10,-4)*lidarRight)-.4);

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
	public LidarAlignSimple(double speed) {
		requires(Robot.drivetrain);
		this.speed = speed;
	}

	/** 
	 * @author Cam
	 */
	public LidarAlignSimple() {
		requires(Robot.drivetrain);

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		
		//Sets up the variable and ignores the -1 (error) values
		if(lidarLeft != -1){
			lidarLeftUse = lidarLeft;
			}
		else{
			lidarLeftUse = lidarLeftUse;
		}
		if(lidarRight != -1){
			lidarRightUse = lidarRight;
		}
		else{
			lidarRightUse = lidarRightUse;
		}
		if(lidarAngle != -1){
			lidarAngleUse = lidarAngle;
		}	
		else{
			lidarAngleUse = lidarAngleUse;
		}
}
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		//Sets up the variable and ignores the -1 (error) values
		
		if(lidarLeft != -1){
			lidarLeftUse = lidarLeft;
			}
		else{
			lidarLeftUse = lidarLeftUse;
		}
		if(lidarRight != -1){
			lidarRightUse = lidarRight;
		}
		else{
			lidarRightUse = lidarRightUse;
		}
		if(lidarAngle != -1){
			lidarAngleUse = lidarAngle;
		}	
		else{
			lidarAngleUse = lidarAngleUse;
		}

		//Calculates other side and angle measurements of the triangle
		crossMeasure = Math.sqrt((Math.pow(lidarLeftUse, 2)+Math.pow(lidarRightUse, 2))-(2*lidarLeftUse*lidarRightUse*Math.cos(Math.toRadians(lidarAngleUse))));
		leftAngle = Math.toDegrees(Math.asin(lidarRightUse*(Math.sin(Math.toRadians(lidarAngleUse))/crossMeasure)));
		rightAngle = Math.toDegrees(Math.asin(lidarLeftUse*(Math.sin(Math.toRadians(lidarAngleUse))/crossMeasure)));
		//totalDist = 
		
		if((lidarLeftUse <= stopDistance) || (lidarRightUse <=stopDistance)){
			Robot.drivetrain.tank(0, 0);
			SmartDashboard.putString("LidarAlign", "Straight");
		}

		//sets the correction speed based on triangle leg length (relative to the stop difference)
		else if(lidarLeftUse > (lidarRightUse + legError)){
			Robot.drivetrain.tank(speedLeft, speedRight);
		}
		else if(lidarRightUse > (lidarLeftUse + legError)){
			Robot.drivetrain.tank(speedLeft, speedRight);
		}

		}
	// Make this return true when this Command no longer needs to run execute(), stops at a set distance (stopDistance) or for driver/opperator control
	protected boolean isFinished() {
		if((lidarLeftUse == stopDistance && lidarRightUse == stopDistance) || (Robot.oi.driverCancel.get() == true) || (Robot.oi.operatorCancel.get() == true)){
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
	