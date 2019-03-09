/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.SystemsTests;

import edu.wpi.first.wpilibj.command.*;
import frc.robot.Robot;

/**
 * Tests motors on hatch collector.
 * @author Ben
 */
public class HatchMotorSystemsTest extends Command {
  public HatchMotorSystemsTest(){

  }
  protected void initialize(){
    Robot.hatch.setFlipper(0);
    Robot.networktable.table.getEntry("DebugStateHatchFlipper").setString("");
  }
  protected void execute(){

  }
  protected boolean isFinished(){
    return false;
  }
}
