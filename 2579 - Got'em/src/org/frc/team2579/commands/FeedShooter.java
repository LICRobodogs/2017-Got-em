package org.frc.team2579.commands;

import org.frc.team2579.Robot;
import org.frc.team2579.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

public class FeedShooter  extends Command
{
	public FeedShooter() {
		requires(Robot.intake);
	}

	@Override
	protected void initialize() {
		Robot.intake.feedWithCheck();
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
