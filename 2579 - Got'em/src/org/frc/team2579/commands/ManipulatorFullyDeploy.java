package org.frc.team2579.commands;

import org.frc.team2579.subsystems.Manipulator;
import org.frc.team2579.subsystems.Manipulator.LiftState;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ManipulatorFullyDeploy extends CommandGroup {
    
    public ManipulatorFullyDeploy() {
        addSequential(new ManipulatorPosition(LiftState.DOWN));
        addSequential(new ManipulatorSpeed(Manipulator.INTAKE_LOAD_SPEED));
    }}
