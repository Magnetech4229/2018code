package org.usfirst.frc.team4229.robot.commands;

import org.usfirst.frc.team4229.robot.Robot;
import org.usfirst.frc.team4229.robot.OI;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class AutoRelease extends Command {
	private Timer autoTimer = new Timer();
	private double  maxSeconds;
	private double speed;

    public AutoRelease(double time, double speed) {
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
    	Robot.intake.Grab(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (autoTimer.get()>maxSeconds);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.Grab(0.0);
    }
}
