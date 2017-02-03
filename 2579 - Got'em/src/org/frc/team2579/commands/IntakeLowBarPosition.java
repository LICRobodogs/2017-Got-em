package org.frc.team2579.commands;

import edu.rhhs.frc.subsystems.Intake.LiftState;
import edu.rhhs.frc.subsystems.Manipulator.Attachment;
import edu.rhhs.frc.subsystems.Manipulator.PresetPositions;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class IntakeLowBarPosition extends CommandGroup {

	public IntakeLowBarPosition() {
		addSequential(new IntakeOuterPosition(LiftState.DOWN));
		addSequential(new IntakeInnerPosition(LiftState.DOWN));
		addSequential(new ManipulatorMoveMP(PresetPositions.RETRACTED,
				Attachment.CHEVAL_DE_FRISE));
		addSequential(new IntakeOuterSpeed(0));
		addSequential(new IntakeInnerSpeed(0));
	}
}
