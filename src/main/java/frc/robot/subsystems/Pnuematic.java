package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Pnuematic extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
	private final Solenoid high = RobotMap.high;
  private final Solenoid low = RobotMap.low;
  private final Solenoid finger = RobotMap.finger;
  private final Solenoid hatchExtender = RobotMap.hatchExtender;
  private final Solenoid hatchRetractor = RobotMap.hatchRetractor;
  private final boolean isShiftingOn=false;
  @Override
  public void initDefaultCommand() {
  }

  public boolean isHighGear() {
    if (!low.get() && high.get()) return true;
    return false;
  }

  public boolean isLowGear() {
    if (low.get() && !high.get()) return true;
    return false;
  }

  public void setHighGear() {
    if(isShiftingOn){
      low.set(false);
      high.set(true);
    }
  }
  
	public void setLowGear() {
    if(isShiftingOn){
  	  low.set(true);
      high.set(false);
    }
  }

  public boolean isFingerExtended() {
    return finger.get();
  }

  public void fingerRaise(){
    finger.set(true);
  }

  public void fingerLower(){
    finger.set(false);
  }

  public boolean isHatchExtended(){
    if (hatchExtender.get() && !hatchRetractor.get()) return true;
    return false;
  }

  public void hatchExtend() {
    hatchExtender.set(true);
    hatchRetractor.set(false);
  }
  
  public void hatchRetract(){
    hatchExtender.set(false);
    hatchRetractor.set(true);
  }
}