/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.OI;

public class CargoControls extends Command {

  /* The deadzone value for the triggers */
  public double deadzone;

  /* The maximum error allowed for when it resets to neutral */
  public double maxError;

  /**
   * This is the cargo manipulator controls during tele-op.
   * It is the default command of the cargo subsystem.
   * 
   * This command requires the cargo subsystem so that it
   * take priority over other commands using the subsystem.
   * 
   * This command does not finish.
   * 
   * @author Jack
   * @category Drive Command
   */

  public CargoControls(double deadzone) {
    requires(Robot.cargo);
    this.deadzone = deadzone;

    //This value is a guess and must be tested/replaced
    maxError = 100;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    //Determines state of robot, and drives motor if corresponding button is pressed
    //and the corresponding limit switch is not activated
    if(triggerPast("right")){
      if(!Robot.cargo.getLimitTop()){
        Robot.cargo.liftDrive(1);
      }
      if(Robot.cargo.getLimitTop()){
        Robot.cargo.collectorSpin(1);
      }
    }
    else if(triggerPast("right")){
      if(!Robot.cargo.getLimitBottom()){
        Robot.cargo.liftDrive(-1);
      }
      if(Robot.cargo.getLimitBottom()){
        Robot.cargo.collectorSpin(-1);
      }
    }

    //Rudimentary neutral-reset, error corrects by virtue but it's very rough
    else if(!triggerPast("left") && !triggerPast("right")){
      if(Robot.cargo.getEncoder() < (0 - maxError)) Robot.cargo.liftDrive(1);
      if(Robot.cargo.getEncoder() > (0 + maxError)) Robot.cargo.liftDrive(-1);
    }
  }

  //Returns whether the requested side's trigger is past the deadzone and is therefore "pressed"
  private boolean triggerPast(String side){
    if(side.equals("right")) return Robot.oi.operatorControl.getRightTrigger() >= .5 + deadzone;
    if(side.equals("left")) return Robot.oi.operatorControl.getLeftTrigger() >= .5 + deadzone;
    return false;
  }

  //As a default driving command, this will never need to end
  @Override
  protected boolean isFinished() {
    return false;
  }
}
