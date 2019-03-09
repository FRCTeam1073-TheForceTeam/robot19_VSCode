/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoStartCargo extends CommandGroup {
  /**
   * Start of a sandstorm auto chain.
   * This will run off the hab and place a hatch panel on the cargo ship,
   * then go to the loading station.
   * 
   * @author Jack
   * @category Autonomous
   */
  public AutoStartCargo() {
    //Drive forward
    //Turn right slightly, drive forward, turn left slightly to allign with front of cargo ship
    //Drive forward
    //Place hatch on cargo ship
    //Drive backward slightly
    //Turn left or right
      //Left
      //Drive forward until perpendicular to loading zone
      //Turn left
      //Drive forward until at loading station
    
      //Right
      //Drive forward until perpendicular to loading zone
      //Turn right
      //Drive forward until at loading station
  }
}
