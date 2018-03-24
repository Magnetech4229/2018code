package org.usfirst.frc.team4229.robot.commands;

import org.usfirst.frc.team4229.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class RTurn extends Command {

	private double degrees;
	private double  maxSeconds;
	private Timer autoTimer = new Timer();
	private boolean gamedataq;
	private String gamedata = "";
	private int timesgood = 0;
	
	
	public RTurn(double inputDegrees, double inputMaxSeconds) {
		new RTurn(inputDegrees, inputMaxSeconds, false);
	}
	
    public RTurn(double inputDegrees, double inputMaxSeconds, boolean usegamedata) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	degrees = inputDegrees;
    	maxSeconds = inputMaxSeconds;
    	gamedataq = usegamedata;
    	
    	
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.gyro.reset();
    	Robot.drivetrain.getPIDController().setPID(0.1,0,0.1); //Values for tank tread test bot
    	//Robot.drivetrain.getPIDController().setPID(SmartDashboard.getNumber("P",2), SmartDashboard.getNumber("I",0), SmartDashboard.getNumber("D",0));//SmartDashboard PID Values
		SmartDashboard.putNumber("robotP", SmartDashboard.getNumber("P",2));
		SmartDashboard.putNumber("robotI", SmartDashboard.getNumber("I",2));
		SmartDashboard.putNumber("robotD", SmartDashboard.getNumber("D",2));
    	Robot.drivetrain.getPIDController().reset();
    	
    	if(gamedataq==true){
    		gamedata = DriverStation.getInstance().getGameSpecificMessage();
    	}
    	
    	if(gamedata.length() > 0)
    	{
    		if(gamedata.charAt(0) == 'L')
    		{
    			Robot.drivetrain.setSetpoint(-degrees);
    		} else {
    			Robot.drivetrain.setSetpoint(degrees);	
    		}
    	} else{
    		Robot.drivetrain.setSetpoint(degrees);
    	}
    	
		Robot.drivetrain.enable();
		autoTimer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Math.abs(degrees - Robot.gyro.getAngle())<1){
    		timesgood+=1;
    	}
    	else{
    		timesgood=0;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (autoTimer.get()>maxSeconds || timesgood>=1);
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putNumber("Final gyro", Robot.gyro.getAngle());
    	Robot.drivetrain.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
