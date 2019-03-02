/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class ModeSwitch extends InstantCommand {
  /**
   * Swaps the operator mode to the next mode in the order
   * 
   * Hatch -> Cargo
   * Cargo -> Hatch
   * 
   * @author Jack McEvoy
   */
  public ModeSwitch() {
    super();
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    if(Robot.operatorMode == "Hatch") Robot.operatorMode = "Cargo";
    if(Robot.operatorMode == "Cargo" || Robot.operatorMode == "Climb") Robot.operatorMode = "Hatch";
  }

}
