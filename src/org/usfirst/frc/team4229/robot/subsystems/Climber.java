package org.usfirst.frc.team4229.robot.subsystems;

import org.usfirst.frc.team4229.robot.Robot;
import org.usfirst.frc.team4229.robot.commands.Climb;

import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class Climber extends Subsystem {

	private boolean inversed = false;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public SpeedController climbMotor;
	//public SpeedController climbMotor2;
	
	public Climber(){
		climbMotor = new Talon(8);
		//climbMotor2 = new Talon(3);
	}
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    }
    
    public void spin(double speed){
    	climbMotor.set(speed*(-Robot.oi.stickR.getZ()/2 + 0.5));
  
    	//climbMotor2.set(speed);
    }
    
    public void stop(){
    	climbMotor.set(0);
    	//climbMotor2.set(0);
    }
}

