package org.frc.team2579.commands;

import edu.rhhs.frc.subsystems.Intake.LiftState;
import edu.rhhs.frc.subsystems.Manipulator.Attachment;
import edu.rhhs.frc.subsystems.Manipulator.PresetPositions;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class IntakeHelperRetract extends CommandGroup {

	public IntakeHelperRetract() {
		addSequential(new IntakeInnerPosition(LiftState.UP));
		addSequential(new IntakeOuterPosition(LiftState.UP));
		addParallel(new ManipulatorMoveMP(PresetPositions.ZERO,
				Attachment.CHEVAL_DE_FRISE));
		addSequential(new IntakeOuterSpeed(0));
		addSequential(new IntakeInnerSpeed(0));
		// addSequential(new WaitCommand(0.8));
		addSequential(new IntakeInnerPosition(LiftState.DOWN));
	}
}
