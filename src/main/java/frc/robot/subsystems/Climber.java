package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Presets;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.ClimbControls;
import frc.robot.commands.DriveControls;

public class Climber extends Subsystem {
    
    public final WPI_TalonSRX rightClimber = RobotMap.rightClimber;
	public final WPI_VictorSPX leftClimber = RobotMap.leftClimber;
	
	public double climbEncoder;
	
    
	public Climber() {
		/* Reset all motors */
		rightClimber.configFactoryDefault();
		leftClimber.configFactoryDefault();

    	rightClimber.setSafetyEnabled(false);
		leftClimber.setSafetyEnabled(false);

		/* Set Neutral Mode */
		rightClimber.setNeutralMode(NeutralMode.Brake);
		leftClimber.setNeutralMode(NeutralMode.Brake);
		
		/* Configure the left Talon's selected sensor to a Quad Encoder*/
		rightClimber.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, Presets.timeoutMS);
		
		/* Configure output and sensor direction */
		rightClimber.setInverted(false);
		leftClimber.setInverted(true);
		leftClimber.follow(rightClimber);

	}
    
    @Override
    public void initDefaultCommand() {
    	setDefaultCommand(new ClimbControls(.1));
	}

    public void periodic() {
		climbEncoder = rightClimber.getSelectedSensorPosition();
	}

	public void tank(double left, double right) {
		leftClimber.set(ControlMode.PercentOutput, left);
		rightClimber.set(ControlMode.PercentOutput, right);
	}

}