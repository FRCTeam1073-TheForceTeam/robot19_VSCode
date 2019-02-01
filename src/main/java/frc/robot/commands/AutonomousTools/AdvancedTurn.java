package frc.robot.commands.AutonomousTools;

import frc.robot.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Command;

/*** Gyro based turn command 
 * @author Nathaniel 
 */
public class AdvancedTurn extends Command {
	
	/** Percent for near end */
	private double slowDegrees = 37;
	
	/** Near end running speed */
	private double slowSpeed = 0.65;
	
	/** Normal running speed */
	private double regularSpeed = 1;
	
	/** Current gyro value */
	private double currentDegrees;
	
	/** Initial gyro value */
	private double initialDegrees;
	
	/** Requested gyro value */
	private double finalDegrees = -1;
	
	/** Direction of driving (can also set to point based driving) */
	private String direction = "empty";
	
	private boolean point = false;
	
	/** Timeout values */
	private double timeStart, timeout;
	
	/** If TurnToPoint and TurnWithGyro had a kid that they sent to prepschool
	 * @author Nathaniel
	 * @param degrees requested degrees
	 * Note: must be positive
	 * @param direction "left", "right", or "point"
	 * Note: will not run if string is incorrect or misspelled
	 * @param timeout in milliseconds
	 * Note: timeout should be slightly larger than the assumed runtime
	 * and will finish early if less than the time taken to travel the requested distances
	 * @category Drive Command
	 */
	public AdvancedTurn(double finalDegrees, String direction, double timeout) {
		if (0 <= Math.abs(finalDegrees) && 
			Math.abs(finalDegrees) <= 359) this.finalDegrees = Math.abs(finalDegrees);
		if (Math.abs(finalDegrees) == 360) this.finalDegrees = 0;
		if (this.finalDegrees == -1) System.out.print("WARN: robot19.commands.AutonomousTools.AdvancedTurn says:\n"
				+ "\"Invalid input!\n"
				+ "Must be: 0 to 359\n"
				+ "Your input: " + finalDegrees + "\"");
		
		if (direction.equals("right") == true ||
			direction.equals("left") == true ||
			direction.equals("point") == true) this.direction = direction;
		if (this.direction.equals("empty")) System.out.print("WARN: robot19.commands.AutonomousTools.AdvancedTurn says:\n"
				+ "\"Invalid direction!\n"
				+ "Must be: \"right\", \"left\", or \"point\"\n"
				+ "Your input: " + direction + "\"");
		
		if (timeout != 0) this.timeout = timeout;
		else this.timeout = 214748364.9999999;
	}
	
	protected void initialize() {
		initialDegrees = RobotMap.headingGyro.getAngle();
		System.out.println("LOG: robot19.commands.AutonomousTools.AdvancedTurn says:\n"
				+ "\"Initial Gyro point: " + initialDegrees + "\"");
		
		if (direction.equals("point")) direction = turnPoint();
		
		/* Grabs a start time for timeout */
		timeStart = System.currentTimeMillis();
	}
	
	private String turnPoint() {
		point = true;
		System.out.println("LOG: robot19.commands.AutonomousTools.AdvancedTurn says:\n"
				+ "\"turnPoint: Point " + initialDegrees 
				+ "\t Math " + (finalDegrees - initialDegrees % 360) + "\"");
		if (finalDegrees - initialDegrees % 360 > 0) return "right";
		if (finalDegrees - initialDegrees % 360 < 0) return "left";
		return "empty";
	}

	protected void execute() {
		currentDegrees = RobotMap.headingGyro.getAngle();
		RobotMap.leftMaster.set(ControlMode.PercentOutput, turnCheck("left") * turnSpeed());
		RobotMap.rightMaster.set(ControlMode.PercentOutput, turnCheck("right") * turnSpeed());
	}
	
	private double turnCheck(String string) {
		if (string.equals(direction)) return 1;
		return -1;
	}
	
	private double turnSpeed() {
		if (Math.abs(currentDegrees - initialDegrees) + slowDegrees >= finalDegrees) return slowSpeed;
		return regularSpeed;
	}

	protected boolean isFinished() {
		if (Robot.oi.driverCancel.get() == true || Robot.oi.operatorCancel.get() == true) return true;
		if ((point && Math.abs(finalDegrees - currentDegrees % 360) <= 0) || 
			(!point && Math.abs(currentDegrees - initialDegrees) >= finalDegrees) || 
			System.currentTimeMillis() - timeStart >= timeout ||
			direction.equals("empty")) return true;
		return false;
	}
	
	protected void end() {
		System.out.println("LOG: robot19.commands.AutonomousTools.AdvancedTurn says:\n"
				+ "\"Final Gyro point: " + currentDegrees + "\"");
	}
}
		