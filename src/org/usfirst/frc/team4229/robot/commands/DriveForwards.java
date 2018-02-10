package org.usfirst.frc.team4229.robot.commands;

import org.usfirst.frc.team4229.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForwards extends Command {
	
	private double error;
	private double distance;
	private double driveForwardSpeed;
	private final double TOLERANCE=0.1;

    public DriveForwards() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this(10, 0.5);
    }
    
    public DriveForwards(double dist){
    	this(dist, 0.5);
    }
    
    public DriveForwards(double dist, double maxSpeed){
    	requires(Robot.drivetrain);
    	requires(Robot.encoders);
    	distance = dist;
    	driveForwardSpeed = maxSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.encoders.reset();
    	Robot.drivetrain.drive(0, 0);
    	setTimeout(4);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	error = (distance - Robot.encoders.encLeft.getDistance());
    	if (driveForwardSpeed * error >=  driveForwardSpeed){
    		Robot.drivetrain.drive(driveForwardSpeed,
					driveForwardSpeed);
    	} else {
			Robot.drivetrain.drive(driveForwardSpeed * error,
					driveForwardSpeed * error);
		}
    	
    	Robot.drivetrain.log();
    	Robot.encoders.log();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return (Math.abs(error) <= TOLERANCE) || isTimedOut();
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
