package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * This is the climber movement controls
 * for the teleoperated period of a match.
 * It is also the default command for 
 * the Climber.java subsystem.
 * 
 * This command does not finish.
 * 
 * @author Cam
 * @see /subsystems/Climber.java
 * @category Climb Command
 */
public class ClimbControls extends Command {
	
	/** Controller Dead Zone */
	private double deadzone;

	/** Controller Data: Left Y */
	private double forward;
	
	/** Controller Data: Right X */
	private double rotational;

	/** Output for Motor Power */
	private double leftClimbOutput, rightClimbOutput;

	/** Just a delay */
	private double executes = 0;


	/**
	 * This is the climber movement controls
	 * for the teleoperated period of a match.
	 * It is also the default command for
	 * the climber.java subsystem.
	 * 
	 * This command requires the drivetrain subsystem
	 * so as to give it priority over other commands 
	 * accessing the drivetrain.
	 * 
	 * This command does not finish.
	 * 
	 * @author Cam
	 * @see /subsystems/Climber.java
	 * @category Drive Command
	 */
	public ClimbControls(double deadzone) {
		requires(Robot.climber);
		this.deadzone = deadzone;
	}

	/** Called Repeatedly */
	protected void execute() {
		/* Controller Data */
		//right y axis
		forward = Robot.oi.operatorControl.getRawAxis(5);
		
		/* Outputs Checked Controller Data to Motors */
		tankClimb(deadZoneCheck(forward));
	}

	/**
   	 * Tank climb method for differential drive platform.
   	 *
   	 * @param fwd The climber's speed along the X axis [-1.0..1.0]. Forward is positive.
   	 */
	public void tankClimb(double fwd) {
		
		Robot.climber.tank(limit(fwd), limit(fwd));

	}

	/** 
	 * @param val Input to check against dead zone
	 * @return If within dead zone return 0, Else return val
	 */
	private double deadZoneCheck(double val) {
		if (Math.abs(val) < deadzone) return 0;
		return val;

	}

  	/**
   	 * Limit motor values to the -1.0 to +1.0 range.
   	 */
  	private double limit(double value) {
    	if (Math.abs(value) > 1.0) return Math.copySign(1, value);
    	return value;
  	}

	/** 
	 * This command should never finish as it 
	 * must remain active for the duration of
	 * any teleoperated period, and is only run
	 * during the teleoperated period.
	 */
	protected boolean isFinished() {
		return false;
	}
}