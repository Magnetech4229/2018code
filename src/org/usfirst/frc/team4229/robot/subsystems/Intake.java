package org.usfirst.frc.team4229.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
	private static SpeedController IntakeLeft = new Talon(3);
	private static SpeedController IntakeRight = new Talon(2);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public static void Grab(double speed){
    	IntakeLeft.set(1 * speed);
    	IntakeRight.set(1 * speed);
    	
    }
    
    public static void Release(double speed){
    	IntakeLeft.set(speed * -1);
    	IntakeRight.set(speed * -1); 
    	
    }
}

