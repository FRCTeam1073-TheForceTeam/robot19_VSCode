package frc.robot.commands.HatchCommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Full hatch place
 * @author Nathaniel
 */
public class HatchPlace extends CommandGroup {

  /**
   * Full hatch place
   * @author Nathaniel
   */
  public HatchPlace() {
    addSequential(new HatchExtend());
    addSequential(new WaitCommand(.5));
    addSequential(new FingerLower());
    addSequential(new WaitCommand(.2));
    addSequential(new HatchRetract());
  }
}
