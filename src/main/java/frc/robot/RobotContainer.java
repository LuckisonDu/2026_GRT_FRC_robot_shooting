package frc.robot;

import com.ctre.phoenix6.CANBus;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandPS5Controller;
import frc.robot.subsystem.ShooterConstants.*;
import frc.robot.subsystem.ShooterSubsystem;

public class RobotContainer {
  final CANBus kCANBus = new CANBus("BetaBot");
  private final ShooterSubsystem ShooterSubsystem = new ShooterSubsystem(kCANBus);
  private final CommandPS5Controller driverController = new CommandPS5Controller(OIConstants.DRIVER_CONTROLLER_PORT);


  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    driverController.circle().onTrue(
        ShooterSubsystem.ShootingCommand());

    driverController.cross().onTrue(
        ShooterSubsystem.ShootingCommand2());

    driverController.triangle().onTrue(
        ShooterSubsystem.runOnce(ShooterSubsystem::stop));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}