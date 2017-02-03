package org.frc.team2579.commands;

import edu.rhhs.frc.subsystems.Intake;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class IntakeEject extends CommandGroup {

	public IntakeEject() {
		addSequential(new IntakeOuterSpeed(Intake.OUTER_INTAKE_EJECT_SPEED));
		addSequential(new IntakeInnerSpeed(Intake.INNER_INTAKE_EJECT_SPEED));
	}
}
