package org.frc.team2579.commands;

import org.frc.team2579.subsystems.DriveTrain;
import org.frc.team2579.utility.SideGearForwardProfile;

import com.ctre.CANTalon.SetValueMotionProfile;

import edu.wpi.first.wpilibj.command.Command;

public class DriveSideForward extends Command {
	public DriveSideForward(){
		
	}

	protected void initialize(){
		DriveTrain.whichAuton = "Side";
		DriveTrain.resetPosition();
		DriveTrain.startFilling(SideGearForwardProfile.Points, SideGearForwardProfile.kNumPoints);
		DriveTrain.setSetValue(SetValueMotionProfile.Enable);
	}

	protected void execute() {
		DriveTrain.startMP();
    }
		
	protected void end() {
    	DriveTrain.resetMP();
    	DriveTrain.setSetValue(SetValueMotionProfile.Disable);
    }
	@Override
	protected boolean isFinished() {
		System.out.println(DriveTrain.isFinished());
		return DriveTrain.isFinished();
	}
}
