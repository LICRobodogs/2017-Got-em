package org.frc.team2579.commands.auton;

import org.frc.team2579.Robot;
import org.frc.team2579.subsystems.Shooter.ShooterControlMode;
import edu.wpi.first.wpilibj.command.Command;

public class ShootAuton extends Command{
	private ShooterControlMode mode;
	private double speed;
	public ShootAuton(ShooterControlMode mode, double speed){
		requires(Robot.shooter);
		this.mode = mode;
		this.speed = speed;
	}

	protected void initialize(){
		setTimeout(6);
		Robot.shooter.setWheelSpeed(mode, speed);
	}

	protected void execute() {
		Robot.intake.feedWithCheck();
    }
		
	protected void end() {
    	Robot.shooter.setWheelSpeed(ShooterControlMode.MANUAL, 0);
    	Robot.intake.setSpeedInner(0);
    }
	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}
}
