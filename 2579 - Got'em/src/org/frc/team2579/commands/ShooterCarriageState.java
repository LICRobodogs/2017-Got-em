package org.frc.team2579.commands;

import edu.rhhs.frc.RobotMain;
import edu.rhhs.frc.subsystems.Shooter.CarriageState;
import edu.wpi.first.wpilibj.command.Command;

public class ShooterCarriageState extends Command {
	private CarriageState state;

	public ShooterCarriageState(CarriageState state) {
		requires(RobotMain.shooter);
		this.state = state;
	}

	@Override
	protected void initialize() {
		RobotMain.shooter.setCarriageRelease(state);
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