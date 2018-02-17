
package org.usfirst.frc.team4229.robot;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import org.usfirst.frc.team4229.robot.ADXRS453Gyro;
//import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.*;
//import edu.wpi.first.wpilibj.RobotDrive;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionThread;

import org.usfirst.frc.team4229.robot.subsystems.Agitator;
import org.usfirst.frc.team4229.robot.subsystems.Climber;
import org.usfirst.frc.team4229.robot.subsystems.Drivetrain;
import org.usfirst.frc.team4229.robot.subsystems.Encoders;
import org.usfirst.frc.team4229.robot.subsystems.Gyroscope;
import org.usfirst.frc.team4229.robot.subsystems.ServoMotor;
import org.usfirst.frc.team4229.robot.subsystems.Shooter;
import org.usfirst.frc.team4229.robot.subsystems.Testing;
import org.usfirst.frc.team4229.robot.subsystems.Intake;
import org.usfirst.frc.team4229.robot.subsystems.Elevator;
import org.opencv.core.Mat;
import org.usfirst.frc.team4229.robot.GRIPGreenMaskPipeline;
import org.usfirst.frc.team4229.robot.commands.Autonomous1;
import org.usfirst.frc.team4229.robot.commands.DriveForwards;
import org.usfirst.frc.team4229.robot.CameraThread;
import org.usfirst.frc.team4229.robot.FURetro;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	private static final String kDefaultAuto = "Default";
	private static final String kCustomAuto = "My Auto";
	private String m_autoSelected;
	private SendableChooser<String> m_chooser = new SendableChooser<>();
	// differential drive is the new robot drive
	Joystick left, right;
	Talon cubeD1;
	Talon cubeD2;
	Talon DriveR;
	Talon DriveL;
	double speed1;
	double speed2;
	double topSpeed;
	public static double P = 0.3;
	public static double I = 0.000001;
	public static double D = 0.36;
	String gameData;
	public static ADXRS453Gyro gyro = new ADXRS453Gyro();


	public void turn(double speed){

		
	}


	private static final int IMG_WIDTH = 1600;
	private static final int IMG_HEIGHT = 800;
	//public static ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static Drivetrain drivetrain = new Drivetrain();
	public static Climber climber = new Climber();
	public static Intake intake = new Intake();
	public static Elevator elevator = new Elevator();
	//public static GRIPGreenMaskPipeline greenMaskPipeline = new GRIPGreenMaskPipeline();
	//public static FURetro fuRetro = new FURetro();
	public static ServoMotor servoMotor = new ServoMotor();
	public static Gyroscope gyroscope = new Gyroscope();
	public static Encoders encoders = new Encoders();
	public static PIDController turner = new PIDController(P, I, D, gyro, new gyroPIDoutput());
	//public static CameraThread cameraThread = new CameraThread(); 
	//public static Testing testing = new Testing();
	public static OI oi;
	//public static AxisCamera camera;
	//NetworkTable table;
	
	//private static final int IMG_WIDTH = 320;
	//private static final int IMG_HEIGHT = 240;
	
	//private VisionThread visionThread;
	//private double centerX = 0.0;
	
	//private final Object imgLock = new Object();

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();


	
	
	
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {

		m_chooser.addDefault("Default Auto", kDefaultAuto);
		m_chooser.addObject("My Auto", kCustomAuto);
		SmartDashboard.putData("Auto choices", m_chooser);

		// joysticks
		left = new Joystick(0);
		right = new Joystick(1);
		//elivator

		cubeD1 = new Talon(3);
		cubeD2 = new Talon(4);
		speed1 = 0;
		speed2 = 0;
		topSpeed = 0;
		// gyrosphere
		gyro.startThread();
		gyro.calibrate();
		SmartDashboard.putNumber("P", 0.3);
		SmartDashboard.putNumber("I", 0.000001);
		SmartDashboard.putNumber("D", 0.36);
		

		
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
	    camera.setResolution(IMG_WIDTH, IMG_HEIGHT);
	    
	    UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture();
	    camera1.setResolution(IMG_WIDTH, IMG_HEIGHT);
	    
	    //visionThread = new VisionThread(camera, new GRIPGreenMaskPipeline(), pipeline -> {
	        //if (pipeline.maskOutput().empty()) {
	            //Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
	        	//Mat i = pipeline.maskOutput();
	            //synchronized () {
	                //centerX = r.x + (r.width / 2);
	            //}
	        //}
	    //});
	    //visionThread.start();
	    
	    //Robot.cameraThread.init();
	    //Robot.cameraThread.start(); 
	    //camera = CameraServer.getInstance().addAxisCamera("axis-camera.local");

		
		oi = new OI();
		//table = NetworkTable.getTable("data");
		//chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		
		SmartDashboard.putNumber("Shoot Speed", 0.55);
		SmartDashboard.putData("Auto mode", chooser);
		SmartDashboard.putNumber("Drive Speed", 1.0);
		SmartDashboard.putNumber("Servo Base", 0.85);
		SmartDashboard.putNumber("Brake Speed", 0.5);
		SmartDashboard.putNumber("Brake Thresh", 10.0);
		SmartDashboard.putBoolean("Brakes", true);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		//table.putBoolean("autoStart", true);
		Robot.encoders.reset();
		//autonomousCommand = chooser.getSelected();
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if(gameData.length() > 0)
		{
			if(gameData.charAt(0)== 'L') {
				//left code here
			}
			else  {
				//right auto code here
			}
		}
		//autonomousCommand = new Autonomous1();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */
		
		

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		log();
		/**
		if (Robot.encoders.getDistance()<1){
			Robot.drivetrain.drive(0.75, 0.75);
		}
		**/
		
		/**
		if (gyroscope.gyroSPI.getAngle()>90 || gyroscope.gyroSPI.getAngle()<-90) {
			Robot.drivetrain.drive(-0.25, 0.25);
		}
		**/
		
		Scheduler.getInstance().run();
	}

	@Override

	public void teleopInit(){
		turner.disable();
		//table.putBoolean("teleStart", true);
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		//drivetrain.drive(1, 1);
		if (autonomousCommand != null)
			autonomousCommand.cancel();
			
		
	}
	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {


		
		topSpeed = left.getZ()/2.0-0.5;
		SmartDashboard.putNumber("left", left.getZ() );
		SmartDashboard.putNumber("right", right.getZ() );
		speed1 = left.getZ();
		speed2 = right.getZ();
		//some stuff for gyro
		SmartDashboard.putNumber("gyro", gyro.getAngle());
		SmartDashboard.putNumber("GyroAngle", gyro.getAngle());
		SmartDashboard.putNumber("GyroPos", gyro.getPos());
		SmartDashboard.putNumber("GyroRate", gyro.getRate());
		SmartDashboard.putNumber("GyroTemp", gyro.getTemp());

		/* If right trigger pulls up
		 * if released stop the elevator
		 * if left trigger, elevator down
		 * if both triggers, ?
		 * if at top don't go up
		 * if at bottom don't go down
		 * 
		 * 
		 * 
		 * intakes
		 * left button 3 take intakes
		 * if released stop intaking
		 * right button 3 to shoot
		 * if released stop shooting
		 * 
		 * 
		 * 
		 */


		Scheduler.getInstance().run();
		log();
		LiveWindow.run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	

	
	@Override
	public void testInit(){
		
		
		
		
		//(0.1, 0.0000001, 0.07)
		turner = new PIDController(P, I, D, gyro, new gyroPIDoutput());
		gyro.reset();
		turner.setSetpoint(53);
		turner.enable();
		
		
		
	}
	
	
	
	
	
	@Override
	public void testPeriodic() {

		SmartDashboard.putNumber("GyroAngle", gyro.getAngle());
		SmartDashboard.putNumber("GyroPos", gyro.getPos());
		SmartDashboard.putNumber("GyroRate", gyro.getRate());
		SmartDashboard.putNumber("GyroTemp", gyro.getTemp());
		//SmartDashboard.putNumber("codep", P);
		
		SmartDashboard.putNumber("robotP", P);
		SmartDashboard.putNumber("robotI", I);
		SmartDashboard.putNumber("robotD", D);
		
		P = SmartDashboard.getNumber("P", 0);
		I = SmartDashboard.getNumber("I", 0);
		D = SmartDashboard.getNumber("D", 0);
		turner.setPID(P, I, D);
		
		
		
	}
	
	public class gyroPIDoutput implements PIDOutput {
		public void pidWrite(double output){
			turn(output);
			
		}
		

		//LiveWindow.run();
	}
	
	private void log() {
		drivetrain.log();
		gyroscope.log();
		encoders.log();

	}
}
