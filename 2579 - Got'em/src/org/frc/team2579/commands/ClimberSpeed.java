package org.frc.team2579.commands;

import org.frc.team2579.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberSpeed extends Command {

	private double speed;
	
	public ClimberSpeed(double speed) {
		requires(Robot.climber);
		this.speed = speed;
	}

	@Override
	protected void initialize() {
		if(Robot.operationMode == Robot.OperationMode.COMPETITION)
			Robot.climber.setSpeed(speed);
		else
			Robot.climber.setSpeed(-speed);
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
