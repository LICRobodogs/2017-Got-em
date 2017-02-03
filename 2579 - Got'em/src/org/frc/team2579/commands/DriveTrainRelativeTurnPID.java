package org.frc.team2579.commands;

import edu.rhhs.frc.RobotMain;
import edu.rhhs.frc.subsystems.DriveTrain.DriveTrainControlMode;
import edu.rhhs.frc.utility.MPSoftwarePIDController.MPSoftwareTurnType;
import edu.wpi.first.wpilibj.command.Command;

public class DriveTrainRelativeTurnPID extends Command {
	private double relativeTurnAngleDeg;
	private MPSoftwareTurnType turnType;

	public DriveTrainRelativeTurnPID(double relativeTurnAngleDeg,
			MPSoftwareTurnType turnType) {
		requires(RobotMain.driveTrain);
		this.relativeTurnAngleDeg = relativeTurnAngleDeg;
		this.turnType = turnType;
	}

	protected void initialize() {
		RobotMain.driveTrain.setRelativeTurnPID(relativeTurnAngleDeg, 0.3, 0.1,
				turnType);
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