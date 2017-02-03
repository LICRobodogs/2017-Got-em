package org.frc.team2579.commands;

import org.frc.team2579.RobotMain;
import org.frc.team2579.subsystems.DriveTrain.DriveTrainControlMode;
import org.frc.team2579.utility.MPSoftwarePIDController.MPSoftwareTurnType;
import edu.wpi.first.wpilibj.command.Command;

public class DriveTrainRelativeMaxTurnMP extends Command {
	private double relativeTurnAngleDeg, maxTurnRateDegPerSec;
	private MPSoftwareTurnType turnType;

	public DriveTrainRelativeMaxTurnMP(double relativeTurnAngleDeg,
			double maxTurnRateDegPerSec, MPSoftwareTurnType turnType) {
		requires(RobotMain.driveTrain);
		this.relativeTurnAngleDeg = relativeTurnAngleDeg;
		this.maxTurnRateDegPerSec = maxTurnRateDegPerSec;
		this.turnType = turnType;
	}

	protected void initialize() {
		RobotMain.driveTrain.setRelativeMaxTurnMP(relativeTurnAngleDeg,
				maxTurnRateDegPerSec, turnType);
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return RobotMain.driveTrain.isFinished();
	}

	protected void end() {
		RobotMain.driveTrain.setControlMode(DriveTrainControlMode.JOYSTICK);
	}

	protected void interrupted() {
		end();
	}
}