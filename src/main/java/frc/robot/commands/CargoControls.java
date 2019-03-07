package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OperatorMode;
import frc.robot.Robot;

/**
 * This is the cargo manipulator controls during tele-op.
 * It is the default command of the cargo subsystem.
 * 
 * This command requires the cargo subsystem so that it
 * take priority over other commands using the subsystem.
 * 
 * This command does not finish.
 * 
 * @author Nathaiel
 * @category Cargo Command
 */
public class CargoControls extends Command {

  /** The deadzone value for the triggers */
  public double deadzone;

  /**
   * This is the cargo manipulator controls during tele-op.
   * It is the default command of the cargo subsystem.
   * 
   * This command requires the cargo subsystem so that it
   * take priority over other commands using the subsystem.
   * 
   * This command does not finish.
   * 
   * @author Nathaiel
   * @category Cargo Command
   */
  public CargoControls(double deadzone) {
    requires(Robot.cargo);
    this.deadzone = deadzone;
  }

  /** Called Repeatedly */
  @Override
  protected void execute() {
    if (Robot.operatorMode.equals(OperatorMode.CARGO)) {
      Robot.cargo.lift(-deadZoneCheck(Robot.oi.operatorControl.getRawAxis(1)));
      if (deadZoneCheck(Robot.oi.operatorControl.getRightTrigger()) > 0 || deadZoneCheck(Robot.oi.operatorControl.getLeftTrigger()) > 0) 
			Robot.cargo.collector(deadZoneCheck(Robot.oi.operatorControl.getRightTrigger()) - deadZoneCheck(Robot.oi.operatorControl.getLeftTrigger()));
			else Robot.cargo.collector(0);
    } else {
      Robot.cargo.lift(0);
      Robot.cargo.collector(0);
    }
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
	 * This command should never finish as it 
	 * must remain active for the duration of
	 * any teleoperated period, and is only run
	 * during the teleoperated period.
	 */
  @Override
  protected boolean isFinished() {
    return false;
  }
}
