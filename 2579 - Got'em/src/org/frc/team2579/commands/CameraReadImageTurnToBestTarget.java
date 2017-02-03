package org.frc.team2579.commands;

import edu.rhhs.frc.RobotMain;
import edu.rhhs.frc.subsystems.DriveTrain;
import edu.rhhs.frc.subsystems.DriveTrain.DriveTrainControlMode;
import edu.rhhs.frc.utility.MPSoftwarePIDController.MPSoftwareTurnType;
import edu.rhhs.frc.vision.ImageProcessor.TargetInfo;
import edu.wpi.first.wpilibj.command.Command;

public class CameraReadImageTurnToBestTarget extends Command {
	private boolean targetFound;

	public CameraReadImageTurnToBestTarget() {
		requires(RobotMain.camera);
		requires(RobotMain.driveTrain);
	}

	@Override
	protected void initialize() {
		TargetInfo bestTarget = RobotMain.camera.readImageGetBestTarget();
		if (bestTarget != null) {
			RobotMain.driveTrain.setRelativeTurnMP(bestTarget.angleToTargetDeg,
					DriveTrain.MP_AUTON_MAX_TURN_RATE_DEG_PER_SEC,
					MPSoftwareTurnType.TANK);
			targetFound = true;
		} else {
			targetFound = false;
		}
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		if (!targetFound) {
			return true;
		}
		return RobotMain.driveTrain.isFinished();
	}

	protected void end() {
		RobotMain.driveTrain.setControlMode(DriveTrainControlMode.JOYSTICK);
	}

	protected void interrupted() {
		end();
	}
}