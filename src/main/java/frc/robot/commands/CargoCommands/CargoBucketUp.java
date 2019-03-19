package frc.robot.commands.CargoCommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Raises hatch grabber
 * @author Nathaniel
 */
public class CargoBucketUp extends Command {
  
  /**
   * Raises hatch grabber
   * @author Nathaniel
   */
  public CargoBucketUp() {
  }

  /** Raises hatch grabber and finishes when confirmed raised */
  @Override
  protected boolean isFinished() {
    Robot.cargo.bucketUp();
    return Robot.cargo.isBucketUp();
  }
}