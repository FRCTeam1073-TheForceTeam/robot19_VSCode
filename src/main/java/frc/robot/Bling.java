/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Add your docs here.
 */
 import edu.wpi.first.networktables.NetworkTable;

 public class Bling {
        NetworkTable newtable;


        private String color;
        private String speed;
        private int min;
        private int max;
        private String pattern;
        private String segment;
        private String put;
        private String put2;

        public String setPattern(String patt,  String col, String seg, String spd, int mini, int maxi) {
                pattern = patt;
                color = col;
                segment = seg;
                speed = spd;
                min = mini;
                max = maxi;
                put = "Pattern=" + pattern + "," + "Color=" + color + "," + "Segment=" + segment + "," + "Speed=" + speed + "," + "Min=" + min + "," + "Max=" + max;
                return put;
        }
        
        public void disableLeds() {
                put = "Pattern=" + "off" + "," + "Color=" + color + "," + "Segment=" + segment + "," + "Speed=" + speed + "," + "Min=" + min + "," + "Max=" + max;
        }
        
        public void send() {
                newtable.getEntry("Bling_Command").setString(put);
        }

        public void setPattern( BlingMode pattern ) {
                Boolean validPattern = true;
                
                switch( pattern ) {
                case ROBOT_INT:
                        setPattern("RainbowHalves", "Rainbow", "All", "Medium", 0, 100);
                        break;
                case ROBOT_ERROR:
                        setPattern("Blinking", "Error", "All", "Fast", 0, 100);
                        break;
                case OFF:
                        disableLeds();
                        break;

                default:
                        setPattern("Alternates", "Blue", "All", "Slow", 0, 100);
                        break;
                
                }
                
                if ( validPattern ) {
                        send();
                }

                return;

        
        }
}
