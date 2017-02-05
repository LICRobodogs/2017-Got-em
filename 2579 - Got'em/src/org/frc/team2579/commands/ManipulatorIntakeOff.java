package org.frc.team2579.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;


public class ManipulatorIntakeOff extends CommandGroup{
	public ManipulatorIntakeOff() {
        addSequential(new ManipulatorSpeed(0.0));
    }
}
