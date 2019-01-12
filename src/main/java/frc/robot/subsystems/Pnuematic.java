package frc.robot.subsystems;
 
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.Compress;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Pnuematic extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  private final Compressor compressor = RobotMap.compressor;
	private final Solenoid high = RobotMap.high;
  private final Solenoid low = RobotMap.low;
  
  public Pnuematic() {
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new Compress());
  }

  public void highGear() {
		low.set(false);
		high.set(true);
  }
  
	public void lowGear() {
	  low.set(true);
    high.set(false);
    
	}
}
