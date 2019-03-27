/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.Robot;
import frc.robot.commands.AutonomousTools.*;
import frc.robot.commands.HatchCommands.*;

public class PerfectPickup extends CommandGroup {

  public PerfectPickup() {
    addParallel(new SendFlipperToTop(.75));
    addSequential(new Align());
    if (!Robot.canceled) {
      addSequential(new AdvancederDrive(20, "forward", 400));
      addSequential(new WaitCommand(.1));
      addSequential(new HatchGrabberDown());
      addSequential(new AdvancederDrive(25, "backward", 500));
      addParallel(new HatchGrabberUp());
      addSequential(new SendFlipperToMid(.75));
    }
  }
}
