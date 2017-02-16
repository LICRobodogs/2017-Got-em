package org.frc.team2579.commands;

import org.frc.team2579.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterPID extends Command
{
	public ShooterPID() {
		requires(Robot.shooter);
	}

	@Override
	protected void initialize() {
		Robot.shooter.shootWithFeedBack();
	}

	@Override
	protected void execute() {
		
	}

	@Override
	protected boolean isFinished() {
		return Robot.shooter.isOnTarget();
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
			
	}

}