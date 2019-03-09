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
 * Controls for LiDAR Localization/GPS
 * @author AlexHill and CamH
 */

public class Location {

    public String geoLoca;
    
    public double latitude;

    public double longitude;

    public Location(String geoLoca, double latitude, double longitude) {
            this.geoLoca = geoLoca;
            this.latitude = latitude;
            this.longitude = longitude;
    }
}
