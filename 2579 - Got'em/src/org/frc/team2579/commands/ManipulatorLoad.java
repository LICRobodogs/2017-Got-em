package org.frc.team2579.commands;

import org.frc.team2579.subsystems.Manipulator;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ManipulatorLoad extends CommandGroup {
    
    public ManipulatorLoad() {
        addSequential(new ManipulatorSpeed(Manipulator.INTAKE_LOAD_SPEED));
    }
}
