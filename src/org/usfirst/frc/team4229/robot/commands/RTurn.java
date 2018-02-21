package org.usfirst.frc.team4229.robot.commands;

import org.usfirst.frc.team4229.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class RTurn extends Command {

	private double degrees;
	private double  maxSeconds;
	private Timer autoTimer = new Timer();
	
	
	
    public RTurn(double inputDegrees, double inputMaxSeconds) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	degrees = inputDegrees;
    	maxSeconds = inputMaxSeconds;
    	
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.gyro.reset();
    	Robot.drivetrain.getPIDController().setPID(SmartDashboard.getNumber("P",2), SmartDashboard.getNumber("I",0), SmartDashboard.getNumber("D",0));
		Robot.drivetrain.getPIDController().reset();
		Robot.drivetrain.setSetpoint(degrees);
		Robot.drivetrain.enable();
		autoTimer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return autoTimer.get()>maxSeconds;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
