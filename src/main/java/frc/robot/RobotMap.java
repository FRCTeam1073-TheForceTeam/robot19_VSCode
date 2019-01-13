package frc.robot;

import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.*;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  
  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
  
	public static PowerDistributionPanel pdp;
	public static AnalogGyro headingGyro;
	public static WPI_TalonSRX rightMotor1;
	public static WPI_VictorSPX rightMotor2;
	public static WPI_TalonSRX leftMotor1;
	public static WPI_VictorSPX leftMotor2;
	public static Compressor compressor;
	public static Solenoid high;
	public static Solenoid low;
	
	public static void init() {
		headingGyro = new AnalogGyro(0);

		rightMotor2 = new WPI_VictorSPX(2);
		rightMotor1 = new WPI_TalonSRX(3);
		leftMotor2 = new WPI_VictorSPX(9);
		leftMotor1 = new WPI_TalonSRX(8);

		compressor = new Compressor();
		high = new Solenoid (1, 0);
		low = new Solenoid (1, 1);
	}
}