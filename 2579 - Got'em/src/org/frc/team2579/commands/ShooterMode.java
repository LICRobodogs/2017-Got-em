package org.frc.team2579.commands;

import org.frc.team2579.Robot;
import org.frc.team2579.subsystems.Shooter.ShooterControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterMode extends Command{
private ShooterControlMode state;
	
	public ShooterMode(ShooterControlMode state) {
		requires(Robot.manipulator);
		this.state = state;
	}

	@Override
	protected void initialize() {
		Robot.shooter.setMode(state);
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
