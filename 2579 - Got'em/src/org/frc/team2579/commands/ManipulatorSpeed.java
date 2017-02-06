package org.frc.team2579.commands;

import org.frc.team2579.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ManipulatorSpeed extends Command {

	private double speed;
	
	public ManipulatorSpeed(double speed) {
		requires(Robot.manipulator);
		this.speed = speed;
	}

	@Override
	protected void initialize() {
		Robot.manipulator.setSpeed(speed);
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
