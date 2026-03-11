package teamcode.TeleOp.controllers;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.Servo;

import teamcode.TeleOp.RobotMap;

@Config
public class IntakeWristController {

    public enum ServoRotationStatus {
        INIT,
        HOVER,
        GRAB
    }

    public static ServoRotationStatus currentStatus = ServoRotationStatus.INIT;
    public ServoRotationStatus previousStatus = null;

    public Servo intakeWrist = null;

    public static double init_position = 0.7;
    public static double grab_position = 0.05;
    public static double hover_position = 0.35;

    public IntakeWristController(RobotMap robot) {
        this.intakeWrist = robot.intakeWrist;
    }

    public void update() {
        if (currentStatus != previousStatus) {
            previousStatus = currentStatus;

            switch (currentStatus) {
                case INIT: {
                    this.intakeWrist.setPosition(init_position);
                    break;
                }

                case GRAB: {
                    this.intakeWrist.setPosition(grab_position);
                    break;
                }
                case HOVER:{
                    this.intakeWrist.setPosition(hover_position);
                }
            }
        }
    }
}
