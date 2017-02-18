package org.frc.team2579.commands;

import org.frc.team2579.Robot;
import org.frc.team2579.subsystems.Shooter.ShooterControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberKonami extends Command {

	private String s;
	private static boolean kon = false,ami = false;//,go= false;
	public ClimberKonami(String s) {
		requires(Robot.climber);
		this.s = s;
	}

	@Override
	protected void initialize() {
		if(s=="kon")
			kon=true;
		else if(s=="ami")
			ami=true;
		else if(s=="kill"){
			kon=false;
			ami=false;
		}

	}

	@Override
	protected void execute() {
		if(kon&&ami)
			Robot.climber.setBackward();
		else
			Robot.climber.setForward();
	}

	@Override
	protected boolean isFinished() {
		return false;
		//return Robot.shooter.isOnTarget();
	}

	@Override
	protected void end() {
		//kon = false;
		//ami = false;
		//Robot.climber.setForward();
	}

	@Override
	protected void interrupted() {
			
	}


}
