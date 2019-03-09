package frc.robot.commands.HatchCommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/** 
 * Retracts hatch
 * @author Ben
 */
public class HatchRetract extends Command {
  
  public HatchRetract() {
    requires(Robot.hatch);
  }

  /** Retracts hatch and finishes when confirmed retracted */
  @Override
  protected boolean isFinished() {
    Robot.hatch.hatchRetract();
    return !Robot.pnuematic.isHatchExtended();
  }
}
