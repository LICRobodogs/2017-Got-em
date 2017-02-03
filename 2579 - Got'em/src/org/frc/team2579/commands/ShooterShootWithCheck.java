package org.frc.team2579.commands;

import edu.rhhs.frc.RobotMain;
import edu.rhhs.frc.subsystems.Shooter.CarriageState;
import edu.wpi.first.wpilibj.command.Command;

public class ShooterShootWithCheck extends Command {
	private boolean checkForValidTarget;

	public ShooterShootWithCheck(boolean checkForValidTarget) {
		requires(RobotMain.shooter);
		this.checkForValidTarget = checkForValidTarget;
	}

	@Override
	protected void initialize() {
		if (checkForValidTarget) {
			if (RobotMain.camera.isTargetValid()) {
				RobotMain.shooter.setCarriageRelease(CarriageState.RELEASED);
			}
		} else {
			RobotMain.shooter.setCarriageRelease(CarriageState.RELEASED);
		}
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