package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * @author Nathaniel
 */
public class Pnuematic extends Subsystem {
  
  private final Solenoid high = RobotMap.high;
  private final Solenoid low = RobotMap.low;
  private final Solenoid fingerUp = RobotMap.fingerUp;
  private final Solenoid fingerDown = RobotMap.fingerDown;
  private final Solenoid hatchExtender = RobotMap.hatchExtender;
  private final Solenoid hatchRetractor = RobotMap.hatchRetractor;
  
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
		low.set(false);
		high.set(true);
  }
  
	public void setLowGear() {
	  low.set(true);
    high.set(false); 
  }

  public boolean isFingerExtended() {
    return fingerUp.get()&&(!fingerDown.get());
  }

  public void fingerRaise(){
    fingerUp.set(true);
    fingerDown.set(false);
  }

  public void fingerLower(){
    fingerUp.set(false);
    fingerDown.set(true);
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