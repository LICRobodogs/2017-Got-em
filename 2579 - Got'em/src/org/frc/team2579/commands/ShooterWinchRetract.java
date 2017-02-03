package org.frc.team2579.commands;

import edu.rhhs.frc.RobotMain;
import edu.rhhs.frc.subsystems.Shooter;
import edu.rhhs.frc.subsystems.Shooter.CarriageState;

public class ShooterWinchRetract extends ExtraTwoTimeoutCommand {
	private final static double CARRIAGE_PNEUMATIC_LOCK_TIMEOUT = 0.100;
	private final static double SAFETY_TIMEOUT = 7.0;
	private boolean waitingForLock;
	private boolean carriageIsAlreadyRetracted;

	public ShooterWinchRetract() {
		requires(RobotMain.shooter);
	}

	@Override
	protected void initialize() {
		this.setInterruptible(false);
		waitingForLock = false;
		resetTimer1();
		resetTimer2();
		startExtraTimeout2(SAFETY_TIMEOUT);
		RobotMain.shooter.resetWinchEncoder();
		carriageIsAlreadyRetracted = RobotMain.shooter.isCarriageRetracted();
		if (!carriageIsAlreadyRetracted) {
			RobotMain.shooter.setCarriageRelease(CarriageState.RELEASED);
			RobotMain.shooter.setWinchSpeed(Shooter.WINCH_RETRACT_SPEED);
		}
	}

	@Override
	protected void execute() {
		if (!carriageIsAlreadyRetracted && !waitingForLock) {
			if (RobotMain.shooter.isCarriageRetracted()
					|| RobotMain.shooter.isWinchCurrentAtMax()) {
				RobotMain.shooter.setCarriageRelease(CarriageState.LOCKED);
				startExtraTimeout1(CARRIAGE_PNEUMATIC_LOCK_TIMEOUT);
				waitingForLock = true;
			}
		}
	}

	@Override
	protected boolean isFinished() {
		return carriageIsAlreadyRetracted || isExtraTimedOut1()
				|| isExtraTimedOut2();
	}

	@Override
	protected void end() {
		RobotMain.shooter.setWinchSpeed(0.0);
	}

	@Override
	protected void interrupted() {
		end();
	}
}