package org.frc.team2579.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ClimbOff extends CommandGroup {
    
    public ClimbOff() {
        addSequential(new ClimberSpeed(0));
    }
}
