/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.SystemsTests;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;

/**
 * Global command for doing systems tests.
 * Uses POV to select which subsystem to test.
 * @author Ben
 */
public class GlobalSystemsTest extends CommandGroup {
  public GlobalSystemsTest() {
    switch(Robot.oi.operatorControl.getPOV()){
      case 0:addSequential(new SystemTest());
      case 270:addSequential(new HatchSystemsTest());
    }
  }
}
