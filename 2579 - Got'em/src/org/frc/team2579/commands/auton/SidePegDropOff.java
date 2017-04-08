package org.frc.team2579.commands.auton;

import org.frc.team2579.commands.DriveSideForward;
import org.frc.team2579.commands.ManipulatorPosition;
import org.frc.team2579.commands.ManipulatorSpeed;
import org.frc.team2579.subsystems.Manipulator;
import org.frc.team2579.subsystems.Manipulator.LiftState;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class SidePegDropOff extends CommandGroup {
    
    public SidePegDropOff() {
    	addSequential(new DriveSideForward());
    	System.out.println("DONE DRIVING");
    	addSequential(new WaitCommand(1));
    	System.out.println("Waiting");
    	addSequential(new ManipulatorSpeed(Manipulator.INTAKE_EJECT_SPEED));
    	addSequential(new WaitCommand(1));
    	System.out.println("Ejecting");
        addSequential(new ManipulatorPosition(LiftState.DOWN));
        addSequential(new WaitCommand(1));
        addSequential(new ManipulatorSpeed(0));
        addSequential(new DriveBackwardMP());
    }

}
