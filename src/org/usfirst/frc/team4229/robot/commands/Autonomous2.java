package org.usfirst.frc.team4229.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Autonomous2 extends CommandGroup {

	
    public Autonomous2() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	
    	//AUTONOMOUS INSTRUCTIONS
    	addSequential(new DriveForwards(0.5, 0.75));//Placeholder time value- needs to go about 3 feet
    	addSequential(new DriveForwards(0.5, 0.0)); //a nice stop
    	addSequential(new RTurn(90, 3, true));
    	addSequential(new DriveForwards(6.0, 0.5));//placehonlder value - needs to go about 4.5 feet
    	addSequential(new RTurn(-90, 3, true)); //UNSURE
    	addSequential(new DriveForwards(3.5, 0.75)); //Placeholder time value- needs to go about 9 feet minus the length of the robot. Can overshoot.
    	
    	
    	
 
    	
    	//addSequential(new LTurnAuto(45, 1.0));
        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
    
  
}
