package org.usfirst.frc.team4229.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {
	private SpeedController Elevator = new Talon(4);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void Up(double speed){
    	Elevator.set(speed);
    	
    }
    
    public void Down(double speed){
    	Elevator.set(speed * -1); 
    	
    }
}

