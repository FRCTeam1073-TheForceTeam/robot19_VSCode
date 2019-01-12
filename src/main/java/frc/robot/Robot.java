/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.AutonomousTools.*;
import frc.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
	public double initialBootTime, teleopStartTime, autoStartTime;
	public static OI oi;
	public static Drivetrain drivetrain;
	public static Pnuematic pnuematic;
	public static GearBox gearbox;
	public static Vision vision;
	public static String FMS;
	public static SendableChooser<AutoObject> autonomousPosition, autonomousMatchType;
	public AutoObject left, center, right, other, quals, elims, experimental;
	public static boolean clawBool, EncoderBool, EncoderBoolSet, notClear;
	public static boolean selectedCamera;
	public static NetworkTableInstance netTableInst;
	public static edu.wpi.first.networktables.NetworkTable lidarSendTable;
  	Command autonomousCommand;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    	System.out.println("Robot Initializing");
		
		RobotMap.init();

		RobotMap.headingGyro.reset();
		RobotMap.headingGyro.calibrate();

		initialBootTime = System.currentTimeMillis();
		netTableInst = NetworkTableInstance.getDefault();
		lidarSendTable = netTableInst.getTable("LidarSendTable");
		
		drivetrain = new Drivetrain();

		pnuematic = new Pnuematic();

		gearbox = new GearBox();

		vision = new Vision();

		oi = new OI();

		FMS = "";

		/* Position Objects */
		left = new AutoObject(1);
		center = new AutoObject(2);
		right = new AutoObject(3);
		other = new AutoObject(4);
		quals = new AutoObject(5);
		elims = new AutoObject(6);
		experimental = new AutoObject(7);
		
		/* The Position Chooser */
		autonomousPosition = new SendableChooser<AutoObject>();
		autonomousPosition.setDefaultOption("None", other);
		autonomousPosition.addOption("Left", left);
		autonomousPosition.addOption("Center", center);
		autonomousPosition.addOption("Right", right);
		SmartDashboard.putData("Position", autonomousPosition);

		/* The MatchType Chooser */
		autonomousMatchType = new SendableChooser<AutoObject>();
		autonomousMatchType.setDefaultOption("None", other);
		autonomousMatchType.addOption("Qualifications", quals);
		autonomousMatchType.addOption("Eliminations", elims);
		autonomousMatchType.addOption("Experimental", experimental);
		SmartDashboard.putData("Match Type", autonomousMatchType);
		
		autonomousCommand = new AutoTest();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }
  
  /**
   * This function is called when the disabled button is hit.
   * You can use it to reset subsystems before shutting down.
   */
	public void disabledInit(){
		System.out.println("At " + ((System.currentTimeMillis() - initialBootTime) * 1000) + ", robot19.robot says \n"
				+ "\" WE ARE DISABLED WHAT THE HECK?\"");
		
		System.out.println(RobotMap.headingGyro.getAngle());
		
		vision.init();
		lidarSendTable = netTableInst.getTable("LidarSendTable");
		
		vision.refresh();
		
		Robot.oi.driverControl.rumbleTimeRep(1, 250, 2);
		Robot.oi.driverControl.rumbleTimeRep(.2, 250, 2);
		Robot.oi.driverControl.rumbleTimeRep(1, 250, 2);
		Robot.oi.driverControl.rumbleTimeRep(.2, 250, 2);
	}

	public void disabledPeriodic() {
		vision.init();
		lidarSendTable = netTableInst.getTable("LidarSendTable");
		
		vision.refresh();
	}

	public void autonomousInit() {
		autoStartTime = System.currentTimeMillis();
		
		vision.init();
		lidarSendTable = netTableInst.getTable("LidarSendTable");
		
		vision.refresh();
		
		System.out.println("Auto Setting Up");
		
		RobotMap.headingGyro.reset();

		FMS = DriverStation.getInstance().getGameSpecificMessage();
		SmartDashboard.putString("FMS", FMS);
		System.out.println("FMS: " + FMS);

		Scheduler.getInstance().run();
		
		Robot.notClear = lidarSendTable.getEntry("Stop").getBoolean(false);
		
		System.out.println("Auto Starting");
		if (autonomousCommand != null) autonomousCommand.start();
	}

	/** This function is called periodically during autonomous */
	public void autonomousPeriodic() {
		lidarSendTable = netTableInst.getTable("LidarSendTable");
		
		vision.refresh();
		
		Scheduler.getInstance().run();
		Robot.notClear = lidarSendTable.getEntry("Stop").getBoolean(false);
	}

	public void teleopInit() {
		teleopStartTime = System.currentTimeMillis();
		
		vision.init();
		lidarSendTable = netTableInst.getTable("LidarSendTable");
		
		if (autonomousCommand != null) autonomousCommand.cancel();
		
		FMS = DriverStation.getInstance().getGameSpecificMessage();
		SmartDashboard.putString("FMS", FMS);
		System.out.println("FMS: " + FMS);
	}
	
	/** This function is called periodically during operator control */
	public void teleopPeriodic() {
		lidarSendTable = netTableInst.getTable("LidarSendTable");
		
		vision.refresh();
		
		Scheduler.getInstance().run();
	}

	/** This function is called periodically during test mode */
	public void testPeriodic() {
		System.out.println("Test Mode.");
		vision.refresh();
	}
}
