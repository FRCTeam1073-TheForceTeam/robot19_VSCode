package frc.robot.commands.HatchCommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class FingerDebug extends CommandGroup {
  /**
   * Add your docs here.
   */
  public FingerDebug() {
    addSequential(new FingerRaise());
    addSequential(new WaitCommand(0.5));
    addSequential(new FingerLower());
  }
}
