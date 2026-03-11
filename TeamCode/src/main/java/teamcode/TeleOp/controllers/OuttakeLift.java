package teamcode.TeleOp.controllers;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.TouchSensor;

import teamcode.TeleOp.RobotMap;

@Config
public class OuttakeLift {
    //this controls the position of the drawer; it can be either in the init position or collection position
    public enum liftStatus {
        INIT,
        POWEROFF,

        HIGH,

        MEDIUM;
    }

    //At init the initial position is INIT
    public static liftStatus currentStatus = liftStatus.INIT;
    public liftStatus previousStatus = null;

    public static DcMotorEx rightLift = null;
    public static DcMotorEx leftLift = null;


    //Initial position of both motors
    public int init_position = 0;
    //Collect position of both motors
    public int high_position = 2800;
    public static int medium_position = 1150;

    public static int velocidadbrazo = 1800;
    public static int velocidadbrazoizq = 2000;


    public int currentPosition = init_position;

    //We create the drawers variables to use them later
    public OuttakeLift(RobotMap robot) {
        this.rightLift= robot.rightLift;
        this.leftLift= robot.leftLift;

    }

    public void update(int target) {

        //If the status changes
        if (currentStatus != previousStatus) {
            previousStatus = currentStatus;


            switch (currentStatus) {

                case INIT: {


                    this.rightLift.setTargetPosition(init_position);
                    this.rightLift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
                    this.rightLift.setVelocity(2000);

                    this.leftLift.setTargetPosition(init_position);
                    this.leftLift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
                    this.leftLift.setVelocity(2000);

                    currentPosition = init_position;
                    break;
                }

                case POWEROFF: {
                    rightLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    this.rightLift.setPower(0);
                    leftLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    this.leftLift.setPower(0);
                    break;
                }

                case HIGH: {

                    this.rightLift.setTargetPosition(high_position);
                    this.rightLift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
                    this.rightLift.setVelocity(2000);

                    this.leftLift.setTargetPosition(high_position);
                    this.leftLift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
                    this.leftLift.setVelocity(2000);

                    currentPosition = high_position;
                    break;
                }
                case MEDIUM: {

                    this.rightLift.setTargetPosition(medium_position);
                    this.rightLift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
                    this.rightLift.setVelocity(2000);

                    this.leftLift.setTargetPosition(medium_position);
                    this.leftLift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
                    this.leftLift.setVelocity(2000);

                    currentPosition = medium_position;
                    break;
                }
            }

        }
    }
}
