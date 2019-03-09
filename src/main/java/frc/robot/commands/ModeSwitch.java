package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OperatorMode;
import frc.robot.Robot;
import frc.robot.commands.HatchCommands.*;
/**
 * Swaps the operator mode to the next mode in the order
 * @author Nathaniel,Jack
 */
public class ModeSwitch extends Command {

  /** 
   * Type of change:
   * 0 = cargo/hatch toggle
   * 1 = climber set
   */
  private int val = 0;

  /**
   * Swaps the operator mode to the next mode in the order
   * @author Nathaniel
   */
  public ModeSwitch(int val) {
    this.val = val;
  }

  /** Called once when the command executes */
  @Override
  protected boolean isFinished() {
    if (val == 0) {
      if (Robot.operatorMode.equals(OperatorMode.HATCH)) {
        Robot.operatorMode = OperatorMode.CARGO;
        Robot.oi.operatorLeft.whenPressed(new ExampleCommand());
        Robot.oi.operatorRight.whenPressed(new ExampleCommand());
      }
      else {
        Robot.operatorMode = OperatorMode.HATCH;
        Robot.oi.operatorLeft.whenPressed(new HatchGrab());
        Robot.oi.operatorRight.whenPressed(new HatchPlace());
      }
    }
    else if (val == 1) {
      Robot.operatorMode = OperatorMode.CLIMB;
      Robot.oi.operatorLeft.whenPressed(new ExampleCommand());
      Robot.oi.operatorRight.whenPressed(new ExampleCommand());
    }
    return true;
  }
}
