package frc.robot.subsystem;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.FeedbackSensorSourceValue;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.SensorDirectionValue;
import edu.wpi.first.math.controller.BangBangController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystem.ShooterConstants.*;


public class ShooterSubsystem extends SubsystemBase {
  // initialize devices on the rio can bus

  // don't put it here and also its not suppose to be roborio but in main idk what it is ask daniel 
  CANBus kCANBus;
  TalonFX m_leftLeader = new TalonFX(ShooterConstants.LEFT_LEADER_ID, kCANBus);
  TalonFX m_leftFollower = new TalonFX(ShooterConstants.LEFT_FOLLOWER_ID, kCANBus);
  TalonFX m_right = new TalonFX(ShooterConstants.RIGHT_ID, kCANBus);
  CANcoder m_cancoder = new CANcoder(ShooterConstants.CANCODER_ID, kCANBus);
  PositionVoltage m_request = new PositionVoltage(0).withSlot(0);
  BangBangController bangBang = new BangBangController();






  public ShooterSubsystem() {
    m_leftLeader = new TalonFX(ShooterConstants.LEFT_LEADER_ID, kCANBus);
    m_leftFollower = new TalonFX(ShooterConstants.LEFT_FOLLOWER_ID, kCANBus);
    m_right = new TalonFX(ShooterConstants.RIGHT_ID, kCANBus);
    m_cancoder = new CANcoder(ShooterConstants.CANCODER_ID, kCANBus);

    //useless change
    m_cancoder.setPosition(m_cancoder.getAbsolutePosition().waitForUpdate(0.2).getValueAsDouble());

    

    //Pivoit
    TalonFXConfiguration configs = new TalonFXConfiguration();
    configs.Slot0.kP = PID.kP;
    configs.Slot0.kI = PID.kI;
    configs.Slot0.kD = PID.kD;
    
    configs.Feedback.FeedbackRemoteSensorID = ShooterConstants.CANCODER_ID;
    configs.Feedback.FeedbackSensorSource = FeedbackSensorSourceValue.RemoteCANcoder;
    configs.Feedback.SensorToMechanismRatio = ShooterConstants.PIVOT_SENSOR_RATIO;
    configs.CurrentLimits.StatorCurrentLimit = ShooterConstants.CURRENT_LIMIT_AMPS;
    m_right.getConfigurator().apply(configs);

    TalonFXConfiguration flywheelConfig = new TalonFXConfiguration();
    flywheelConfig.Feedback.SensorToMechanismRatio = ShooterConstants.FLYWHEEL_GEAR_RATIO;
    flywheelConfig.CurrentLimits.StatorCurrentLimit = ShooterConstants.CURRENT_LIMIT_AMPS;
    m_leftLeader.getConfigurator().apply(flywheelConfig);
    m_leftFollower.getConfigurator().apply(flywheelConfig);

    // match 0.02second updates
    m_leftLeader.getMotorVoltage().setUpdateFrequency(50);
    m_leftLeader.getVelocity().setUpdateFrequency(50);
    //from aligned to opposed
    m_leftFollower.setControl(new Follower(ShooterConstants.LEFT_LEADER_ID, MotorAlignmentValue.Opposed));

    stop();
  }



  public void stop() {
    //stop motor
    m_leftLeader.setVoltage(0);
    m_right.stopMotor();
  }


  


  public void RotationAndPiviot(double RotationSpeed, double PiviotAngle) {
    m_right.setControl(m_request.withPosition(PiviotAngle));
    double output = bangBang.calculate(m_leftLeader.getVelocity().getValueAsDouble(), RotationSpeed);
    m_leftLeader.setVoltage(output * ShooterConstants.FLYWHEEL_ON_VOLTS);
  }


  public Command ShootingCommand(CANBus kCANBus) {
    this.kCANBus = kCANBus;
    return runEnd(
        () -> RotationAndPiviot(
            ShooterConstants.RotationSpeed1,
            ShooterConstants.PiviotAngle1),
        this::stop);
  }

  public Command ShootingCommand2(CANBus kCANBus) {
    this.kCANBus = kCANBus;
    return runEnd(
        () -> RotationAndPiviot(
            ShooterConstants.RotationSpeed2,
            ShooterConstants.PiviotAngle2),
        this::stop);
  }
  
}
