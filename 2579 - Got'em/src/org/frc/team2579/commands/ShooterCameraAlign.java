package org.frc.team2579.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShooterCameraAlign extends CommandGroup {

	public ShooterCameraAlign() {
		addSequential(new CameraAlignmentState(false));
		addSequential(new CameraTurnToBestTarget());
		addSequential(new CameraTurnToBestTarget());
		addSequential(new CameraAlignmentState(true));
	}
}
