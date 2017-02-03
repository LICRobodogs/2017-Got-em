package org.frc.team2579.commands;

import edu.rhhs.frc.subsystems.Intake;
import edu.rhhs.frc.subsystems.Intake.LiftState;
import edu.rhhs.frc.subsystems.Manipulator.Attachment;
import edu.rhhs.frc.subsystems.Manipulator.PresetPositions;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class IntakeFullyDeploy extends CommandGroup {

	public IntakeFullyDeploy() {
		addSequential(new IntakeOuterPosition(LiftState.DOWN));
		addSequential(new IntakeInnerPosition(LiftState.UP));
		addParallel(new ManipulatorMoveMP(PresetPositions.ZERO,
				Attachment.CHEVAL_DE_FRISE));
		addSequential(new IntakeOuterSpeed(Intake.OUTER_INTAKE_LOAD_SPEED));
		addSequential(new IntakeInnerSpeed(Intake.INNER_INTAKE_LOAD_SPEED));
	}
}
