/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OperatorMode;
import frc.robot.Robot;
import frc.robot.commands.HatchCommands.*;

public class ModeSwitch extends Command {
  private int val = 0;

  // private JoystickButton operatorLeft, operatorRight;

  /**
   * Swaps the operator mode to the next mode in the order
   * @author Jack McEvoy
   */
  public ModeSwitch(int val) {
    this.val = val;
    // operatorLeft = Robot.oi.operatorLeft;
    // operatorRight = Robot.oi.operatorRight;
  }

  // Called once when the command executes
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
