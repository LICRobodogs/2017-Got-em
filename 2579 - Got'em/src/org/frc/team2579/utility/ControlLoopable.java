package org.frc.team2579.utility;

public interface ControlLoopable 
{
	public void controlLoopUpdate();
	public void setPeriodMs(long periodMs);
}