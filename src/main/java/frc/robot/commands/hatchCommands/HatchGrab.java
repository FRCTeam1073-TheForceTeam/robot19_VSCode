/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.HatchCommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class HatchGrab extends CommandGroup {
  /**
   * Add your docs here.
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
