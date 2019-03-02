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
import frc.robot.commands.DriveControls;
import frc.robot.commands.*;

public class Hatch extends Subsystem {
    
	public final WPI_TalonSRX hatchLift = RobotMap.hatchLift;
	public final WPI_TalonSRX hatchCollect = RobotMap.hatchCollect;
	private double lift;
	private double collect;
	
	public Hatch() {
		/* Reset all motors */
		hatchLift.configFactoryDefault();
		hatchCollect.configFactoryDefault();

    	hatchLift.setSafetyEnabled(false);
		hatchCollect.setSafetyEnabled(false);
		/* Set Neutral Mode */
		hatchLift.setNeutralMode(NeutralMode.Brake);
		hatchCollect.setNeutralMode(NeutralMode.Brake);
		
		/* Configure the left Talon's selected sensor to a Quad Encoder*/
		hatchLift.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, Presets.timeoutMS);
		
		/* Configure the right Talon's selected sensor to a Quad Encoder*/
		hatchCollect.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, Presets.timeoutMS);
		
		/* Configure output and sensor direction */
		hatchLift.setInverted(false);
		hatchCollect.setInverted(false);


		/**
		 * Max out the peak output (for all modes).  
		 * However you can limit the output of a given PID object with configClosedLoopPeakOutput().
		 */
		hatchLift.configPeakOutputForward(1.0, Presets.timeoutMS);
		hatchCollect.configPeakOutputReverse(-1.0, Presets.timeoutMS);
		

	}
    
    @Override
    public void initDefaultCommand() {
    	setDefaultCommand(new HatchControls(Presets.deadzone));
	}

    public void periodic() {
		lift = hatchLift.getSelectedSensorPosition();
		collect = hatchCollect.getSelectedSensorPosition();

		/*if (Robot.networktable.table.getEntry("changeP").getDouble(P)  != P ||
		Robot.networktable.table.getEntry("changeI").getDouble(I) != I ||
		Robot.networktable.table.getEntry("changeD").getDouble(D) != D ||
		Robot.networktable.table.getEntry("changeK").getDouble(K) != K ||
		(int)Robot.networktable.table.getEntry("changeIZ").getDouble(IZ) != IZ ||
		Robot.networktable.table.getEntry("changePO").getDouble(PO) != PO ||
		(int)Robot.networktable.table.getEntry("changeCLE").getDouble(CLE) != CLE) PIDChange();*/
	}
	public void setFlipper(Integer value) {
		//NATHANIEL please fill this out with the correct integers for the fingers!!
	}
	
	public void fingerLower() {
		//NATHANIEL please fill this out with the value for "Fingers"!
	}

	public void fingerRaise() {
		//NATHANIEL please fill this out with the correct value for "Fingers"!
	}
	
	public boolean getLimitSwitchState() {
		return false;
		//Can return whatever NATHANIEL would like. NATHANIEL please fill this out with the apropos
		//NATHANIEL fill this out with the correct value!!
	}

	public void hatchExtend() {
		//NATHANIEL please add in what you please here
	}

	public void hatchRetract() {
		//NATHANIEL add in your own thing here
	}

	public void liftCollect(double lift, double collect) {
		hatchLift.set(ControlMode.PercentOutput, lift);
		hatchCollect.set(ControlMode.PercentOutput, collect);
	}

}