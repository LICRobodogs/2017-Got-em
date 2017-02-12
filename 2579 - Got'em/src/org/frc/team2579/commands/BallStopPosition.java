package org.frc.team2579.commands;

import org.frc.team2579.Robot;
import org.frc.team2579.subsystems.Intake.BallStopState;

import edu.wpi.first.wpilibj.command.Command;

public class BallStopPosition extends Command{
private BallStopState state;
	
	public BallStopPosition(BallStopState state) {
		requires(Robot.intake);
		this.state = state;
	}

	@Override
	protected void initialize() {
		Robot.intake.setBallStop(state);
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
