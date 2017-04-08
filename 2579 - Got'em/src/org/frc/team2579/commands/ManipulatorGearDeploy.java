package org.frc.team2579.commands;

import org.frc.team2579.subsystems.Manipulator;
import org.frc.team2579.subsystems.Manipulator.LiftState;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class ManipulatorGearDeploy extends CommandGroup {
    
    public ManipulatorGearDeploy() {
        addParallel(new ManipulatorSpeed(Manipulator.INTAKE_EJECT_SPEED));
        addSequential(new WaitCommand(.5));
        addSequential(new ManipulatorPosition(LiftState.DOWN));
    }

}
