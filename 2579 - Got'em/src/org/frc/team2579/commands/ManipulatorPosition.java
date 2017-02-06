package org.frc.team2579.commands;

import org.frc.team2579.Robot;
import org.frc.team2579.subsystems.Manipulator.LiftState;

import edu.wpi.first.wpilibj.command.Command;

public class ManipulatorPosition extends Command{
private LiftState state;
	
	public ManipulatorPosition(LiftState state) {
		requires(Robot.manipulator);
		this.state = state;
	}

	@Override
	protected void initialize() {
		Robot.manipulator.setPosition(state);
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
