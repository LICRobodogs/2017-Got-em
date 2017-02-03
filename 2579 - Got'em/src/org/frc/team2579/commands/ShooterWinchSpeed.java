package org.frc.team2579.commands;

import edu.rhhs.frc.RobotMain;
import edu.wpi.first.wpilibj.command.Command;

public class ShooterWinchSpeed extends Command {
	private double speed;

	public ShooterWinchSpeed(double speed) {
		requires(RobotMain.shooter);
		this.speed = speed;
	}

	@Override
	protected void initialize() {
		RobotMain.shooter.setWinchSpeed(speed);
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