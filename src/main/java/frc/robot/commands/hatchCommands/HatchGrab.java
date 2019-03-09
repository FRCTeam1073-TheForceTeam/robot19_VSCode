package frc.robot.commands.HatchCommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Full hatch grab
 * @author Nathaniel
 */
public class HatchGrab extends CommandGroup {

  /**
   * Full hatch grab
   * @author Nathaniel
   */
  public HatchGrab() {
    addSequential(new FingerLower());
    addSequential(new WaitCommand(.2));
    addSequential(new HatchExtend());
    addSequential(new WaitCommand(.5));
    addSequential(new FingerRaise());
    addSequential(new WaitCommand(.2));
    addSequential(new HatchRetract());
  }
}
