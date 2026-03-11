package teamcode.TeleOp.controllers;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.Servo;

import teamcode.TeleOp.RobotMap;
@Config
public class IntakeClawController {
    //this controls whether the claw is open or closed
    public enum clawStatus{
        OPEN,
        CLOSED;
    }

    public static clawStatus currentStatus= clawStatus.OPEN;
    public clawStatus previousStatus=null;

    public Servo intakeClaw = null;

    public static double open_position=0.5;
    public static double closed_position=0.71;

    public IntakeClawController(RobotMap robot) {
        this.intakeClaw =robot.intakeClaw;
    }

    public void update()
    {
        if(currentStatus!=previousStatus)
        {
            previousStatus=currentStatus;


            switch(currentStatus)
            {
                case OPEN:
                {
                    this.intakeClaw.setPosition(open_position);
                    break;
                }

                case CLOSED:
                {
                    this.intakeClaw.setPosition(closed_position);
                    break;
                }

            }
        }

    }
}
