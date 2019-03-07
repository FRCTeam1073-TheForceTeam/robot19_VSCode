package frc.robot.commands.HatchCommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class DuckDebug extends CommandGroup {
  /**
   * Add your docs here.
   */
  public DuckDebug() {
    addSequential(new HatchExtend());
    addSequential(new WaitCommand(0.2));
    addSequential(new HatchRetract());
  }
}
