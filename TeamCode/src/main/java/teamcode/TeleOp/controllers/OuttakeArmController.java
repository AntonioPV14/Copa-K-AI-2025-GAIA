package teamcode.TeleOp.controllers;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.Servo;

import teamcode.TeleOp.RobotMap;

@Config
public class OuttakeArmController {

    public enum OuttakeArmStatus {
        INIT,
        SCORE,
        GRAB,
        TRANSFER,
        CLOUD
    }

    public static OuttakeArmStatus currentStatus = OuttakeArmStatus.INIT;
    public OuttakeArmStatus previousStatus = null;

    public Servo leftOuttakeArm = null;
    public Servo rightOuttakeArm = null;

    public static double init_position = 0.2;
    public static double score_position = 0.97;
    public static double grab_position = 0;
    public static double transfer_position = 0.2;
    public static double cloud_position = 0.3;

    public double currentPosition = init_position;

    public OuttakeArmController(RobotMap robot) {
        this.leftOuttakeArm = robot.leftOuttakeArm;
        this.rightOuttakeArm = robot.rightOuttakeArm;

    }

    public void update() {
        if (currentStatus != previousStatus) {
            previousStatus = currentStatus;

            switch (currentStatus) {
                case INIT: {
                    leftOuttakeArm.setPosition(init_position);
                    rightOuttakeArm.setPosition(1-init_position);
                    currentPosition = init_position;
                    break;
                }

                case SCORE: {
                    leftOuttakeArm.setPosition(score_position);
                    rightOuttakeArm.setPosition(1-score_position);
                    currentPosition = score_position;
                    break;
                }

                case GRAB: {
                    leftOuttakeArm.setPosition(grab_position);
                    rightOuttakeArm.setPosition(1-grab_position);
                    currentPosition = grab_position;
                    break;
                }

                case TRANSFER: {
                    leftOuttakeArm.setPosition(transfer_position);
                    rightOuttakeArm.setPosition(1-transfer_position);
                    currentPosition = transfer_position;
                    break;
                }

                case CLOUD: {
                    leftOuttakeArm.setPosition(cloud_position);
                    rightOuttakeArm.setPosition(1-cloud_position);
                    currentPosition = cloud_position;
                    break;
                }
            }
        }
    }
}
