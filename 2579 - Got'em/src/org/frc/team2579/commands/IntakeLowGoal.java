package org.frc.team2579.commands;

import edu.rhhs.frc.subsystems.Intake;
import edu.rhhs.frc.subsystems.Intake.LiftState;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class IntakeLowGoal extends CommandGroup {
	public IntakeLowGoal() {
		addSequential(new IntakeOuterPosition(LiftState.DOWN));
		addSequential(new IntakeInnerPosition(LiftState.UP));
		addSequential(new IntakeOuterSpeed(Intake.OUTER_INTAKE_LOAD_SPEED));
	}
}