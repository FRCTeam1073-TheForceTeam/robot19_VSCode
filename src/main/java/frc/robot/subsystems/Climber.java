package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Presets;
import frc.robot.RobotMap;
import frc.robot.commands.ClimbControls;

/**
 * @author Nathaniel
 */
public class Climber extends Subsystem {
    
    public final WPI_TalonSRX rightClimber = RobotMap.rightClimber;
	public final WPI_TalonSRX leftClimber = RobotMap.leftClimber;
	public final WPI_TalonSRX climbCrawler = RobotMap.climbWheels;
	
	public double climbEncoder;
	
    /**
 	 * @author Nathaniel
 	 */
	public Climber() {
		/* Reset all motors */
		rightClimber.configFactoryDefault();
		leftClimber.configFactoryDefault();
		climbCrawler.configFactoryDefault();

    	rightClimber.setSafetyEnabled(false);
		leftClimber.setSafetyEnabled(false);
		climbCrawler.setSafetyEnabled(false);

		/* Set Neutral Mode */
		rightClimber.setNeutralMode(NeutralMode.Brake);
		leftClimber.setNeutralMode(NeutralMode.Brake);
		climbCrawler.setNeutralMode(NeutralMode.Brake);
		
		/* Configure the left Talon's selected sensor to a Quad Encoder*/
		rightClimber.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, Presets.timeoutMS);
		leftClimber.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, Presets.timeoutMS);
		
		/* Configure output and sensor direction */
		rightClimber.setInverted(false);
		leftClimber.setInverted(true);
		leftClimber.follow(rightClimber);

		climbCrawler.setInverted(false);
	}
    
    @Override
    public void initDefaultCommand() {
    	setDefaultCommand(new ClimbControls(Presets.deadzone));
	}

    public void periodic() {
		climbEncoder = rightClimber.getSelectedSensorPosition();
	}

	public void tank(double val) {
		rightClimber.set(ControlMode.PercentOutput, val);
	}

	public void crawl(double val) {
		climbCrawler.set(ControlMode.PercentOutput, val);
	}
}