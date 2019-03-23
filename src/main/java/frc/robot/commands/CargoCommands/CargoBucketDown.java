package frc.robot.commands.CargoCommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/** 
 * Lowers hatch grabber
 * @author Nathaniel
 */
public class CargoBucketDown extends Command {
  
  /** 
   * Lowers hatch grabber
   * @author Nathaniel
   */
  public CargoBucketDown() {
  }

  /** Lowers hatch grabber and finishes when confirmed lowered */
  @Override
  protected boolean isFinished() {
    Robot.cargo.bucketDown();
    return Robot.cargo.isBucketDown();
  }
}
