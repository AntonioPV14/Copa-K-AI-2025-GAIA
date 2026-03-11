package teamcode.TeleOp.controllers;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.Servo;

import teamcode.TeleOp.RobotMap;

@Config
public class OuttakeWristController {

    public enum ServoRotationStatus {
        GRAB,
        SCORE,
        TRANSFER,
        INIT,

    }

    public static ServoRotationStatus currentStatus = ServoRotationStatus.INIT;
    public ServoRotationStatus previousStatus = null;

    public Servo outtakeWrist = null;

    public static double grab_position = 0.45;
    public static double score_position = 0.25;
    public static double transfer_position = 0.8;
    public static  double init_position = 0.8;

    public OuttakeWristController(RobotMap robot) {
        this.outtakeWrist = robot.outtakeWrist;
    }

    public void update() {
        if (currentStatus != previousStatus) {
            previousStatus = currentStatus;

            switch (currentStatus) {
                case GRAB: {
                    this.outtakeWrist.setPosition(grab_position);
                    break;
                }

                case SCORE: {
                    this.outtakeWrist.setPosition(score_position);
                    break;
                }

                case TRANSFER: {
                    this.outtakeWrist.setPosition(transfer_position);
                    break;
                }

                case INIT: {
                    this.outtakeWrist.setPosition(init_position);
                    break;
                }
            }
        }
    }
}
