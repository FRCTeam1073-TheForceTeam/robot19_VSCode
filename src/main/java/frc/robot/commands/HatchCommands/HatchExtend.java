package frc.robot.commands.HatchCommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Extends Hatch.
 * @author Ben
 */
public class HatchExtend extends Command {
  
  public HatchExtend() {
    requires(Robot.hatch);
  }

  /** Extends hatch and finishes when confirmed extended */
  @Override
  protected boolean isFinished() {
    Robot.hatch.hatchExtend();
    return Robot.pnuematic.isHatchExtended();
  }
}