package org.frc.team2579.commands;

import org.frc.team2579.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class IntakeOuterSpeed extends Command
{
	private double speed;
	
	public IntakeOuterSpeed(double speed) {
		requires(Robot.intake);
		this.speed = speed;
	}

	@Override
	protected void initialize() {
		Robot.intake.setSpeedOuter(speed);
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