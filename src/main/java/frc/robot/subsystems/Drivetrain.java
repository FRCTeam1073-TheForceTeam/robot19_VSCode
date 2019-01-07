package frc.robot.subsystems;
import frc.robot.*;
import frc.robot.commands.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drivetrain extends Subsystem {
    
    private final WPI_TalonSRX rightMotor1 = RobotMap.rightMotor1;
    private final WPI_VictorSPX rightMotor2 = RobotMap.rightMotor2;
    private final WPI_TalonSRX leftMotor1 = RobotMap.leftMotor1;
    private final WPI_VictorSPX leftMotor2 = RobotMap.leftMotor2;
    
    /** The Robot's Drivetrain */
    public DifferentialDrive difDrive;
    
	public Drivetrain() {
		leftMotor1.setInverted(false);
    	leftMotor2.setInverted(false);
    	rightMotor1.setInverted(false);
    	rightMotor2.setInverted(false);
    	
    	leftMotor2.follow(leftMotor1);
    	rightMotor2.follow(rightMotor1);
    	
    	rightMotor1.setSafetyEnabled(false);
    	rightMotor2.setSafetyEnabled(false);
    	leftMotor1.setSafetyEnabled(false);
    	leftMotor2.setSafetyEnabled(false);
    	
    	difDrive = new DifferentialDrive(RobotMap.leftMotor1, RobotMap.rightMotor1);
	}
    
    @Override
    public void initDefaultCommand() {
    	setDefaultCommand(new DriveControls(Presets.deadzone));
    }

    public void periodic() {
    	
    }
}