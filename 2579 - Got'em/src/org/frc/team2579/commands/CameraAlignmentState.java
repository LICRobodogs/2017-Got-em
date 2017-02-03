package org.frc.team2579.commands;

import edu.rhhs.frc.RobotMain;
import edu.wpi.first.wpilibj.command.Command;

public class CameraAlignmentState extends Command {
	private boolean state;

	public CameraAlignmentState(boolean state) {
		requires(RobotMain.camera);
		this.state = state;
	}

	@Override
	protected void initialize() {
		RobotMain.camera.setAlignmentFinished(state);
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