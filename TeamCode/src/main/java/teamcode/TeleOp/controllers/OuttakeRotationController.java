package teamcode.TeleOp.controllers;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.Servo;

import teamcode.TeleOp.RobotMap;

@Config
public class OuttakeRotationController {
//intakelift
    public enum ServoRotationStatus {
        INIT,
        SCORE
    }

    public static ServoRotationStatus currentStatus = ServoRotationStatus.INIT;
    public ServoRotationStatus previousStatus = null;

    public Servo outtakeRotationClaw = null;

    public static double init_position = 0;
    public static double score_position = 0.7;

    public OuttakeRotationController(RobotMap robot) {
        this.outtakeRotationClaw = robot.outtakeRotationClaw;
    }

    public void update() {
        if (currentStatus != previousStatus) {
            previousStatus = currentStatus;

            switch (currentStatus) {
                case INIT: {
                    this.outtakeRotationClaw.setPosition(init_position);
                    break;
                }

                case SCORE: {
                    this.outtakeRotationClaw.setPosition(score_position);
                    break;
                }
            }
        }
    }
}
