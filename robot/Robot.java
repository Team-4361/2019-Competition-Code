/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot;

import javax.lang.model.util.ElementScanner6;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
//import edu.wpi.first.wpilibj.internal.HardwareTimer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.Library.Chassis.TankDrive;
import frc.Library.Controllers.Drive;
import frc.Library.Controllers.PneumaticsControl;
import frc.Library.Controllers.TalonEncoder;
import frc.Library.Controls.JoystickTank;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */

public class Robot extends IterativeRobot {
  public WPI_TalonSRX[] talons;
  public Servo hatchServo;

  public Drive ElevatorDrive;
  public TankDrive theTankDrive;
  public Intake IntakeSystem;
  public Elevator ElevatorSystem;
  public Climber Climber;
  public Autonomous AutonomousSystem;
  public PowerManagement PowerManagementSystem;

  public JoystickTank sticks = new JoystickTank(1, 2);

  public XboxController xboxCont = new XboxController(0);

  private String AutoSelected;

  public PneumaticsControl pneum;
  public DoubleSolenoid intake1;
  public DoubleSolenoid intake2;
  public DoubleSolenoid climber1;
  public DoubleSolenoid climber2;
  public DoubleSolenoid driveTrain;

  public Encoder elevatorEnc;
  
  public DigitalInput topFrontClimber, topBackClimber, bottomFrontClimber, bottomBackClimber;
  public DigitalInput topElevator, bottomElevator;

  // Debug code
  private Timer timer = new Timer();
  private Timer frontClimbTimerExtending = new Timer();
  private Timer backClimbTimerExtending = new Timer();
  private Timer climberWaitFront = new Timer();
  private Timer climberWaitBack = new Timer();

  public boolean frontClimberTime, 
  frontClimberState;
  public boolean backClimberTime, backClimberState;
  public boolean frontClimberTopState, backClimberTopState;
  public boolean frontStartedClimbChange = false, frontClimbingDone = true;
  public boolean backStartedClimbChange = false, backClimbingDone = true;
  
  public UsbCamera cam0;
  public UsbCamera cam1; 

  public boolean ManualElevator;
  public boolean teleopForced;

  public Command autonomousCommand;
  public SendableChooser <String> startChooser = new SendableChooser<>();
  public SendableChooser <String> endChooser = new SendableChooser<>();
  public String startSelected;
  public String endSelected;

  public TalonEncoder lDriveEnc, rDriveEnc;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.                       
   */
  @Override
  public void robotInit() 
  {
    //Auto Slector
    
    ShuffleboardLayout autoChoose = Shuffleboard.getTab("Commands")
    .getLayout("Elevator", BuiltInLayouts.kList)
    .withSize(2, 2);
  
    //autoChoose.add("OffHab", AutonomousSystem.offHAB());

      //"time" variable is if sensor is hit or not
      frontClimberTime = true;//Starts Retracted
      frontClimberState = true;
      backClimberTime = true;//Starts Retracted
      backClimberState = true;
      frontClimberTopState = false;
      backClimberTopState = false;


    //camgurl
    //cameraSetup();
    startChooser.setDefaultOption("Left", "left");
    startChooser.addOption("Mid", "mid");
    startChooser.addOption("Right", "right");

    endChooser.setDefaultOption("OffHAB", "offHAB");
    endChooser.addOption("LeftSideCargo1", "leftsideCargo1");
    endChooser.addOption("LeftSideCargo2", "leftsideCargo2");
    endChooser.addOption("LeftSideCargo3", "leftsideCargo3");
    endChooser.addOption("LeftMidCargo", "rleftCargo");
    endChooser.addOption("RightMidCargo", "rightMidCargo");
    endChooser.addOption("RightSideCargo1", "rightSideCargo1");
    endChooser.addOption("RightSideCargo2", "rightSideCargo2");
    endChooser.addOption("RightSideCargo3", "rightSideCargo3");

    
    //chooser.addOption();
   
    SmartDashboard.putData("Auto Start Chooser", startChooser);
    SmartDashboard.putData("Auto End Chooser", endChooser);
    pneum = new PneumaticsControl(Constant.pcmValue, Constant.PressureSensor, Constant.PneumaticOffset);

    this.talons = new WPI_TalonSRX[Constant.CANLength];
    for(int i = 1; i < talons.length; i++)
    {
      this.talons[i] = new WPI_TalonSRX(i);
    }
    Drive.SetFullCAN(talons);

    int[] lDriveMotors = {Constant.Left0, Constant.Left1 /*Constant.Left2*/};
    Drive lDrive = new Drive(lDriveMotors);
    int[] rDriveMotors = {Constant.Right0, Constant.Right1 /*Constant.Right2*/};
    Drive rDrive = new Drive(rDriveMotors);
    //lDriveTrain = new Encoder(Constant.lDriveEncChannelA, Constant. lDriveEncChannelB);
    //rDriveTrain = new Encoder(Constant.rDriveEncChannelA, Constant.rDriveEncChannelB);

    this.theTankDrive = new TankDrive(lDrive, rDrive /*lDriveEnc, rDriveEnc, Constant.wheelDiameter*/);

    topElevator = new DigitalInput(Constant.limitSwitch1);
    bottomElevator = new DigitalInput(Constant.limitSwitch2);
    elevatorEnc = new Encoder(Constant.eleEncChannelA, Constant.eleEncChannelB);

    int[] elevatorMotor = {Constant.elevatorMotor};
    ElevatorDrive = new Drive(elevatorMotor);
    ElevatorSystem = new Elevator(ElevatorDrive, topElevator, bottomElevator, xboxCont, elevatorEnc);

    //Hatch Servo things
    hatchServo = new Servo(Constant.autoHatchServo);        
    

    
    //Setting up DOUBLE SOLENOIDS
    intake1 = new DoubleSolenoid(Constant.intake1FChan, Constant.intake1RChan);
    climber1 = new DoubleSolenoid(Constant.climber1FChan, Constant.climber1RChan);
    climber2 = new DoubleSolenoid(Constant.climber2FChan, Constant.climber2RChan);
    driveTrain = new DoubleSolenoid(Constant.driveTrainFChan, Constant.driveTrainRChan); //shifting gearbox        

    topFrontClimber = new DigitalInput(Constant.limitSwitch3);
    bottomFrontClimber = new DigitalInput(Constant.limitSwitch4);
    topBackClimber = new DigitalInput(Constant.limitSwitch5);
    bottomBackClimber = new DigitalInput(Constant.limitSwitch6);
    
    PowerManagementSystem = new PowerManagement();

    cam0 = new UsbCamera("cam1", 0);
    cam1 = new UsbCamera("cam2", 1);

    timer.start();
    this.IntakeSystem = new Intake(intake1);
    this.IntakeSystem.Setup();

    Climber = new Climber(climber1, climber2, talons[Constant.frontLiftMotor], talons[Constant.backLiftMotor], talons[Constant.backRollerMotor]);

    //lDriveEnc.setDistancePerPulse(Constant.distanceForPulse*(3.0/8.0)*Math.PI);// * (3/8) * Math.PI);
    //rDriveEnc.setDistancePerPulse(Constant.distanceForPulse);// * (3/8) * Math.PI);

    this.AutonomousSystem = new Autonomous(theTankDrive, IntakeSystem);


    Climber.frontPistonForward();
    Climber.backPistonForward();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic()
  {
    SmartDashboard.putNumber("Pressure", (int) pneum.GetPSI());
    SmartDashboard.putString("Climber State", frontClimberTime ? "Retracted":"Extended");
    SmartDashboard.putBoolean("LimitSwitch1", topFrontClimber.get());
    SmartDashboard.putBoolean("LimitSwitch2", bottomFrontClimber.get());
    SmartDashboard.putBoolean("Back Retract Switch", bottomBackClimber.get());
    SmartDashboard.putBoolean("Back Extend Switch", topBackClimber.get());
    //SmartDashboard.putNumber("Left Drive Encoder Data", lDriveEnc.getDistance());
    //SmartDashboard.putNumber("Right Drive Encoder Data", rDriveEnc.getDistance());
    boolean climberDisplayFront = topFrontClimber.get();
    boolean climberDisplayBack = bottomFrontClimber.get();
    SmartDashboard.putBoolean("topFrontClimber", climberDisplayFront);
    SmartDashboard.putBoolean("bottomFrontClimber", climberDisplayBack);        
    SmartDashboard.putBoolean("Front Climber 'Time'", frontClimberTime);
    SmartDashboard.putBoolean("Back Climber 'Time'", backClimberTime);    
    SmartDashboard.putNumber("Servo Degrees", hatchServo.get());
    SmartDashboard.putBoolean("FrontClimberTopState", frontClimberTopState);
    SmartDashboard.putBoolean("FrontClimberState", frontClimberState);

    //PowerManagementSystem.sendWattageToShuffleBoard();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() 
  {
    startSelected = startChooser.getSelected();
    endSelected = endChooser.getSelected();

    
    //autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
    
  }

  /**
   * This function is  called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic()
  {
    if(sticks.right.getRawButtonPressed(11))
    {
      teleopForced=true;
    }
    if(teleopForced=true)
    {
      this.teleopPeriodic();
    }

    switch (startSelected)
    {
    case "left":
	    switch(endSelected)
		  {
		    case "offHAB":
			    AutonomousSystem.offHAB();
			    break;
			  case "leftSideCargo1":
				  AutonomousSystem.toLeftSide1FromLeftSide();
			    break;
        case "leftSideCargo2":
	        AutonomousSystem.toLeftSide2FromLeftSide();
	        break;
        case "leftSideCargo3":
	        AutonomousSystem.toLeftSide3FromLeftSide();
	        break;
			  case "leftMidCargo":
			    AutonomousSystem.toLeftMiddleFromLeftSide();
			    break;
		    case "rightMidCargo":
			    AutonomousSystem.toRightMiddleFromLeftSide();
				  break;
		    case "rightSideCargo1":
			    AutonomousSystem.toRightSide1FromLeftSide();
			    break;
			  case "rightSideCargo2":
				  AutonomousSystem.toRightSide2FromLeftSide();
			    break;
		    case "rightSideCargo3":
          AutonomousSystem.toRightSide3FromLeftSide();				
          break;
      }


	  case "mid":
	    switch(endSelected)
	    {
		    case "offHAB":
			    AutonomousSystem.offHAB();
			    break;
	      case "leftSideCargo1":
		      AutonomousSystem.toLeftSide1FromMiddle();
		      break;
			  case "leftSideCargo2":
			    AutonomousSystem.toLeftSide2FromMiddle();
		      break;
        case "leftSideCargo3":
		  	  AutonomousSystem.toLeftSide3FromMiddle();
				  break;
		    case "leftMidCargo":
		  	  AutonomousSystem.toMiddle1FromMiddle();
		  	  break;
		    case "rightMidCargo":
			    AutonomousSystem.toMiddle2FromMiddle();
			    break;
	  	  case "rightSideCargo1":
			    AutonomousSystem.toRightSide1FromMiddle();
			    break;
        case "rightSideCargo2":
		      AutonomousSystem.toRightSide2FromMiddle();
	  	    break;
        case "rightSideCargo3":
	        AutonomousSystem.toRightSide3FromMiddle();
          break;
      }

    case "right":
      switch(endSelected)
	    {
    	  case "offHAB":
			    AutonomousSystem.offHAB();
    	  	break;
		    case "leftSideCargo1":
			    AutonomousSystem.toLeftSide1FromRightSide();
	        break;
	      case "leftSideCargo2":
          AutonomousSystem.toLeftSide2FromRightSide();
	        break;
        case "leftSideCargo3":
			    AutonomousSystem.toLeftSide3FromRightSide();
			    break;
		    case "leftMidCargo":
			    AutonomousSystem.toLeftMiddleFromRightSide();
		      break;
		    case "rightMidCargo":
		      AutonomousSystem.toRightMiddleFromRightSide();
		      break;
	      case "rightSideCargo1":
		      AutonomousSystem.toRightSide1FromRightSide();
			    break;
        case "rightSideCargo2":
		      AutonomousSystem.toRightSide2FromRightSide();
		      break;
        case "rightSideCargo3":
		      AutonomousSystem.toRightSide3FromRightSide();
          break;
      }
    }
  }

  /**
   * This function is called periodically during operator control.
   */

  
 @Override
  public void teleopInit()
  {
    Climber.frontPistonForward();
    Climber.backPistonForward();

    
    frontClimberTime = true;//Starts Retracted
    backClimberTime = true;//Starts Retracted

    frontClimberState = true;
    backClimberState = true;

    frontClimberTopState = false;
    backClimberTopState = false;

    frontClimbingDone = true;
    backClimbingDone = true;
  }

  @Override
  public void teleopPeriodic()
  {
    //System.out.println("topFront: " + topFrontClimber.get());
    //System.out.println("bottomFront: " + bottomFrontClimber.get());
    
    pneum.SystemSwitch();
    //System.out.println(timer.get());      

    theTankDrive.drive(sticks.GetDrive());
    ElevatorSystem.Manual(xboxCont.getY(Hand.kRight));

    if(sticks.right.getRawButtonPressed(11))
    {
      hatchServo.set(90);
    }

    //PERFECT TURN
    if(sticks.right.getRawButton  (7))
    {
      theTankDrive.Turn(sticks.right.getX());
    }
    if(sticks.left.getRawButton(8))
    {
      theTankDrive.Straight(sticks.right.getY());
    } 
    
    //INTAKE
    
    //A button opens intake
    if (xboxCont.getBumperPressed(Hand.kRight))
    {
      IntakeSystem.openIntake();
      SmartDashboard.putString("Intake Status", "Open");
    }
    //X button closes intake
    else if (xboxCont.getBumperPressed(Hand.kLeft))
    {
      IntakeSystem.closeIntake();
      SmartDashboard.putString("Intake Status", "Closed");
    }

    //Left trigger does suction
    if (xboxCont.getTriggerAxis(Hand.kRight)>0.75)
    {
      System.out.println("Suction on!");
      IntakeSystem.Suction();
    }
    //Right trigger shoots
    else if (xboxCont.getTriggerAxis(Hand.kLeft)>0.75)
    {
      System.out.println("Shooting!");
      IntakeSystem.Shoot();
    }
    //Stops intake motors
    else
    {
      IntakeSystem.Stop();
    } 
    

    ///CLIMBER
    if(sticks.left.getRawButtonPressed(10) && (frontClimberTime == backClimberTime))
    {
      frontClimbingDone = false;
      
      backClimbingDone = false;
    }

    //Front
    if(sticks.left.getRawButtonPressed(2))
    {
      frontClimbingDone = false;
    }

    if(!frontClimbingDone)
    {
      if(!frontStartedClimbChange)
      {//Runs once at beginning
        //First
        frontClimberState = true;
        //Second
        frontClimberTopState = false;

        frontStartedClimbChange = true;
      }
      else
      {//Runs iteratively
        if(frontClimberTime)
        {//Moving to extended
            if(frontClimberState)//First
            {
              Climber.frontPistonBackward();
              Climber.goUpFront();
              frontClimberState = false;
              frontClimbTimerExtending.reset();
              frontClimbTimerExtending.start();
            }

            if(frontClimbTimerExtending.get() >= .5)//Second
            {
              Climber.frontPistonForward();
              frontClimbTimerExtending.stop();
            }

            if(!topFrontClimber.get())//Third and Final
            {
              //Final stage
              Climber.stopFront();
  
              frontClimbingDone = true;
              frontStartedClimbChange = false;

              //Change to current state
              frontClimberTime = false;//Extended
            }
        }
        else
        {//Moving to Retracted
          if(frontClimberState)//First
          {
            Climber.frontPistonBackward();
            Climber.goDownFront();
            frontClimberState = false;
            frontClimberTopState = true;
            frontClimbTimerExtending.reset();
            frontClimbTimerExtending.start();
          }

          if(frontClimberTopState && frontClimbTimerExtending.get() >= .5)//Second
          {
            Climber.frontPistonForward();
            frontClimbTimerExtending.stop();
          }

          if(!bottomFrontClimber.get())//Third
          {
            frontClimberTopState = false;
            frontClimbTimerExtending.reset();
            frontClimbTimerExtending.start();
          }

          if(!frontClimberTopState && frontClimbTimerExtending.get() >= .1)//Fourth and Final
          {
            //Final Stage
            Climber.stopFront();

            frontClimbingDone = true;
            frontStartedClimbChange = false;

            //Change to current state
            frontClimberTime = true;//Retracted
          }
        }
      }
    }
    else
    {
      Climber.stopFront();
    }



    //back
    if(sticks.left.getRawButtonPressed(3))
    {
      backClimbingDone = false;
    }

    if(!backClimbingDone)
    {
      if(!backStartedClimbChange)
      {//Runs once at beginning
        //First
        backClimberState = true;
        //Second
        backClimberTopState = false;

        backStartedClimbChange = true;
      }
      else
      {//Runs iteratively
        if(backClimberTime)
        {//Moving to extended
            if(backClimberState)//First
            {
              Climber.backPistonBackward();
              Climber.goUpBack();
              backClimberState = false;
              backClimbTimerExtending.reset();
              backClimbTimerExtending.start();
            }

            if(backClimbTimerExtending.get() >= .5)//Second
            {
              Climber.backPistonForward();
              backClimbTimerExtending.stop();
            }

            if(!topBackClimber.get())//Third and Final
            {
              //Final stage
              Climber.stopBack();
  
              backClimbingDone = true;
              backStartedClimbChange = false;

              //Change to current state
              backClimberTime = false;//Extended
            }
        }
        else
        {//Moving to Retracted
          if(backClimberState)//First
          {
            Climber.backPistonBackward();
            Climber.goDownBack();
            backClimberState = false;
            backClimberTopState = true;
            backClimbTimerExtending.reset();
            backClimbTimerExtending.start();
          }

          if(backClimberTopState && backClimbTimerExtending.get() >= .5)//Second
          {
            Climber.backPistonForward();
            backClimbTimerExtending.stop();
          }

          if(!bottomBackClimber.get())//Third
          {
            backClimberTopState = false;
            backClimbTimerExtending.reset();
            backClimbTimerExtending.start();
          }

          if(!backClimberTopState && backClimbTimerExtending.get() >= .1)//Fourth and Final
          {
            //Final Stage
            Climber.stopBack();

            backClimbingDone = true;
            backStartedClimbChange = false;

            //Change to current state
            backClimberTime = true;//Retracted
          }
        }
      }
    }
    else
    {
      Climber.stopBack();
    }
    

    /*
    if(sticks.left.getRawButtonPressed(2))
    {//Switches States
      frontClimberTime = !frontClimberTime;
    }
    if(!frontClimberTime)
    {//Retracting
      if(frontClimberTopState)
      {//Past top sensor
          if(frontClimbTimerExtending.get() >= .001)
          {
            Climber.stopFront();
            frontClimbTimerExtending.stop();
          }
      }
      else if(!bottomFrontClimber.get())
      {//At retracted
        Climber.goUpFront();
        frontClimberState = false;
        System.out.println("BottomFrontClimber");
      }
      else if(topFrontClimber.get() && bottomFrontClimber.get() && frontClimberState)
      {//Below bottom sensor
        Climber.frontPistonBackward();
        Climber.goUpFront();
        System.out.println("belowSensor");
        if(climberWaitFront.get() == 0)
        {
          climberWaitFront.reset();
          climberWaitFront.start();
        }
      }
      else if(topFrontClimber.get() && bottomFrontClimber.get() && !frontClimberState)
      {//Inbetween sensors
        Climber.goUpFront();
        System.out.println("BetweenSensors");
      }
      else if(!topFrontClimber.get())
      {//At top sensor
        Climber.goUpFront();
        frontClimberTopState = true;
        frontClimbTimerExtending.reset();
        frontClimbTimerExtending.start();
        System.out.println("TopSensor");
      }

      if(climberWaitFront.get() > .2)
      {
        Climber.frontPistonForward();
        climberWaitFront.stop();
      }
    }
    else
    {//Extending
      if(!topFrontClimber.get())
      {//At top SENSOR
        Climber.goDownFront();
        frontClimberTopState = false;
        System.out.println("AtTopSensor EXTEND");
      }
      else if(frontClimberTopState)
      {
        Climber.frontPistonBackward();
        Climber.goDownFront();
        if(climberWaitFront.get() == 0)
        {
          climberWaitFront.reset();
          climberWaitFront.start();
        }
        System.out.println("aboveSensor EXTEND");
      }
      else if(bottomFrontClimber.get() && topFrontClimber.get() && !frontClimberState)
      {//no mans LAND
        Climber.goDownFront();
        System.out.println("BetweenSensors EXTEND");
      }
      else if(!bottomFrontClimber.get())
      {//At Bottom Sensor
        Climber.stopFront();
        frontClimberState = true;
        System.out.println("AtBottomSensor EXTEND");
      }

      if(climberWaitFront.get() > .2)
      {
        Climber.frontPistonForward();
        climberWaitFront.stop();
      }
    }
    
    if(sticks.left.getRawButtonPressed(3))
    {
      backClimberTime = !backClimberTime;
    }
    if(!backClimberTime)
    {//Retracting
      if(backClimberTopState)
      {//Past top sensor
          if(backClimbTimerExtending.get() >= .001)
          {
            Climber.stopBack();
            backClimbTimerExtending.stop();
          }
      }
      else if(!bottomBackClimber.get())
      {//At retracted
        Climber.backPistonForward();
        Climber.goUpBack();
        backClimberState = false;
        System.out.println("BottomBackClimber");
      }
      else if(topBackClimber.get() && bottomBackClimber.get() && backClimberState)
      {//Below bottom sensor
        Climber.backPistonBackward();
        Climber.goUpBack();
        System.out.println("belowSensor");
      }
      else if(topBackClimber.get() && bottomBackClimber.get() && !backClimberState)
      {//Inbetween sensors
        Climber.goUpBack();
        System.out.println("BetweenSensors");
      }
      else if(!topBackClimber.get())
      {//At top sensor
        Climber.goUpBack();
        backClimberTopState = true;
        backClimbTimerExtending.reset();
        backClimbTimerExtending.start();
        System.out.println("TopSensor");
      }
    }
    else
    {//Extending
      if(!topBackClimber.get())
      {//At top SENSOR
        Climber.backPistonForward();
        Climber.goDownBack();
        backClimberTopState = false;
      }
      else if(backClimberTopState)
      {
        Climber.backPistonBackward();
        Climber.goDownBack();
      }
      else if(bottomBackClimber.get() && topBackClimber.get() && !backClimberState)
      {//no mans LAND
        Climber.goDownBack();
      }
      else if(!bottomBackClimber.get())
      {//At Bottom Sensor
        Climber.stopBack();
        backClimberState = true;
      }
    }
    */

    //Drivetrain Pneumatics
    if(sticks.right.getRawButtonPressed(3))
    {
      driveTrain.set(DoubleSolenoid.Value.kForward);
    }
    else if(sticks.right.getRawButtonPressed(4))
    {
      driveTrain.set(DoubleSolenoid.Value.kReverse);
    }
    else if(sticks.right.getRawButtonPressed(5))
    {
      driveTrain.set(DoubleSolenoid.Value.kOff);
    }
    
    //Compressor On/Off
    if(sticks.left.getRawButtonPressed(4))
    {
      pneum.ChangeCompressorState(true);//on
    }
    else if(sticks.left.getRawButtonPressed(6))
    {
      pneum.ChangeCompressorState(false);//off
    }

    //Climber Movement
    if(sticks.right.getPOV() == 0)
    {
      Climber.rollerForward();
    }
    else if(sticks.right.getPOV() == 180)
    {
      Climber.rollerBackward();
    }
    else
    {
      Climber.stopRoller();
    }



    //Power Management
    PowerManagement.sendWattageToShuffleBoard();
    PowerManagement.possibleBrownOut();

  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic()
  {
    
  }


  /*public void cameraSetup()
  {
    try 
		{
      
      cam0.setVideoMode(PixelFormat.kGray, 640, 480, 30);
      cam1.setVideoMode(PixelFormat.kGray, 320, 240, 30);
		}
		catch (Exception e)
		{
			//System.out.println("Camera Error: " + e.getMessage());
		}
  }*/
}