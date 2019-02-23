/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.HatchCommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class HatchPlace extends CommandGroup {
  /**
   * Add your docs here.
   */
  public HatchPlace() {
    addSequential(new FingerLower());
    addSequential(new HatchExtend());
    addSequential(new WaitCommand(1));
    addSequential(new HatchRetract());
  }
}
