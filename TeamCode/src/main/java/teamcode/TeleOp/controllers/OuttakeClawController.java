package teamcode.TeleOp.controllers;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.Servo;

import teamcode.TeleOp.RobotMap;
@Config
public class OuttakeClawController {
    //this controls whether the claw is open or closed
    public enum outtakeClawStatus{
        OPEN,
        CLOSED;
    }

    public static outtakeClawStatus currentStatus= outtakeClawStatus.CLOSED;
    public outtakeClawStatus previousStatus=null;

    public Servo outtakeClaw = null;

    public static double open_position=0.3;
    public static double closed_position=0.5;

    public OuttakeClawController(RobotMap robot) {
        this.outtakeClaw =robot.outtakeClaw;
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
                    this.outtakeClaw.setPosition(open_position);
                    break;
                }

                case CLOSED:
                {
                    this.outtakeClaw.setPosition(closed_position);
                    break;
                }

            }
        }

    }
}
