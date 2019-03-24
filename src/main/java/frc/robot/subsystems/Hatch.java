package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Presets;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.HatchControls;

/**
 * @author Nathaniel
 */
public class Hatch extends Subsystem {
  
	public final WPI_TalonSRX hatchLift = RobotMap.hatchLift;
	public final WPI_VictorSPX hatchLiftSlave = RobotMap.hatchLiftSlave;
	public final WPI_TalonSRX hatchCollect = RobotMap.hatchCollect;

	public final DigitalInput topLim = RobotMap.flipperTopLim;
	public final DigitalInput midLim = RobotMap.flipperMidLim;
	public final DigitalInput bottomLim = RobotMap.flipperBotLim;

	public final DigitalInput collectInSensor = RobotMap.collectorInSensor;
	public final DigitalInput duckInSensor = RobotMap.duckInSensor;

	/**
 	 * @author Nathaniel
 	 */
	public Hatch() {
		/* Reset all motors */
		hatchLift.configFactoryDefault();
		hatchLiftSlave.configFactoryDefault();
		hatchCollect.configFactoryDefault();

		hatchLift.setSafetyEnabled(false);
		hatchLiftSlave.setSafetyEnabled(false);
		hatchCollect.setSafetyEnabled(false);
		
		/* Set Neutral Mode */
		hatchLift.setNeutralMode(NeutralMode.Brake);
		hatchLiftSlave.setNeutralMode(NeutralMode.Brake);
		hatchCollect.setNeutralMode(NeutralMode.Brake);
		
		/* Configure the left Talon's selected sensor to a Quad Encoder*/
		hatchLift.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, Presets.timeoutMS);
		hatchLiftSlave.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, Presets.timeoutMS);
		
		/* Configure the right Talon's selected sensor to a Quad Encoder*/
		hatchCollect.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, Presets.timeoutMS);

		/* Configure output and sensor direction */
		hatchLift.setInverted(false);
		hatchLiftSlave.setInverted(false);
		hatchCollect.setInverted(false);

		hatchLiftSlave.follow(hatchLift);
	}
    
    @Override
  	public void initDefaultCommand() {
    	setDefaultCommand(new HatchControls(Presets.deadzone));
	}

	public void periodic() {
	}

	public void setFlipper(double value) {
		hatchLift.set(ControlMode.PercentOutput, value);
	}

	public void setFlipperPosition(double value) {
		hatchLift.set(ControlMode.Position, value);
	}

	public void setCollector(double value) {
		hatchCollect.set(ControlMode.PercentOutput, value);
	}

	public void setCollectorPosition(double value) {
		hatchCollect.set(ControlMode.Position, value);
	}

	public void setCollectorVelocity(double value) {
		hatchCollect.set(ControlMode.Velocity, value);
	}

	public void hatchGrabberUp() {
		Robot.pnuematic.hatchGrabberUp();
	}

	public void hatchGrabberDown() {
		Robot.pnuematic.hatchGrabberDown();
	}

	public boolean isHatchGrabberRaised() {
		return Robot.pnuematic.isHatchGrabberRaised();
	  }
	
	  public boolean isHatchGrabberLowered() {
		return Robot.pnuematic.isHatchGrabberRaised();
	  }
}