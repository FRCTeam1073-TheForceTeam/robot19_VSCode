package frc.robot.commands.HatchCommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Duck debug program.
 * @author Ben
 */
public class DuckDebug extends CommandGroup {
  public DuckDebug() {
    addSequential(new HatchExtend());
    addSequential(new WaitCommand(0.5));
    addSequential(new HatchRetract());
  }
}
