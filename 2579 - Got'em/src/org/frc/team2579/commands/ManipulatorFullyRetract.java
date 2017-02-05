package org.frc.team2579.commands;

import org.frc.team2579.subsystems.Manipulator.LiftState;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ManipulatorFullyRetract extends CommandGroup
{
	public ManipulatorFullyRetract() {
        addSequential(new ManipulatorPosition(LiftState.UP));
        addSequential(new ManipulatorSpeed(0));
	}

}