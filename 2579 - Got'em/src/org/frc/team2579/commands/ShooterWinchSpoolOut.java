package org.frc.team2579.commands;

import edu.rhhs.frc.RobotMain;
import edu.rhhs.frc.subsystems.Shooter;
import edu.wpi.first.wpilibj.command.Command;

public class ShooterWinchSpoolOut extends Command {
	private boolean isWinchAlreadyRetracted;

	public ShooterWinchSpoolOut() {
		requires(RobotMain.shooter);
	}

	@Override
	protected void initialize() {
		isWinchAlreadyRetracted = false;
		if (Math.abs(RobotMain.shooter.getWinchPositionWorld()) > 1) {
			RobotMain.shooter.resetWinchEncoder();
			RobotMain.shooter.setWinchSpeed(Shooter.WINCH_SPOOLOUT_SPEED);
		} else {
			isWinchAlreadyRetracted = true;
		}
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return isWinchAlreadyRetracted || RobotMain.shooter.isWinchSpooledOut();
	}

	@Override
	protected void end() {
		RobotMain.shooter.setWinchSpeed(0.0);
		RobotMain.shooter.resetWinchEncoder();
	}

	@Override
	protected void interrupted() {
		end();
	}
}