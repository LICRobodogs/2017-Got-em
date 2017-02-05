package org.frc.team2579.commands;

import org.frc.team2579.RobotMain;

import edu.wpi.first.wpilibj.command.Command;

public class ManipulatorSpeed extends Command {

	private double speed;
	
	public ManipulatorSpeed(double speed) {
		requires(RobotMain.manipulator);
		this.speed = speed;
	}

	@Override
	protected void initialize() {
		RobotMain.manipulator.setSpeed(speed);
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
