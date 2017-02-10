package org.frc.team2579.commands;

import org.frc.team2579.subsystems.Climber;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ClimbUp extends CommandGroup {
    
    public ClimbUp() {
        addSequential(new ClimberSpeed(Climber.CLIMB_SPEED));
    }
}
