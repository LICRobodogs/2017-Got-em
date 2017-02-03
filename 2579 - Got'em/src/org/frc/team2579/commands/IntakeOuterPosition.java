package org.frc.team2579.commands;

import edu.rhhs.frc.RobotMain;
import edu.rhhs.frc.subsystems.Intake.LiftState;
import edu.wpi.first.wpilibj.command.Command;

public class IntakeOuterPosition extends Command {
	private LiftState state;

	public IntakeOuterPosition(LiftState state) {
		requires(RobotMain.intake);
		this.state = state;
	}

	@Override
	protected void initialize() {
		RobotMain.intake.setPositionOuter(state);
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