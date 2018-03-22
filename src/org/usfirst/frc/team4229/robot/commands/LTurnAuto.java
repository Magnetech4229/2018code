package org.usfirst.frc.team4229.robot.commands;

import org.usfirst.frc.team4229.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LTurnAuto extends Command {
	
	private double error;
	private double angle;
	private double turnSpeed;
	private final double TOLERANCE = 0.5;

    public LTurnAuto() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this(45);
    }
    
    public LTurnAuto(double a){
    	this(a, 0.5);
    }
    
    public LTurnAuto(double a, double maxSpeed) {
    	angle = a;
    	turnSpeed = maxSpeed;
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(1);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	error = angle - Robot.gyro.getAngle();
    	if (turnSpeed * error >= turnSpeed){
    		Robot.drivetrain.drive(-turnSpeed, turnSpeed);
    	}
    	else {
    		Robot.drivetrain.drive(-turnSpeed * error, turnSpeed * error);
    	}
    	Robot.drivetrain.log();
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
