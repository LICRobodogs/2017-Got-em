package org.frc.team2579.commands;

import org.frc.team2579.RobotMain;
import org.frc.team2579.subsystems.Shooter;
import org.frc.team2579.subsystems.Shooter.CarriageState;
import edu.wpi.first.wpilibj.command.Command;

public class ShooterWinchSafeRelease extends Command {
	private boolean safeReleaseComplete;

	public ShooterWinchSafeRelease() {
		requires(RobotMain.shooter);
	}

	@Override
	protected void initialize() {
		RobotMain.shooter.setWinchSpeed(Shooter.WINCH_SAFE_RELEASE_SPEED);
		safeReleaseComplete = false;
	}

	@Override
	protected void execute() {
		if (RobotMain.shooter.isWinchCurrentAtSafeRelease()) {
			RobotMain.shooter.setCarriageRelease(CarriageState.RELEASED);
			RobotMain.shooter.setWinchSpeed(0);
			safeReleaseComplete = true;
		}
	}

	@Override
	protected boolean isFinished() {
		return safeReleaseComplete;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}
}