package frc.robot.commands.HatchCommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/** 
 * Lowers finger
 * @author Ben
 */
public class FingerLower extends Command {
  
  public FingerLower() {
    requires(Robot.hatch);
  }

  /** Lowers the finger and finishes when confirmed to be lowered */
  @Override
  protected boolean isFinished() {
    Robot.hatch.fingerLower();
    return !Robot.pnuematic.isFingerExtended();
  }
}
