/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.internal.HardwareTimer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.Library.Chassis.TankDrive;
import frc.Library.Controls.JoystickTank;
import frc.Library.Controllers.*;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends IterativeRobot {
  public WPI_TalonSRX[] talons;


  public TankDrive TankDrive;
  public Intake IntakeSystem;
  public Elevator ElevatorSystem;
  public Climber Climber;

  public JoystickTank sticks = new JoystickTank(1, 2);

  public XboxController xboxCont = new XboxController(0);


  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  public PneumaticsControl pneum;
  public DoubleSolenoid intake1;
  public DoubleSolenoid intake2;
  public DoubleSolenoid climber1;
  public DoubleSolenoid climber2;
  public DoubleSolenoid driveTrain1;
  public DoubleSolenoid driveTrain2;
  

  // Debug code
  private Timer timer = new Timer();
  private double lastTime;

  

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() 
  {
    SmartDashboard.putData("Auto choices", m_chooser);

    pneum = new PneumaticsControl(Constant.PneumaticCompressor, Constant.PressureSensor, Constant.PneumaticOffset);

    this.talons = new WPI_TalonSRX[Constant.CANLength];
    for(int i = 0; i < talons.length; i++)
    {
      this.talons[i] = new WPI_TalonSRX(i);
    }
    Drive.SetFullCAN(talons);
    int[] lDriveMotors = {Constant.Left0, Constant.Left1, Constant.Left2};
    Drive lDrive = new Drive(lDriveMotors);
    int[] rDriveMotors = {Constant.Right0, Constant.Right1, Constant.Right2};
    Drive rDrive = new Drive(rDriveMotors);

    this.TankDrive = new TankDrive(lDrive, rDrive);
    this.IntakeSystem = new Intake();
    this.IntakeSystem.Setup();
    //this.ElevatorSystem = new Elevator(xboxCont);
    //this.ElevatorSystem.Setup();

    timer.start();
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
    pneum.DisplayPSI();
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
    m_autoSelected = m_chooser.getSelected();
    // autoSelected = SmartDashboard.getString("Auto Selector",
    // defaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic()
  {
    switch (m_autoSelected) {
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic()
  {
    System.out.println(timer.get());


    TankDrive.drive(sticks.GetDrive());
    //ElevatorSystem.handleInputs();
    //A button opens intake
    if (xboxCont.getRawButton(Constant.BButton)==true)
    {
      IntakeSystem.openIntake();
    }
    //X button closes intake
    else if (xboxCont.getRawButton(Constant.XButton)==true)
    {
      IntakeSystem.closeIntake();
    }

    //Left trigger does suction
    if (xboxCont.getTriggerAxis(Hand.kLeft)>0.5)
    {
      System.out.println("Suction on!");
      IntakeSystem.Suction();
    }
    //Right trigger shoots
    else if (xboxCont.getTriggerAxis(Hand.kRight)>0.5)
    {
      System.out.println("Shooting!");
      IntakeSystem.Shoot();
    }
    //Stops intake motors
    else
    {
      IntakeSystem.Stop();
    } 
 
    //CLIMBER
    if(sticks.left.getRawButton(2))
    {
      Climber.goUpFront();
    }
    else if(sticks.left.getRawButton(3))
    {
      Climber.goDownFront();
    }

    if(sticks.right.getRawButton(2))
    {
      Climber.goUpBack();
    }
    else if(sticks.right.getRawButton(3))
    {
      Climber.goDownBack();
    }

    if(sticks.right.getPOV()<90 || sticks.right.getPOV()>270)
    {
      Climber.rollerForward();
    }
    else if(sticks.right.getPOV()>90 || sticks.right.getPOV()<270)
    {
      Climber.rollerBackward();
    }
  }
  /**
   * This function is called periodically during test mode.
   */

  @Override
  public void testPeriodic()
  {
  }

/*
sansascii
  █████████████▀▀▀▀▀▀▀▀▀▀▀▀▀███████████
  ████████▀▀░░░░░░░░░░░░░░░░░░░▀▀██████
  ██████▀░░░░░░░░░░░░░░░░░░░░░░░░░▀████
  █████░░░░░░░░░░░░░░░░░░░░░░░░░░░░░███
  ████░░░░░▄▄▄▄▄▄▄░░░░░░░░▄▄▄▄▄▄░░░░░██
  ████░░▄██████████░░░░░░██▀░░░▀██▄░░██
  ████░░███████████░░░░░░█▄░░▀░░▄██░░██
  █████░░▀▀███████░░░██░░░██▄▄▄█▀▀░░███
  ██████░░░░░░▄▄▀░░░████░░░▀▄▄░░░░░████
  █████░░░░░█▄░░░░░░▀▀▀▀░░░░░░░█▄░░░███
  █████░░░▀▀█░█▀▄▄▄▄▄▄▄▄▄▄▄▄▄▀██▀▀░░███
  ██████░░░░░▀█▄░░█░░█░░░█░░█▄▀░░░░██▀▀
  ▀░░░▀██▄░░░░░░▀▀█▄▄█▄▄▄█▄▀▀░░░░▄█▀░░░
  ▄▄▄░░░▀▀██▄▄▄▄░░░░░░░░░░░░▄▄▄███░░░▄█
  ██████▄▄░░▀█████▀█████▀██████▀▀░░▄███
  ██████████▄░░▀▀█▄░░░░░▄██▀▀▀░▄▄▄███▀▄
  ███████████░██░▄██▄▄▄▄█▄░▄░████████░█
  ヨルダンは気力追跡をする
  */
}
