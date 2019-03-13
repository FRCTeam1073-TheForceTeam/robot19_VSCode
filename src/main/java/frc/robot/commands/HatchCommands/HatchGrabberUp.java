package frc.robot.commands.HatchCommands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Full hatch grab
 * @author Nathaniel
 */
public class HatchGrabberUp extends CommandGroup {

  /**
   * Full hatch grab
   * @author Nathaniel
   */
  public HatchGrabberUp() {
    addSequential(new HatchExtend());
  }
}
