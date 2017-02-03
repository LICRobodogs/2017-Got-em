package org.frc.team2579.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class IntakeOff extends CommandGroup {

	public IntakeOff() {
		addSequential(new IntakeOuterSpeed(0.0));
		addSequential(new IntakeInnerSpeed(0.0));
	}
}
