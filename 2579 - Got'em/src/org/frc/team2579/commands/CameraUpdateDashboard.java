package org.frc.team2579.commands;

import edu.rhhs.frc.RobotMain;
import edu.wpi.first.wpilibj.command.Command;

public class CameraUpdateDashboard extends Command {
	public CameraUpdateDashboard() {
		requires(RobotMain.camera);
	}

	@Override
	protected void initialize() {
		RobotMain.camera.postCameraImageToDashboard();
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