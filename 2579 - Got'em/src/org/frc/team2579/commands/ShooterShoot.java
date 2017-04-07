package org.frc.team2579.commands;

import org.frc.team2579.Robot;
import org.frc.team2579.subsystems.Shooter;
import org.frc.team2579.subsystems.Shooter.ShooterControlMode;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShooterShoot extends CommandGroup {
    
    public ShooterShoot() {
        //addSequential(new ShooterMode(ShooterControlMode.SENSORED));
        //addSequential(new BallStopIn());
        //addParallel(new ShooterSpeed(Shooter.BOILER_RPM_SETPOINT));
        addParallel(new ShooterPID(ShooterControlMode.SENSORED,Shooter.BOILER_RPM_SETPOINT));
        //addParallel(new FeedShooter());
        //addSequential(new ShooterSpeed(0));
        }

}
