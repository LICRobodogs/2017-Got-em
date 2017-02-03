package org.frc.team2579.commands;

import edu.rhhs.frc.RobotMain;
import edu.wpi.first.wpilibj.command.Command;

public class DriveTrainGyroOffset extends Command {
	private double offsetDeg;

	public DriveTrainGyroOffset(double offsetDeg) {
		requires(RobotMain.driveTrain);
		this.offsetDeg = offsetDeg;
	}

	@Override
	protected void initialize() {
		RobotMain.driveTrain.setGyroOffset(offsetDeg);
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