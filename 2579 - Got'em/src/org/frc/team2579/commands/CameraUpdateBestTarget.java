package org.frc.team2579.commands;

import edu.rhhs.frc.RobotMain;
import edu.wpi.first.wpilibj.command.Command;

public class CameraUpdateBestTarget extends Command {

	public CameraUpdateBestTarget() {
		requires(RobotMain.camera);
	}

	@Override
	protected void initialize() {
		RobotMain.camera.getBestTarget();
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {

	}

	protected void interrupted() {
		end();
	}
}