package org.usfirst.frc.team4229.robot.commands;

import org.usfirst.frc.team4229.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class RTurn extends Command {

	private double degrees;
	private double  maxSeconds;
	private Timer autoTimer;
	
	
	
    public RTurn(double inputDegrees, double inputMaxSeconds) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	degrees = inputDegrees;
    	maxSeconds = inputMaxSeconds;
    	
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.drive(1, -1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
