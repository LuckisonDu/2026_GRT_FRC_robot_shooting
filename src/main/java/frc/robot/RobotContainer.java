package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandPS5Controller;
import frc.robot.subsystem.ShooterConstants.*;
import frc.robot.subsystem.ShooterSubsystem;

public class RobotContainer {
  private final ShooterSubsystem ShooterSubsystem = new ShooterSubsystem();
  private final CommandPS5Controller driverController = new CommandPS5Controller(OIConstants.DRIVER_CONTROLLER_PORT);

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    ShooterSubsystem.setDefaultCommand(
        ShooterSubsystem.ShootingCommand(
            () -> -driverController.getCircleButtonReleased(),
          
          
          
        ShooterSubsystem.ShootingCommand2(
            () -> -driverController.getCrossButtonReleased(),
          
          );
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
