package org.frc.team2579.commands;

import org.frc.team2579.subsystems.Intake.BallStopState;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BallStopOut extends CommandGroup {
    
    public BallStopOut() {
        addSequential(new BallStopPosition(BallStopState.OUT));
    }

}
