package org.frc.team2579.commands;

import org.frc.team2579.subsystems.Intake.BallStopState;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BallStopIn extends CommandGroup {
    
    public BallStopIn() {
        addSequential(new BallStopPosition(BallStopState.IN));
    }

}
