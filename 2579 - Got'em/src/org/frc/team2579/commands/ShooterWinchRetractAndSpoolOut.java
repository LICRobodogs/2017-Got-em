package org.frc.team2579.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShooterWinchRetractAndSpoolOut extends CommandGroup {

	public ShooterWinchRetractAndSpoolOut() {
		addSequential(new ShooterWinchRetract());
		addParallel(new ShooterWinchSpoolOut());
	}
}
