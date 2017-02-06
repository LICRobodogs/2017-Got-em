package org.frc.team2579.commands;

import org.frc.team2579.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class IntakeInnerSpeed extends Command
{
	private double speed;
	
	public IntakeInnerSpeed(double speed) {
		requires(Robot.intake);
		this.speed = speed;
	}

	@Override
	protected void initialize() {
		Robot.intake.setSpeedInner(speed);
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