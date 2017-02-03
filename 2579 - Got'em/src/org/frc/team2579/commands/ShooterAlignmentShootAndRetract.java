package org.frc.team2579.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class ShooterAlignmentShootAndRetract extends CommandGroup {

	public ShooterAlignmentShootAndRetract() {
		addSequential(new CameraTurnToBestTarget());
		addSequential(new CameraTurnToBestTarget());
		addSequential(new WaitCommand(0.2));
		addSequential(new ShooterShoot(true));
		// addParallel(new ShooterWinchRetractAndSpoolOut());
	}
}
