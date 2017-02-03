package org.frc.team2579.commands;

import edu.rhhs.frc.RobotMain;
import edu.wpi.first.wpilibj.command.Command;

public class CameraOffset extends Command {
	private double deltaAngle;

	public CameraOffset(double deltaAngle) {
		requires(RobotMain.camera);
		this.deltaAngle = deltaAngle;
	}

	@Override
	protected void initialize() {
		RobotMain.camera.incrementAngleOffset(deltaAngle);
		;
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