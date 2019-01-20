/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutonomousTools;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PsuedoHatch extends CommandGroup {
  /**
   * Add your docs here.
   */
  public PsuedoHatch() {
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
    addSequential(new AdvancederDrive(18, "forward", 2000));
    addSequential(new Wait(1));
    addSequential(new TurnWithGyro(.6, 180, "clockwise"));
    addSequential(new AdvancederDrive(18, "forward", 2000));
  }
}
