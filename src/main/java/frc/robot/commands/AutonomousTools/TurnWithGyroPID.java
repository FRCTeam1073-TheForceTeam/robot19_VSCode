package frc.robot.commands.AutonomousTools;

import frc.robot.*;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class TurnWithGyroPID extends PIDCommand {
	
	private double turnSpeed;
	private double turnDegrees;
	private String turnDirection;
	private double originalDegrees;
	private double onTargetCounter=0;
	private double timeoutCounter=0;
	
	/** Uses basic drive to turn based on the gyro's position Using  PIDCOmmand
	 * 
	 * @author Mr. Robey (adapted from Jack's TurnWithGyro)
	 * @category Drive Command
	 * @param Speed from 0 to 1
	 * @param Degrees should be positive
	 * @param Direction should be either "clockwise" or "counterclockwise"
	 * 
	 * for PIDCommand example see http://robottutorial.alexpavel.com
	 */
    public TurnWithGyroPID(double Speed, double Degrees, String Direction, double P, double I, double D) {
        super(P,I,D);
        // JRJR It would be really good to get P,I,D from a preference but I failed in my first attempt at getting it from the smart dashboard
    	turnSpeed = Speed;
    	turnDegrees = Degrees;
    	turnDirection = Direction;
        requires(Robot.drivetrain);
		System.out.println("TurnWithGyroPID constructor()  P:" + P +" I:"+ I + " D:" + D + "\n");    		

    }
    
    // Called just before this Command runs the first time
    protected void initialize() {

    	
    	originalDegrees = RobotMap.headingGyro.getAngle();
    	setInputRange(-37258,40274);
    	if (turnDirection.equals("clockwise")){
    		setSetpoint(originalDegrees + turnDegrees);
    	}
    	else if (turnDirection.equals("counterclockwise")){
    		setSetpoint(originalDegrees - turnDegrees);
    	}    
    	else
    	{
    		//ERROR
    	}
		System.out.println("TurnWithGyroPID Initalize()  originalDegrees:" + originalDegrees +" setPoint:"+ getSetpoint() +"\n");    		
    	getPIDController().setAbsoluteTolerance(1);  // the number of degrees off we can be and still be done
    	getPIDController().setOutputRange(turnSpeed*-1, turnSpeed);  //minimum and maximum motor speeds
    	getPIDController().setContinuous();
    	timeoutCounter = 0;

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean onTarget = false;
    	boolean finished = false;
    	if(Robot.oi.driverCancel.get() == true || Robot.oi.operatorCancel.get() == true){
    		System.out.println("TurnWithGyroPID Operator cancel");    		
    		return true;
    	}
 
    	//The robot could still have momentum when we hit the desired angle.  So, instead of ending right away, start a short countdown
    	onTarget = getPIDController().onTarget();
    	if (onTarget){
    		System.out.println("TurnWithGyroPID onTarget  counter=" + onTargetCounter);
    		onTargetCounter++;
    	}
    	else {
    		//System.out.println("TurnWithGyroPID NOT onTarget  timeout=" + timeoutCounter);
    		onTargetCounter = 0;
    	}
    	if (onTargetCounter >= 25){
    		finished = true;
    		System.out.println("\nTurnWithGyroPID onTarget done\n\n");
    	}
    	if (timeoutCounter++ > 150){
    		finished = true;
    		System.out.println("\nTurnWithGyroPID NOT onTarget  Timed Out !!!\n\n");
    	}
    	return finished; 
    }
  
    
    // Called once after isFinished returns true
    protected void end() {
    	getPIDController().disable();
		Robot.drivetrain.difDrive.tankDrive(0, 0);
		System.out.println("\n\nTurnWithGyroPID end() \n\n");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    public void interrupted() {
    	System.out.println("\n\nTurnWithGyroPID interrupted() \n\n");
    	end();
    }

	@Override
	protected double returnPIDInput() {
		// give the PID controller the current gyro value
		double angle = RobotMap.headingGyro.getAngle();
		//System.out.println("TurnWithGyroPID angle " + angle);
		return angle;
	}

	@Override
	protected void usePIDOutput(double output) {
		System.out.println("TurnWithGyroPID output: " + output + " angle: " + RobotMap.headingGyro.getAngle() +
				" error: " + getPIDController().getError()  );
		//JR need code here to deal with the fact that voltages below about .4 (more or less depending on battery)
		//  are not enough to move the robot
		Robot.drivetrain.difDrive.tankDrive(output*-1, output);
		
	}
}
