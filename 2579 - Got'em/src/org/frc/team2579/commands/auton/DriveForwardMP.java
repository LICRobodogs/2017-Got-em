package org.frc.team2579.commands.auton;

import org.frc.team2579.subsystems.DriveTrain;
import org.frc.team2579.utility.MiddleGearForwardProfile;

import com.ctre.CANTalon.SetValueMotionProfile;

import edu.wpi.first.wpilibj.command.Command;

public class DriveForwardMP extends Command{
	
	public DriveForwardMP(){
		
	}

	protected void initialize(){
		DriveTrain.resetPosition();
		DriveTrain.startFilling(MiddleGearForwardProfile.Points, MiddleGearForwardProfile.kNumPoints);
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
		//if(DriveTrain._status.hasUnderrun)
		//	return true;
		System.out.println(DriveTrain.isFinished());
		return DriveTrain.isFinished();
	}
}
