/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.LiDAR;

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
 * Controls for LiDAR Localization
 * @author AlexHill and CamH
 */
public class LidarDrive extends Command {
    public static final double initialLatitude = Robot.networktable.table.getEntry("XCoord").getDouble(0);
	public static final double initialLongitude = Robot.networktable.table.getEntry("YCoord").getDouble(0);
	public static final double speed = Robot.networktable.table.getEntry("robotVelocity").getDouble(0); // ~0.05m - 0.1m per step
	public static final double radius = 18401.945/2;  // 9200.9725 mm
    private String name = SmartDashboard.getString("Waypoint Name", "test");
	private double xCoord = SmartDashboard.getNumber("X", 0);
    private double yCoord = SmartDashboard.getNumber("Y", 0);
    private double initGyro;
    private double turnDegrees;
    private double driveDistance;
    private String driveDirection = "forward";
    private String turnDirection;
	public Location currentLocation = new Location("currentLocation", initialLatitude, initialLongitude);
    public int waypointCounter = 0;
    public Route destination;

    
	public LidarDrive(String name, double xCoord, double yCoord) {
        this.name = name;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        destination = new Route(new Location(name, xCoord, yCoord));
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

        //continually getting its location and using it to calculate location
        initialLatitude = Robot.networktable.table.getEntry("XCoord").getDouble(0);
        initialLongitude = Robot.networktable.table.getEntry("YCoord").getDouble(0);
        initGyro = Math.toRadians(RobotMap.headingGyro.getAngle());
        turnDegrees = Math.atan((xCoord-initialLatitude)/(yCoord-initialLongitude));
        driveDistance = Math.hypot((xCoord-initialLatitude),(yCoord-initialLongitude));

        //determining turn direction
        if((initGyro%360) - turnDegrees <= 0){
            turnDirection = "left";

        }
        else if((initGyro%360) - turnDegrees >= 0){
            turnDirection = "right";
        }
        else{
            turnDirection = "point";
        }        

        AdvancedTurn(turnDegrees,turnDirection,5000);
        AdvancederDrive(driveDistance, driveDirection, 5000);


    }

    protected boolean isFinished() {

        //gives a margain of error of about 10 cm in either direction, to be tuned later
        if(driveDistance <=100 || driveDistance >=100){
            return true;
        }
        else{
        return false;
        }
    }

    protected void end() {
        super.end();
    }
}
