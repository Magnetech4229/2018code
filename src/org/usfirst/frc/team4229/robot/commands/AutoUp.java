package org.usfirst.frc.team4229.robot.commands;

import org.usfirst.frc.team4229.robot.Robot;
import org.usfirst.frc.team4229.robot.OI;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class AutoUp extends Command {

	private Timer autoTimer = new Timer();
	private double  maxSeconds;
	private double speed;
	

    public AutoUp(double time, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intake);
    	maxSeconds = time;
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.elevator.Up(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (autoTimer.get()>maxSeconds);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevator.Up(0.0);
    }
}