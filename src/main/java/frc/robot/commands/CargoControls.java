package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
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
 * @author Jack
 * @category Cargo Command
 */
public class CargoControls extends Command {

  /**
   * This is the cargo manipulator controls during tele-op.
   * It is the default command of the cargo subsystem.
   * 
   * This command requires the cargo subsystem so that it
   * takes priority over other commands using the subsystem.
   * 
   * This command does not finish.
   * 
   * @author Jack
   * @category Cargo Command
   */
  public CargoControls() {
    requires(Robot.cargo);
  }

  /** Called Repeatedly */
  @Override
  protected void execute() {
    if(Robot.oi.operatorControl.getPOV() != -1) Robot.cargo.bucketUp();
    else Robot.cargo.bucketDown();
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
