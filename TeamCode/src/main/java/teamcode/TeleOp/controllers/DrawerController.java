package teamcode.TeleOp.controllers;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import teamcode.TeleOp.RobotMap;

@Config
public class DrawerController {
    //this controls the position of the drawer; it can be either in the init position or collection position
    public enum drawerStatus {
        INIT,
        COLLECT,

        TRANSFER,;
    }

    //At init the initial position is INIT
    public static drawerStatus currentStatus = drawerStatus.INIT;
    public drawerStatus previousStatus = null;

    public DcMotorEx intakeArm = null;
    //Initial position of both motors
    public static int init_position = 0;
    public static int transfer_position = 0 ;

    //Collect position of both motors
    public static int collect_position = 1300;
    public int currentPosition = init_position;

    //We create the drawers variables to use them later
    public DrawerController(RobotMap robot) {
        this.intakeArm= robot.intakeArm;
    }

    public void update(int target) {

        //If the status changes
        if (currentStatus != previousStatus) {
            previousStatus = currentStatus;


            switch (currentStatus) {

                case INIT: {
                    this.intakeArm.setTargetPosition(init_position);
                    this.intakeArm.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
                    this.intakeArm.setVelocity(2000);

                    currentPosition = init_position;
                    break;
                }
                case COLLECT: {
                    this.intakeArm.setTargetPosition(collect_position);
                    this.intakeArm.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
                    this.intakeArm.setVelocity(2000);

                    currentPosition = collect_position;
                    break;
                }

                case TRANSFER: {
                    this.intakeArm.setTargetPosition(transfer_position);
                    this.intakeArm.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
                    this.intakeArm.setVelocity(2000);

                    currentPosition = transfer_position;
                    break;
                }
            }

        }
    }
}
