/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.LiDAR;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.LiDAR.*;

/**
 * Controls for LiDAR Localization
 * @author AlexHill and CamH
 */
public class CoordDrive extends Command {

	private String name;
	private double xCoord;
	private double yCoord;
    public CoordDrive(String name, double xCoord, double yCoord){
        this.name = name;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        
        new Location(name, xCoord, yCoord);
    }

    
}
