package org.frc.team2579.commands;

import edu.rhhs.frc.RobotMain;
import edu.rhhs.frc.subsystems.Shooter.ShotPosition;
import edu.wpi.first.wpilibj.command.Command;

public class ShooterShotPosition extends Command {
	private ShotPosition position;

	public ShooterShotPosition(ShotPosition position) {
		requires(RobotMain.shooter);
		this.position = position;
	}

	@Override
	protected void initialize() {
		RobotMain.shooter.setShotPosition(position);
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