package org.usfirst.frc.team4229.robot.commands;

import org.usfirst.frc.team4229.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class JoyDrive extends Command {
	
	public double L;
	public double R;
	public double Speed;
	public double LS;
	public double RS;
	public double brakeL;
	public double brakeR;
	public double BS; //Break speed
	public double BT; //Break threshold (how fast it's willing to go before breaking)
	public boolean brakes;

    public JoyDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	requires(Robot.encoders);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Speed = SmartDashboard.getNumber("Drive Speed", 1.0);
    	L = -(Robot.oi.stickL.getY());
    	R = -(Robot.oi.stickR.getY());
    	//L = Robot.drivetrain.deadzone(L);
    	//R = Robot.drivetrain.deadzone(R);
    	
    	/*
    	if( (L>0.25 && R<-0.25) || (L<-0.25 && R>0.25) ){
    		SmartDashboard.putBoolean("turning", true);
    		Robot.drivetrain.drive(L, R);
    	} else{
    		SmartDashboard.putBoolean("turning", false);
    		Robot.drivetrain.drive(L, R);
    	}
    	*/
    	
    	Robot.drivetrain.drive(L, R);
    	
    	
    	//Robot.drivetrain.drive(L*Speed, R*Speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.drive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
