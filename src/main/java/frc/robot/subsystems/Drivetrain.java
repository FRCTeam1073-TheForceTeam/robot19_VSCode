package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**Author: Alex Hill, tons of help from Nathaniel
* Not tested yet, will do so next meeting. :)
*/

// imports reference folders
import frc.robot.RobotMap;
import frc.robot.commands.ExampleCommand;

//Identifying and assigning motors, also putting methods to control the system

public class Drivetrain extends Subsystem {

    private final WPI_TalonSRX rightMotor1 = RobotMap.rightMotor1;
    private final WPI_VictorSPX rightMotor2 = RobotMap.rightMotor2;
    private final WPI_TalonSRX leftMotor1 = RobotMap.leftMotor1;
    private final WPI_VictorSPX leftMotor2 = RobotMap.leftMotor2;

    public DifferentialDrive difDrive;

    public Drivetrain() {
        rightMotor1.setInverted(false);
        rightMotor2.setInverted(false);
        leftMotor1.setInverted(false);
        leftMotor2.setInverted(false);

        rightMotor2.follow(rightMotor1);
        leftMotor2.follow(leftMotor1);

        rightMotor1.setSafetyEnabled(false);
        rightMotor2.setSafetyEnabled(false);
        leftMotor1.setSafetyEnabled(false);
        leftMotor2.setSafetyEnabled(false);

        difDrive = new DifferentialDrive(RobotMap.rightMotor1, RobotMap.leftMotor1);

    }

    @Override
    // Setting deadzone as default on the controller
    public void initDefaultCommand() {
        setDefaultCommand(new ExampleCommand());

    }

    public void periodic() {

    }
}