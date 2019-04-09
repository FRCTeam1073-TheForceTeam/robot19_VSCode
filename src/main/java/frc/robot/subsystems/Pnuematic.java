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
  private final Solenoid hatchRaiser = RobotMap.hatchRaiser;
  private final Solenoid hatchLowerer = RobotMap.hatchLowerer;
  
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

  public boolean isHatchGrabberRaised() {
    if (hatchRaiser.get() && !hatchLowerer.get()) return true;
    return false;
  }

  public boolean isHatchGrabberLowered() {
    if (!hatchRaiser.get() && hatchLowerer.get()) return true;
    return false;
  }

  public void hatchGrabberUp() {
    hatchRaiser.set(true);
    hatchLowerer.set(false);
  }
  
  public void hatchGrabberDown() {
    hatchRaiser.set(false);
    hatchLowerer.set(true);
  }
}