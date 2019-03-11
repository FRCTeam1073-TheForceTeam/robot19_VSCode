package frc.robot.commands.HatchCommands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Full hatch GrabberDown
 * @author Nathaniel
 */
public class HatchGrabberDown extends CommandGroup {

  /**
   * Full hatch GrabberDown
   * @author Nathaniel
   */
  public HatchGrabberDown() {
    addSequential(new HatchRetract());
  }
}
