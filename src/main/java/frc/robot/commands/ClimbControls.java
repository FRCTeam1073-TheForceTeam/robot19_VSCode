package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Presets;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ClimbControls extends Command {
	
	/** Controller Dead Zone */
	private double deadzone;

	/** Prevents motor from moving upwards */
	private boolean preventUp;

	/** Prevents motor from moving downwards */
	private boolean preventDown;

	/** Current estimate of amperage value */
	private double estValue;

	private DigitalInput climberLeftLim = RobotMap.climberLeftLim;
	private DigitalInput climberRightLim = RobotMap.climberRightLim;

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
	 * @author Nathaniel
	 * @author Jack
	 * @see /subsystems/Climber.java
	 * @category Drive Command
	 */
	public ClimbControls(double deadzone) {
		requires(Robot.climber);
		this.deadzone = deadzone;
	}

	/** Called Repeatedly */
	protected void execute() {
		/* Outputs Checked Controller Data to Motors */
		ampCheck(Presets.deadzone);
		if(!preventUp && !preventDown){
			speedCheck();
		}
		
		if (Robot.oi.operatorControl.y.get()) Robot.climber.crawl(.75);
		else Robot.climber.crawl(0);
	}

	private void speedCheck() {
		if (Robot.oi.operatorControl.leftBumper.get()) Robot.climber.tank(deadZoneCheck(Robot.oi.operatorControl.getRawAxis(5)));
		else Robot.climber.tank(deadZoneCheck(Robot.oi.operatorControl.getRawAxis(5)) / 2);
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
	 * @param Input to check against amperage of motors
	 * @return If amperage is over limit
	 */
	private void ampCheck(double limit) {
		flagReset();
		if(smooth(Robot.climber.rightClimber.getOutputCurrent()) >= limit){
			if(Robot.oi.operatorControl.getY1() > 0) preventUp = true;
			if(Robot.oi.operatorControl.getY1() < 0) preventDown = true;
		}
	}
	
	/**
	 * @param newVal Value of the current amperage
	 * @return A very smooth estimate of the amperage
	 */
	private double smooth(double newVal) {
		estValue = Presets.smoothingAlpha * estValue + (1.0 - Presets.smoothingAlpha) * newVal;
		return estValue;
	}

	/** 
	 * Resets the flags if the other direction is held
	 */
	private void flagReset(){
		if(Robot.oi.operatorControl.getY2() > 0) preventDown = false;
		if(Robot.oi.operatorControl.getY2() < 0) preventUp = false;
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