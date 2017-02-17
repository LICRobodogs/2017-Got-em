package org.frc.team2579.commands;

import org.frc.team2579.subsystems.Shooter;
import org.frc.team2579.subsystems.Shooter.ShooterControlMode;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShooterHold extends CommandGroup {
    
    public ShooterHold() {
        //addSequential(new ShooterMode(ShooterControlMode.SENSORED));
        addSequential(new BallStopOut());
        addSequential(new ShooterSpeed(0));
        //addParallel(new ShooterPID(ShooterControlMode.SENSORED,Shooter.BOILER_RPM_SETPOINT));
        //addSequential(new IntakeInnerSpeed(0));
        addSequential(new ShooterMode(ShooterControlMode.MANUAL));
        //addSequential(new ShooterSpeed(0));
        }

}
