package frc.robot.subsystem;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystem.ShooterConstants.*;
import java.util.function.DoubleSupplier;

public class ShooterSubsystem extends SubsystemBase {
  // initialize devices on the rio can bus
  final CANBus kCANBus = CANBus.roboRIO();
  final TalonFX m_leftLeader = new TalonFX(ShooterConstants.LEFT_LEADER_ID, kCANBus);
  final TalonFX m_right = new TalonFX(ShooterConstants.LEFT_ID, kCANBus);
  final TalonFX m_leftFollower = new TalonFX(ShooterConstants.LEFT_FOLLOWER_ID, kCANBus);



  private final var configs = new TalonFXConfiguration();


  private final DifferentialDrive drive = new DifferentialDrive(leftLeader::set, rightLeader::set);






  public ShooterSubsystem() {
    var configs = new TalonFXConfiguration();
    // in init function, set slot 0 gains
    configs.Slot0.kP = PID.kP; // An error of 1 rotation results in 2.4 V output
    configs.Slot0.kI = PID.kI; // no output for integrated error
    configs.Slot0.kD = PID.kD; // A velocity of 1 rps results in 0.1 V output
    // duty cycle whihc is about electricity

    m_leftLeader.getConfigurator().apply(configs);
    m_right.getConfigurator().apply(configs);
    m_leftFollower.getConfigurator().apply(configs);

  final PositionVoltage m_request = new PositionVoltage(0).withSlot(0);

  // set position to 10 rotations
  m_talonFX.setControl(m_request.withPosition(10));



  }

  public void stop() {
    drive.stopMotor();
  }


  


  public void RotationAndPiviot(double RotationSpeed, double PiviotSpeed) {
    // Trapezoid profile with max velocity 80 rps, max accel 160 rps/s
    final TrapezoidProfile m_profile = new TrapezoidProfile(
      new TrapezoidProfile.Constraints(80, 160)
    );
    // Final target of 200 rot, 0 rps
    TrapezoidProfile.State m_goal = new TrapezoidProfile.State(PiviotSpeed, PiviotSpeed);
    TrapezoidProfile.State m_goal = new TrapezoidProfile.State(PiviotSpeed, PiviotSpeed);
    TrapezoidProfile.State m_setpoint = new TrapezoidProfile.State();

    // create a position closed-loop request, voltage output, slot 0 configs
    final PositionVoltage m_request = new PositionVoltage(0).withSlot(0);

    // calculate the next profile setpoint
    m_setpoint = m_profile.calculate(0.020, m_setpoint, m_goal);

    // send the request to the device
    m_request.Position = m_setpoint.position;
    m_request.Velocity = m_setpoint.velocity;
    if(RotationSpeed = 0)
    m_talonFX.setControl(m_request);
  }
  public Command ShootingCommand(boolean button) {
    if(button){
      return run(
        () -> RotationAndPiviot(ShooterConstants.RotationSpeed1, ShooterConstants.PiviotSpeed1),
      );
    }else{}
  }

  public Command ShootingCommand2(boolean button) {
    if(button){
      return run(
        () -> RotationAndPiviot(ShooterConstants.RotationSpeed2, ShooterConstants.PiviotSpeed2),
      );
    }else{}
  }
  
}
