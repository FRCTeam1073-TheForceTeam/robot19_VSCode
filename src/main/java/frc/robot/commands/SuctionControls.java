/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SuctionControls extends Command {

  private double deadzone;
  private boolean manualControls = true;

/**
	 * This is the suction climber movement controls
	 * for the teleoperated period of a match.
	 * It is also the default command for
	 * the SuctionClimber.java subsystem.
	 * 
	 * This command does not finish.
	 * 
	 * @author Jack
	 * @see /subsystems/SuctionClimber.java
	 * @category Drive Command
	 */

  public SuctionControls(double deadzone) {
    requires(Robot.suctionClimber);
    this.deadzone = deadzone;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(manualControls){
      Robot.suctionClimber.arm(deadZoneCheck(Robot.oi.operatorControl.getRawAxis(5)) / 2);
      if(Robot.oi.operatorControl.leftBumper.get()) Robot.suctionClimber.latch();
    }
    else if(Robot.oi.operatorControl.leftBumper.get()){
        Robot.suctionClimber.arm(.5);
        Robot.suctionClimber.latch();
      }
  }

  /** 
	  * @param val Input to check against dead zone
	  * @return If within dead zone return 0, Else return val
	  */
	  private double deadZoneCheck(double val) {
	  	if (Math.abs(val) < deadzone) return 0;
	  	return val;
    }
  
  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }
}
