/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Add your docs here.
 * Should be Called BlinkyLights but whatever
* @author Nick
 */
 import edu.wpi.first.networktables.NetworkTable;

 public class Bling {
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
                Robot.networktable.table.getEntry("Bling_Command").setString(put);
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
                        setPattern("Alternates", "TeamColors", "All", "Slow", 0, 100);
                        break;
                
                }
                
                if ( validPattern ) {
                        send();
                }

                return;

        
        }
        public void sendAdvancedTurn() {
                setPattern("Solid", "Aqua", "All", "Fast", 0, 100);
                send();
        }/*
        public void VisionerCuberTrackerer() {
                setPattern("RainbowHalves", "Red", "All", "Fast", 0, 100);
                send();
        }
        /*public void sendAutoTest() { (these don't work maybe)
                setPattern("ColorWipe", "Purple", "All", "Fast", 0, 100);
                send();
        }
        public void sendisfinished() {
                setPattern("Solid", "Green", "All", "Medium", 0, 100);
                send();
        }*/
        public void sendSystemTest() {
                setPattern("Alternates", "Blue", "All", "Medium", 0, 100);
                send();
        }
        public void sendFlipUp() {
                setPattern("Solid", "Orange", "All", "Medium", 0, 100);
                send();
        }
        public void sendFlipDown() {
               setPattern("Solid", "Yellow", "All", "Medium", 0, 100);
               send(); 
        }
        public void sendOff() {
                disableLeds();
                send();
        }
        public void sendRobotInit() {
                setPattern("RainbowHalves", "Rainbow", "All", "Medium", 0, 100);
                send();
                Robot.debugPrint("ROBOT INIT");
        }
        public void sendDefaultPattern(){
                Robot.bling.setPattern("Alternates", "TeamColors", "All", "Slow", 0, 100);
        }
        public void sendHatchGrabberDown(){
                Robot.bling.setPattern("Solid", "Purple", "All", "Medium", 0, 100);
        }
        public void sendHatchGrabberUp(){
                Robot.bling.setPattern("Solid", "Cyan", "All", "Medium", 0, 100);
        }
        public void sendHatchToMid(){
                Robot.bling.setPattern("Solid", "White", "All", "Medium", 0, 100);
        }
        public void sendAlign(){
                Robot.bling.setPattern("Solid", "Red", "All", "Medium", 0, 100);
        }
        public void sendClimberFoldUp(){
                Robot.bling.setPattern("Solid", "Green", "All", "Medium", 0, 100);
        }
}
