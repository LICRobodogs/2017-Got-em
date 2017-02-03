package org.frc.team2579.commands;

import edu.rhhs.frc.subsystems.Intake.LiftState;
import edu.rhhs.frc.subsystems.Manipulator.Attachment;
import edu.rhhs.frc.subsystems.Manipulator.PresetPositions;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class IntakeFullyRetract extends CommandGroup {

	public IntakeFullyRetract() {
		addSequential(new IntakeOuterPosition(LiftState.UP));
		addSequential(new IntakeInnerPosition(LiftState.DOWN));
		addParallel(new ManipulatorMoveMP(PresetPositions.ZERO,
				Attachment.CHEVAL_DE_FRISE));
		addSequential(new IntakeOuterSpeed(0));
		addSequential(new IntakeInnerSpeed(0));
	}
}
