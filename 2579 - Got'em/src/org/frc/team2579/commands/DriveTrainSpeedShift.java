package org.frc.team2579.commands;

import edu.rhhs.frc.RobotMain;
import edu.rhhs.frc.subsystems.DriveTrain.SpeedShiftState;
import edu.wpi.first.wpilibj.command.Command;

public class DriveTrainSpeedShift extends Command {
	private SpeedShiftState state;

	public DriveTrainSpeedShift(SpeedShiftState state) {
		requires(RobotMain.driveTrain);
		this.state = state;
	}

	@Override
	protected void initialize() {
		RobotMain.driveTrain.setShiftState(state);
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