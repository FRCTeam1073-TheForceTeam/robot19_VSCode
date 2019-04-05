package frc.robot.commands.HatchCommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/** 
 * Lowers hatch grabber
 * @author Nathaniel
 */
public class HatchGrabberDown extends Command {
  
  /** 
   * Lowers hatch grabber
   * @author Nathaniel
   */
  public HatchGrabberDown() {
    requires(Robot.hatch);
  }

  protected void initialize() {
    Robot.bling.sendHatchGrabberDown();
  }

  /** Lowers hatch grabber and finishes when confirmed lowered */
  @Override
  protected boolean isFinished() {
    Robot.hatch.hatchGrabberDown();
    return Robot.hatch.isHatchGrabberLowered();
  }

  protected void end() {
    Robot.bling.sendDefaultPattern();
  }

}
