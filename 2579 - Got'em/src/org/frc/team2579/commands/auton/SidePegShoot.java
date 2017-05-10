package org.frc.team2579.commands.auton;

import org.frc.team2579.commands.DriveSideForward;
import org.frc.team2579.commands.ManipulatorPosition;
import org.frc.team2579.commands.ManipulatorSpeed;
import org.frc.team2579.subsystems.Manipulator;
import org.frc.team2579.subsystems.Shooter;
import org.frc.team2579.subsystems.Manipulator.LiftState;
import org.frc.team2579.subsystems.Shooter.ShooterControlMode;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class SidePegShoot extends CommandGroup {
	public SidePegShoot(){
		addSequential(new ManipulatorPosition(Manipulator.LiftState.UP));
		addSequential(new WaitCommand(.5));
		addSequential(new ShootAuton(ShooterControlMode.SENSORED,Shooter.BOILER_RPM_SETPOINT));
		addSequential(new DriveSideForward());
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
