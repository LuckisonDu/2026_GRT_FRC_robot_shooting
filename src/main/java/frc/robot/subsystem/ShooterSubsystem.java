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
import frc.robot.Constants.ShooterConstants;
import java.util.function.DoubleSupplier;

public class ShooterSubsystem extends SubsystemBase {
  private final SparkMax leftLeader = new SparkMax(DriveConstants.LEFT_LEADER_ID, MotorType.kBrushless);
  private final SparkMax leftFollower = new SparkMax(DriveConstants.LEFT_FOLLOWER_ID, MotorType.kBrushless);
  private final SparkMax right = new SparkMax(DriveConstants.RIGHT_ID, MotorType.kBrushless);





  private final DifferentialDrive drive = new DifferentialDrive(leftLeader::set, rightLeader::set);






  public ShooterSubsystem() {
    SparkMaxConfig leftLeaderConfig = new SparkMaxConfig();
    leftLeaderConfig
        .idleMode(IdleMode.kBrake)
        .smartCurrentLimit(DriveConstants.CURRENT_LIMIT_AMPS)

    SparkMaxConfig rightConfig = new SparkMaxConfig();
    rightLeaderConfig
        .idleMode(IdleMode.kBrake)
        .smartCurrentLimit(DriveConstants.CURRENT_LIMIT_AMPS)

    SparkMaxConfig leftFollowerConfig = new SparkMaxConfig();
    leftFollowerConfig
        .idleMode(IdleMode.kBrake)
        .smartCurrentLimit(DriveConstants.CURRENT_LIMIT_AMPS)
        .follow(leftLeader);





    leftLeader.configure(leftLeaderConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    leftFollower.configure(leftFollowerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    right.configure(rightConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    drive.setMaxOutput(DriveConstants.MAX_OUTPUT);
    drive.setDeadband(DriveConstants.DEADBAND);
  }

  public void Rotation(double speed) {





    drive.arcadeDrive(forward, rotation);



    
  }

  public void stop() {
    drive.stopMotor();
  }

  public Command RotationCommand(double speed) {
    return run(() -> Rotation(speed);
  }

  
}
