package org.usfirst.frc.team4229.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Autonomous2 extends CommandGroup {

	String gamedata = "";
	
    public Autonomous2() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	
    	//AUTONOMOUS INSTRUCTIONS
    	
    	addSequential(new DriveForwards(300, 1)); //Placeholder time value- needs to go about 3 feet
    	
    	if(gamedata.length() > 0)
    	{
    		if(gamedata.charAt(0) == 'L')
    		{
    			addSequential(new RTurn(-90, 3));
    			
    		} else {
    			addSequential(new RTurn(90, 3));
    			
    		}
    	}

    	
    	addSequential(new DriveForwards(300, 1)); //Placeholder time value- needs to go about 6 feet
    	
    	
    	
 
    	
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
    
    public void Setgamedata(String inputdata) {
    	
    	gamedata = inputdata;
    
    	
    }
}
