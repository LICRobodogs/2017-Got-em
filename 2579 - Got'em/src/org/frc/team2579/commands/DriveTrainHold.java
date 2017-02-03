package org.frc.team2579.commands;

import edu.rhhs.frc.RobotMain;
import edu.wpi.first.wpilibj.command.Command;

public class DriveTrainHold extends Command {
	private boolean status;

	public DriveTrainHold(boolean status) {
		requires(RobotMain.driveTrain);
		this.status = status;
	}

	@Override
	protected void initialize() {
		RobotMain.driveTrain.setDriveHold(status);
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