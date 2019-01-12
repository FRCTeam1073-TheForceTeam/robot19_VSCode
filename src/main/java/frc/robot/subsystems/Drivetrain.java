/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Drivetrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  final WPI_TalonSRX rightMotor_1 = RobotMap.rightMotor1;; 
  final WPI_VictorSPX rightMotor_2 = RobotMap.rightMotor2;

  final WPI_TalonSRX leftMotor_1 = RobotMap.leftMotor1;
  final WPI_VictorSPX leftMotor_2 = RobotMap.leftMotor2;

  public WPI_TalonSRX getrightMotor1() {
      return rightMotor_1;
  }

  public WPI_VictorSPX getrightMotor2() {
    return rightMotor_2;
  }
  public WPI_TalonSRX getleftMotor1() {
    return leftMotor_1;
  }

  public WPI_VictorSPX getleftMotor2() {
    return leftMotor_2;
  }

  @Override
  public void initDefaultCommand() {
    

    
    



    

    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
