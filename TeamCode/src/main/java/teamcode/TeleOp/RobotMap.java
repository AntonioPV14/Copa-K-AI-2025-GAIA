package teamcode.TeleOp;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class RobotMap {

   //intake
    public Servo intakeWrist;
    public Servo intakeClaw;
    public Servo intakeRotationClaw;
    public DcMotorEx intakeArm;

    //outtake
    public Servo outtakeClaw;
    public Servo leftOuttakeArm;
    public Servo rightOuttakeArm;
    public Servo outtakeWrist;
    public Servo outtakeRotationClaw;

    //outtakelift
    public DcMotorEx leftLift;
    public DcMotorEx rightLift;



    public RobotMap(HardwareMap Init)
    {


        //intakelift
        intakeArm=Init.get(DcMotorEx.class,"intakelift");
        intakeArm.setDirection(DcMotor.Direction.FORWARD);
        intakeArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        intakeArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftLift=Init.get(DcMotorEx.class,"leftOuttakeLift");
        leftLift.setDirection(DcMotor.Direction.REVERSE);
        leftLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        rightLift=Init.get(DcMotorEx.class,"rightLift");
        rightLift.setDirection(DcMotor.Direction.FORWARD);
        rightLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        //intake
        intakeRotationClaw=Init.get(Servo.class, "intakeRotationClaw");
        intakeClaw =Init.get(Servo.class, "intakeClaw");
        intakeWrist= Init.get(Servo.class, "intakeWrist");

        //outtake
        outtakeWrist= Init.get(Servo.class, "outtakeWrist");
        outtakeClaw=Init.get(Servo.class, "outtakeClaw");
        outtakeRotationClaw= Init.get(Servo.class, "outtakeRotationClaw");

        //OUTTAKE ARM
        leftOuttakeArm= Init.get(Servo.class, "leftOuttakeArm");
        rightOuttakeArm= Init.get(Servo.class, "rightOuttakeArm");
    }
}