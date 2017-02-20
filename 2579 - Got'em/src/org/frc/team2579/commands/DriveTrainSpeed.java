package org.frc.team2579.commands;

import org.frc.team2579.Robot;
import org.frc.team2579.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class DriveTrainSpeed extends Command
{
	private double speed;
	
    public DriveTrainSpeed(double speed) {
        requires(Robot.driveTrain);
        this.speed = speed;
    }

    protected void initialize() {
    	DriveTrain.slowMode = speed;
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	
    }
}