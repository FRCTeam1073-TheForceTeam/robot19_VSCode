package frc.robot.commands.HatchCommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Raises hatch grabber
 * @author Nathaniel
 */
public class HatchGrabberUp extends Command {
  
  /**
   * Raises hatch grabber
   * @author Nathaniel
   */
  public HatchGrabberUp() {
    requires(Robot.hatch);
  }

  /** Raises hatch grabber and finishes when confirmed raised */
  @Override
  protected boolean isFinished() {
    Robot.hatch.hatchGrabberUp();
    return Robot.hatch.isHatchGrabberRaised();
  }
}