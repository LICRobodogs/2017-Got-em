package org.frc.team2579.commands;

import edu.rhhs.frc.RobotMain;
import edu.wpi.first.wpilibj.command.Command;

public class DriveTrainGyroLock extends Command {
	private boolean useLock, snapToAbsolute0or180;

	public DriveTrainGyroLock(boolean useLock, boolean snapToAbsolute0or180) {
		requires(RobotMain.driveTrain);
		this.useLock = useLock;
		this.snapToAbsolute0or180 = snapToAbsolute0or180;
	}

	@Override
	protected void initialize() {
		RobotMain.driveTrain.setGyroLock(useLock, snapToAbsolute0or180);
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