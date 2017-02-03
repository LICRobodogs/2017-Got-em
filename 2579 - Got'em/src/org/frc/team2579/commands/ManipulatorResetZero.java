package org.frc.team2579.commands;

import edu.rhhs.frc.RobotMain;
import edu.wpi.first.wpilibj.command.Command;

public class ManipulatorResetZero extends Command {
	public ManipulatorResetZero() {
		requires(RobotMain.manipulator);
	}

	protected void initialize() {
		RobotMain.manipulator.setZeroPosition();
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}