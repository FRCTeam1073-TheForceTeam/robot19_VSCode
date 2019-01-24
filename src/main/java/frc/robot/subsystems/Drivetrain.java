package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Presets;
import frc.robot.RobotMap;
import frc.robot.commands.DriveControls;


public class Drivetrain extends Subsystem {
    
    private final WPI_TalonSRX rightMotor1 = RobotMap.rightMotor1;
    private final WPI_VictorSPX rightMotor2 = RobotMap.rightMotor2;
    private final WPI_TalonSRX leftMotor1 = RobotMap.leftMotor1;
	private final WPI_VictorSPX leftMotor2 = RobotMap.leftMotor2;
	
	public double leftEncoder;
	public double rightEncoder;
    
    /** The Robot's Drivetrain */
    public DifferentialDrive difDrive;
    
	public Drivetrain() {
		leftMotor1.setInverted(true);
    	leftMotor2.setInverted(true);
    	rightMotor1.setInverted(false);
    	rightMotor2.setInverted(false);
    	
    	leftMotor2.follow(leftMotor1);
    	rightMotor2.follow(rightMotor1);
    	
    	rightMotor1.setSafetyEnabled(false);
    	rightMotor2.setSafetyEnabled(false);
    	leftMotor1.setSafetyEnabled(false);
		leftMotor2.setSafetyEnabled(false);
		
		leftMotor1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		rightMotor1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
	}
    
    @Override
    public void initDefaultCommand() {
    	setDefaultCommand(new DriveControls(Presets.deadzone));
    }

    public void periodic() {
		leftEncoder = leftMotor1.getSelectedSensorPosition();
		rightEncoder = rightMotor1.getSelectedSensorPosition();
    }
}