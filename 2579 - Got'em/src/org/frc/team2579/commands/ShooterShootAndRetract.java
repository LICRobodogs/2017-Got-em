package org.frc.team2579.commands;

import edu.rhhs.frc.subsystems.Manipulator.Attachment;
import edu.rhhs.frc.subsystems.Manipulator.PresetPositions;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShooterShootAndRetract extends CommandGroup {

	public ShooterShootAndRetract(boolean checkForValidTarget) {
		addSequential(new ShooterShoot(checkForValidTarget));
		addParallel(new ManipulatorMoveMP(PresetPositions.ZERO,
				Attachment.CHEVAL_DE_FRISE));
		addSequential(new ShooterWinchRetractAndSpoolOut());
	}
}
