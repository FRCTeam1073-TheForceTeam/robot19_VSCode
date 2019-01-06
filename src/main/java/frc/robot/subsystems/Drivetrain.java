package frc.robot.subsystems;

import frc.robot.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

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
		
	}   

	public void initDefaultCommand(){

	}

}