package org.frc.team2579.commands;

import org.frc.team2579.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterSpeed extends Command
{
	private double speed;
	
	public ShooterSpeed(double speed) {
		requires(Robot.shooter);
		this.speed = speed;
	}

	@Override
	protected void initialize() {
		Robot.shooter.mSpeed = speed;
	}

	@Override
	protected void execute() {
		
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
			
	}

}
