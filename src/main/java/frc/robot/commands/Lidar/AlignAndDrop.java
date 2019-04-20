/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Lidar;

import frc.robot.*;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.commands.AutonomousTools.*;
import frc.robot.commands.HatchCommands.*;

public class AlignAndDrop extends CommandGroup {

  public AlignAndDrop() {
    addSequential(new AdvancedAlign());
    //addParallel(new SendFlipperToMid(.75));
    addSequential(new AdvancederDrive(25, "backward", 550));
    //addSequential(new SendFlipperToTop(.75));
    //sendFlipperToMid not orking due to a lack of encoders/limit switches
  }
}