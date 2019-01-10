package frc.robot.commands.AutonomousTools;

import frc.robot.*;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnWithGyro extends Command {
	
	private double turnSpeed;
	private double turnDegrees;
	private String turnDirection;
	private double originalDegrees;
	private double slowdownDistance = 8;
	private double slowdownMin = .6;
	
	/** Uses basic drive to turn based on the gyro's position from the last time the gyro was reset
	 * 
	 * @author Jack
	 * @reviewer Nathaniel
	 * @category Drive Command
	 * @param Speed from 0 to 1
	 * @param Degrees should be positive
	 * @param Direction should be either "clockwise" or "counterclockwise"
	 * 
	 */
    public TurnWithGyro(double Speed, double Degrees, String Direction) {
    	turnSpeed = Speed;
    	turnDegrees = Degrees;
    	turnDirection = Direction;
        requires(Robot.drivetrain);

    }
    
    // Called just before this Command runs the first time
    protected void initialize() {    	
    	/* Remember encoder values for later */
////    	leftEnc = RobotMap.leftMotor1.getSelectedSensorPosition(0);
////    	rightEnc = RobotMap.rightMotor1.getSelectedSensorPosition(0);
    	
    	originalDegrees = RobotMap.headingGyro.getAngle();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double right = turnSpeed, left = turnSpeed;
 	   	
    	if(turnDirection.equals("clockwise")) {
 	   		if(Math.abs(RobotMap.headingGyro.getAngle() - (originalDegrees + turnDegrees)) > slowdownDistance) {
 	   			Robot.drivetrain.difDrive.tankDrive(left*-1, right);
 	   		}
 	   		else if(Math.abs(RobotMap.headingGyro.getAngle() - (originalDegrees + turnDegrees)) <= slowdownDistance) {
 	   			Robot.drivetrain.difDrive.tankDrive(Double.min(left*(slowdownDistance - (originalDegrees + turnDegrees))*-.01, slowdownMin*-1), Double.max(right*(slowdownDistance - (originalDegrees + turnDegrees))*.01, slowdownMin));
 	   		}
 	   	}else if(turnDirection.equals("counterclockwise")){
 	   		if(Math.abs(RobotMap.headingGyro.getAngle() - (originalDegrees - turnDegrees)) > slowdownDistance) {
 	   			Robot.drivetrain.difDrive.tankDrive(left, right*-1);
 	   		}
 	   		else if(Math.abs(RobotMap.headingGyro.getAngle() - (originalDegrees - turnDegrees)) <= slowdownDistance) {
 	   			Robot.drivetrain.difDrive.tankDrive(Double.max(left*(slowdownDistance - (originalDegrees - turnDegrees))*.01, slowdownMin), Double.min(right*(slowdownDistance - (originalDegrees - turnDegrees))*-.01, slowdownMin*-1));
 	   		}
 	   	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean finish = false;
    	if(turnDirection.equals("clockwise")) {
    		finish = (RobotMap.headingGyro.getAngle() >= (originalDegrees + turnDegrees));
    	} else if(turnDirection.equals("counterclockwise")) {
    		finish = (RobotMap.headingGyro.getAngle() <= (originalDegrees - turnDegrees));
    	} else {
    		finish = false;
    	}
    	
    	if(Robot.oi.driverCancel.get() == true || Robot.oi.operatorCancel.get() == true){
    		finish = true;
    	}
    	
    	return finish;
    }
  
    
    // Called once after isFinished returns true
    protected void end() {
    	/* Set the encoder values back to what they were */
////    	RobotMap.leftMotor1.setSelectedSensorPosition((int) leftEnc, 0, 10);
//// 	RobotMap.rightMotor1.setSelectedSensorPosition((int) rightEnc, 0, 10);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
