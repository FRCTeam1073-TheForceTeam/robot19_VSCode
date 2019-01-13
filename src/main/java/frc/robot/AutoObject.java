package frc.robot;

/*** Used for different autonomousChooser settings
 * @author Nathaniel
 * @category Dashboard Stuff
 * @param Value (int) that will later be used to equate to a string
 */
public class AutoObject {
	private int objectValue;

	/** Takes an integer when setting and that int will correspond to a string */
	public AutoObject(int objectValue) {
		this.objectValue = objectValue;
	}

	/** Gets the position of the robot from the Chooser */
	public String getString() {
		if (objectValue == 1) return "left";
		if (objectValue == 2) return "center";
		if (objectValue == 3) return "right";
		if (objectValue == 4) return "other";
		if (objectValue == 5) return "quals";
		if (objectValue == 6) return "elims";
		if (objectValue == 7) return "experimental";
		return "not set";
	}

	/** Coopertition?? */

	public void Invert() {
		if (Robot.FMS.equals("RRR")) Robot.FMS = "LLL";
		else if (Robot.FMS.equals("RLR")) Robot.FMS = "LRL";
		else if (Robot.FMS.equals("LLL")) Robot.FMS = "RRR";
		else if (Robot.FMS.equals("LRL")) Robot.FMS = "RLR";
	}
}