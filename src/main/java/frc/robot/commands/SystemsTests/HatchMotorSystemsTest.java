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
  double val=1;
  int counter=0;
  public HatchMotorSystemsTest(){
  }
  protected void initialize(){
    Robot.hatch.setCollector(0);
    Robot.networktable.table.getEntry("DebugStateHatchCollector").setString("");
  }
  protected void execute(){
    Robot.hatch.setCollector(val);
    double speed=Robot.hatch.hatchCollect.getSelectedSensorVelocity();
    Robot.networktable.table.getEntry("DebugStateHatchCollector").setString("Power: "+val+", Speed: "+speed);
    if(counter==50){
      val=-1;
    }
    counter++;
  }
  protected boolean isFinished(){
    return counter>=100;
  }
}
