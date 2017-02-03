package org.frc.team2579.commands;

import edu.rhhs.frc.RobotMain;
import edu.wpi.first.wpilibj.command.Command;

public class DriveTrainGyroReset extends Command {
	public DriveTrainGyroReset() {
		requires(RobotMain.driveTrain);
	}

	@Override
	protected void initialize() {
		RobotMain.driveTrain.resetGyro();
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