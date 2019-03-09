/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.LiDAR;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.LiDAR.*;
import frc.robot.commands.*;
import frc.robot.commands.Autonomous.*;
import frc.robot.commands.AutonomousTools.*;
import frc.robot.commands.AutonomousTools.AdvancedTurn;
import frc.robot.subsystems.*;

/**
 * Controls for LiDAR Localization/GPS
 * @author AlexHill and CamH
 * @param radius uses metric system
 */
public class LidarDrive extends Command {
    public static double initialLatitude = Robot.networktable.table.getEntry("XCoord").getDouble(0);
	public static double initialLongitude = Robot.networktable.table.getEntry("YCoord").getDouble(0);
	public static double speed = Robot.networktable.table.getEntry("robotVelocity").getDouble(0); // ~0.05m - 0.1m per step
	public static double radius = 18401.945/2;  // 9200.9725 mm (feild dimentions the GPS is following)
    private String name = SmartDashboard.getString("Waypoint Name", "test");
	private double xCoord = SmartDashboard.getNumber("X", 0);
    private double yCoord = SmartDashboard.getNumber("Y", 0);
    private double initGyro;
    private double turnDegrees;
    private double driveDistance;
    private double currentHeading;
    private String driveDirection = "forward";
    private String turnDirection;
	public Location currentLocation = new Location("currentLocation", initialLatitude, initialLongitude);
    public int waypointCounter = 0;
    public Route destination;
    
    
	public LidarDrive(String name, double xCoord, double yCoord) {
        this.name = name;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        destination = new Route(new Location(name, xCoord, yCoord)); //makes a new "route" based on initial and final values
    }
    

    protected void initialize(){
        initGyro = Math.toRadians(RobotMap.headingGyro.getAngle());
        turnDegrees = Math.atan((xCoord-initialLatitude)/(yCoord-initialLongitude));
        driveDistance = Math.hypot((xCoord-initialLatitude),(yCoord-initialLongitude));
        if((initGyro%360) - turnDegrees <= 0){
            turnDirection = "left";

        }
        else if((initGyro%360) - turnDegrees >= 0){
            turnDirection = "right";
        }
        else{
            turnDirection = "point";
        }        
    }
    protected void execute() {
        turnDegrees = Math.atan((xCoord-initialLatitude)/(yCoord-initialLongitude));
        currentHeading = RobotMap.headingGyro.getAngle();
        driveDistance = Math.hypot((xCoord-initialLatitude),(yCoord-initialLongitude)); 

        //continually getting its location and using it to calculate location
        //turning to the point
        if( turnDegrees - Math.abs(currentHeading) <= 178){
            turnDirection = "left";
            Robot.drivetrain.rightMaster.set(ControlMode.PercentOutput, 0.3);
            Robot.drivetrain.leftMaster.set(ControlMode.PercentOutput, -0.3);
            System.out.println("turning left");
            System.out.println(currentHeading);

        }
        else if(turnDegrees - Math.abs(currentHeading) >= 182){
            turnDirection = "right";
            Robot.drivetrain.rightMaster.set(ControlMode.PercentOutput, -0.3);
            Robot.drivetrain.leftMaster.set(ControlMode.PercentOutput, 0.3);
            System.out.println("turning right");
            System.out.println(currentHeading);
        }
        else{
            turnDirection = "straight";
            if(driveDistance <=110 || driveDistance >=110){
                Robot.drivetrain.rightMaster.set(ControlMode.PercentOutput, 0.3);
                Robot.drivetrain.leftMaster.set(ControlMode.PercentOutput, 0.3);
                
                System.out.println("straight");
                System.out.println(driveDistance);
            }

        }        
        
            
    }

    protected boolean isFinished() {

        //gives a margain of error of about 10 cm in either direction, to be tuned later
        return false;
    }

    protected void end() {
        super.end();
    }
}
