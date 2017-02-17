package org.frc.team2579.commands;

import org.frc.team2579.Robot;
import org.frc.team2579.subsystems.Shooter.ShooterControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterPID extends Command
{
	private ShooterControlMode mode;
	private double speed;
	public ShooterPID(ShooterControlMode mode, double speed) {
		requires(Robot.shooter);
		this.mode = mode;
		this.speed = speed;
	}

	@Override
	protected void initialize() {
		Robot.shooter.setWheelSpeed(mode, speed);
	}

	@Override
	protected void execute() {
		
	}

	@Override
	protected boolean isFinished() {
		//return false;
		return Robot.shooter.isOnTarget();
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
			
	}

}