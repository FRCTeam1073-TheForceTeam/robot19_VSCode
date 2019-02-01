package frc.robot.commands.AutonomousTools;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class TurnToPoint extends Command {
	
	private double turnSpeed;
	private double turnDegrees;
	private double originalDegrees;
	private double slowdownMin = .5;
	private int loop;
	double turnSpeedDecreased;
	
	/** Uses basic drive to turn based on the gyro's position from the last time the gyro was reset
	 * 
	 * @author Jask
	 * @category Drive Command
	 * @param Speed from 0 to 1
	 * @param Degrees should be positive
	 * @param Direction should be either "clockwise" or "counterclockwise"
	 * 
	 */
    public TurnToPoint(double Speed, double Degrees) {
    	turnSpeed = Speed;
    	turnDegrees = Degrees;
    	
    	
        requires(Robot.drivetrain);

    }
    
    // Called just before this Command runs the first time
    protected void initialize() {    	
    	/* Remember encoder values for later */
////    	leftEnc = RobotMap.leftMotor1.getSelectedSensorPosition(0);
////    	rightEnc = RobotMap.rightMotor1.getSelectedSensorPosition(0);
    	
    	turnSpeedDecreased = turnSpeed;
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	
    	originalDegrees = RobotMap.headingGyro.getAngle()%360;
    	
    	if (originalDegrees < turnDegrees)
    	{
			RobotMap.leftMaster.set(ControlMode.PercentOutput, -Double.max(slowdownMin, turnSpeedDecreased));
			RobotMap.rightMaster.set(ControlMode.PercentOutput, Double.max(slowdownMin, turnSpeedDecreased));
    	}
    	else if (originalDegrees > turnDegrees)
    	{
			RobotMap.leftMaster.set(ControlMode.PercentOutput, Double.max(slowdownMin, turnSpeedDecreased));
			RobotMap.rightMaster.set(ControlMode.PercentOutput, -Double.max(slowdownMin, turnSpeedDecreased));
    	}
 	   	
   	if (originalDegrees > (turnDegrees - 15) && originalDegrees < (turnDegrees + 15) ){
    		
    		if(loop%20==0 && loop <= 100){
    		turnSpeedDecreased =  turnSpeedDecreased * 0.95;
    }
    		
    	}
    	
    	
    	loop++;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean finish = false;
    	
    	if (originalDegrees > (turnDegrees - 0.5) && originalDegrees < (turnDegrees + 0.5))
    	{
    		finish = true;
    	}
    	
    	return finish;
    }
  
    
    // Called once after isFinished returns true
    protected void end() {
    	/* Set the encoder values back to what they were */
////    	RobotMap.leftMotor1.setSelectedSensorPosition((int) leftEnc, 0, 10);
////    	RobotMap.rightMotor1.setSelectedSensorPosition((int) rightEnc, 0, 10);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
