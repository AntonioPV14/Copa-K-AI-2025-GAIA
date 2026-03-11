package teamcode.TeleOp.controllers;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.Servo;


import teamcode.TeleOp.RobotMap;
@Config
public class IntakeRotationController {
        //this controls whether the claw is open or closed
    public enum ServoRotationStatus{
        HORIZONTAL,
            RIGHT,
            VERTICAL,
        LEFT;
    }

    public static ServoRotationStatus currentStatus= ServoRotationStatus.HORIZONTAL;
    public ServoRotationStatus previousStatus=null;

    public Servo intakeRotationClaw = null;

    public static double horizontal_position=0.16;
    public static double left_position=0.25;
    public static double right_position = 0;
    public static double vertical_position = 0.48;

    public IntakeRotationController(RobotMap robot) {
        this.intakeRotationClaw =robot.intakeRotationClaw;
    }

    public void update()
    {
        if(currentStatus!=previousStatus)
        {
            previousStatus=currentStatus;


            switch(currentStatus)
            {
                case HORIZONTAL:
                {
                    this.intakeRotationClaw.setPosition(horizontal_position);
                    break;
                }

                case RIGHT:
                {
                    this.intakeRotationClaw.setPosition(right_position);
                    break;
                }
                case LEFT:
                {
                    this.intakeRotationClaw.setPosition(left_position);
                    break;
                }

                case VERTICAL:
                {
                    this.intakeRotationClaw.setPosition(vertical_position);
                    break;
                }

            }
        }

    }
}
