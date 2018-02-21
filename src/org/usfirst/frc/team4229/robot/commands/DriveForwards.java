package org.usfirst.frc.team4229.robot.commands;

import org.usfirst.frc.team4229.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForwards extends Command {
	
	private double error;
	private double time;
	private double driveForwardSpeed;
	private final double TOLERANCE=0.1;
	private double kp = 1.0/90.0 ;


    public DriveForwards() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this(10, 0.5);
    }
    
    public DriveForwards(double time){
    	this(time, 0.5);
    }
    
    public DriveForwards(double time, double maxSpeed){
    	requires(Robot.drivetrain);
    	this.time = time;
    	driveForwardSpeed = maxSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.driveAuto(0, 0);
    	setTimeout(time);
    	Robot.gyro.reset();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double left = driveForwardSpeed * (1 - Robot.gyro.getAngle() * kp);
    	if (Math.abs(left) > Math.abs(driveForwardSpeed)){
    		
    		left = Math.signum(left) * driveForwardSpeed;
    		
    	}
    	
    	double right = driveForwardSpeed * (1 + Robot.gyro.getAngle() * kp);
    	if (Math.abs(right) > Math.abs(driveForwardSpeed)){
    		
    		right = Math.signum(right) * driveForwardSpeed;
    		
    	}
    	
    	Robot.drivetrain.driveAuto(driveForwardSpeed, driveForwardSpeed);
    	Robot.drivetrain.log();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.driveAuto(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
