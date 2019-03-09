package frc.robot.commands.HatchCommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/** 
 * Raises finger
 * @author Nathaniel
 */
public class FingerRaise extends Command {

  /** 
   * Raises finger
   * @author Nathaniel
   */
  public FingerRaise() {
    requires(Robot.hatch);
  }

  /** Raises finger and finishes when confirmed raised */
  @Override
  protected boolean isFinished() {
    Robot.hatch.fingerRaise();
    return Robot.pnuematic.isFingerExtended();
  }
}
