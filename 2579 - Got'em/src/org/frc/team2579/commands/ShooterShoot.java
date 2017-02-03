package org.frc.team2579.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class ShooterShoot extends CommandGroup {

	public ShooterShoot(boolean checkForValidTarget) {
		addSequential(new DriveTrainHold(true));
		addSequential(new WaitCommand(0.1));
		addSequential(new ShooterShootWithCheck(checkForValidTarget));
		addSequential(new WaitCommand(0.1));
		addSequential(new DriveTrainHold(false));
	}
}
