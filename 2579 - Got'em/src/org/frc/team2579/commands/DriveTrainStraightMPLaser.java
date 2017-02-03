package org.frc.team2579.commands;

import edu.rhhs.frc.RobotMain;
import edu.rhhs.frc.subsystems.DriveTrain.DriveTrainControlMode;
import edu.wpi.first.wpilibj.command.Command;

public class DriveTrainStraightMPLaser extends Command {
	private double distanceInches, maxVelocityInchesPerSec,
			desiredAbsoluteAngle;
	private boolean useGyroLock, useAbsolute;

	public DriveTrainStraightMPLaser(double distanceInches,
			double maxVelocityInchesPerSec, boolean useGyroLock,
			boolean useAbsolute, double desiredAbsoluteAngle) {
		requires(RobotMain.driveTrain);
		this.distanceInches = distanceInches;
		this.maxVelocityInchesPerSec = maxVelocityInchesPerSec;
		this.desiredAbsoluteAngle = desiredAbsoluteAngle;
		this.useGyroLock = useGyroLock;
		this.useAbsolute = useAbsolute;
	}

	protected void initialize() {
		RobotMain.driveTrain.setStraightMP(distanceInches,
				maxVelocityInchesPerSec, useGyroLock, useAbsolute,
				desiredAbsoluteAngle);
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return RobotMain.driveTrain.getLaserSensorStatus()
				|| RobotMain.driveTrain.isFinished();
	}

	protected void end() {
		RobotMain.driveTrain.setFinished(true);
		RobotMain.driveTrain.setControlMode(DriveTrainControlMode.JOYSTICK);
	}

	protected void interrupted() {
		end();
	}
}