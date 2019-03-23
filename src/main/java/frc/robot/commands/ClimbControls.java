package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OperatorMode;
import frc.robot.Presets;
import frc.robot.Robot;
import frc.robot.RobotMap;

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
	 * @author Nathaiel
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
		if (Robot.operatorMode.equals(OperatorMode.CLIMB) && ampCheck(Presets.maxClimberAmps)) speedCheck();
		else Robot.climber.tank(0);
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
	private boolean ampCheck(double limit){
		System.out.println("Climber Amperage: " + Robot.climber.rightClimber.getOutputCurrent());
		return Robot.climber.rightClimber.getOutputCurrent() >= limit;
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