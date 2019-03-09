/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.SystemsTests;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.Robot;
import frc.robot.commands.HatchCommands.*;
/**
 * Finger and duck systems test.
 * @author Ben
 */
public class HatchSystemsTest extends CommandGroup {
  public HatchSystemsTest() {
    addSequential(new HatchExtend());
    addSequential(new WaitCommand(1));
    addSequential(new HatchRetract());
    addSequential(new WaitCommand(1));
    addSequential(new FingerRaise());
    addSequential(new WaitCommand(1));
    addSequential(new FingerLower());
    addSequential(new WaitCommand(1));
    addSequential(new HatchMotorSystemsTest());
  }
}
