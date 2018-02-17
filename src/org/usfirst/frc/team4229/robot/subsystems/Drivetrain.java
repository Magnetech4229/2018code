package org.usfirst.frc.team4229.robot.subsystems;

import org.usfirst.frc.team4229.robot.ADXRS453Gyro;
import org.usfirst.frc.team4229.robot.Robot;
import org.usfirst.frc.team4229.robot.commands.JoyDrive;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drivetrain extends PIDSubsystem {
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private SpeedController motLeft = new Talon(1);
	private SpeedController motRight = new Talon(0);
	private RobotDrive drive = new RobotDrive(motLeft, motRight);
	public double Speed;

	static final double kDEADZONE = 0.1;
	
	public Drivetrain(){
		super("Drivetrain", 2.0, 0.0, 0.0);
		
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new JoyDrive());		
    }
    public void drive(double left, double right){
    	Speed = ((Robot.oi.stickL.getZ() * -1) + 1) / 2;
    	drive.tankDrive(-left * Speed, -right * Speed);
    }
    
    public void log(){
		SmartDashboard.putNumber("Joystick L", Robot.oi.stickL.getY());
		SmartDashboard.putNumber("Joystick R", Robot.oi.stickR.getY());
		SmartDashboard.putBoolean("trigL", Robot.oi.trigL.get());
		SmartDashboard.putNumber("ZAxis", Robot.oi.stickL.getZ());
    }
    

    

    
    public double deadzone(double n){
    	// && is (AND)
    	// || is (OR)
    	
    	if (n<kDEADZONE && n>-kDEADZONE){
    		n=0;
    	}
    	
    	else{
    		
    		if (n>0){
    			n=(n-kDEADZONE)/(1-kDEADZONE);
    		}
    		
    		if (n<0){
    			n=(n+kDEADZONE)/(1-kDEADZONE);
    		}
    		
    	}
    	return n;
    }
    
    public double analogue(double n){
    	if (n<+0.5&&n>+0.5 || n>0.5){
    		n=n*0.5;
    	}
    	return n;
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return Robot.gyro.getAngle();
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	Robot.drivetrain.drive(-output, output);
    }
    
    public void setPIDValues(double inputP,double inputI,double inputD){
    	getPIDController().setPID(inputP, inputI, inputD);
    }
}

