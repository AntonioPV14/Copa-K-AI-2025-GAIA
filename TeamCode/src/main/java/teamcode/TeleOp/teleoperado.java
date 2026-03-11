package teamcode.TeleOp;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;
import com.qualcomm.robotcore.util.ElapsedTime;

import teamcode.TeleOp.controllers.IntakeClawController;
import teamcode.TeleOp.controllers.DrawerController;
import teamcode.TeleOp.controllers.IntakeRotationController;
import teamcode.TeleOp.controllers.IntakeWristController;
import teamcode.TeleOp.controllers.OuttakeArmController;
import teamcode.TeleOp.controllers.OuttakeClawController;
import teamcode.TeleOp.controllers.OuttakeRotationController;
import teamcode.TeleOp.controllers.OuttakeWristController;
import teamcode.TeleOp.controllers.OuttakeLift;

@Config
@TeleOp(name="teleoperado", group="Linear OpMode")
public class teleoperado extends LinearOpMode {

    // Declare OpMode members for each of the 4 motors.
    private final ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;
    int velocidad =1;

    private ServoController servoController;
    public static double p = 0, i = 0, d = 0;
    public static double f = 0;
    public static int target = 0;
    TouchSensor left_magnetic;
    TouchSensor right_magnetic;

    private static double power;
    private final double ticks_in_degree = 288 / 360.0;
    private static double div = 1;
    public static double liftpower = 0;

    public void setMotorRunningMode(DcMotor leftFrontDrive, DcMotor leftBackDrive, DcMotor rightFrontDrive,
                                    DcMotor rightBackDrive, DcMotor.RunMode runningMode) {
        leftFrontDrive.setMode(runningMode);
        rightFrontDrive.setMode(runningMode);
        leftBackDrive.setMode(runningMode);
        rightBackDrive.setMode(runningMode);
    }

    public void setMotorZeroPowerBehaviour(DcMotor leftFrontDrive, DcMotor leftBackDrive, DcMotor rightFrontDrive,
                                           DcMotor rightBackDrive, DcMotor.ZeroPowerBehavior zeroPowerBehavior) {
        leftFrontDrive.setZeroPowerBehavior(zeroPowerBehavior);
        rightFrontDrive.setZeroPowerBehavior(zeroPowerBehavior);
        leftBackDrive.setZeroPowerBehavior(zeroPowerBehavior);
        rightBackDrive.setZeroPowerBehavior(zeroPowerBehavior);
    }

    @Override
    public void runOpMode() {

        RobotMap robot = new RobotMap(hardwareMap);
        OuttakeClawController outtakeClawController = new OuttakeClawController(robot);
        IntakeClawController lowClawController = new IntakeClawController(robot);
        IntakeRotationController intakeRotationController = new IntakeRotationController(robot);
        OuttakeArmController outtakeArmController = new OuttakeArmController(robot);
        OuttakeWristController outtakeWristController = new OuttakeWristController(robot);
        OuttakeRotationController outtakeRotationController = new OuttakeRotationController(robot);
        OuttakeLift liftController = new OuttakeLift(robot);
        IntakeWristController intakeWristController = new IntakeWristController(robot);
        DrawerController drawerController = new DrawerController(robot);

        boolean liftActive = false; // Variable global

        outtakeWristController.update();
        outtakeClawController.update();
        lowClawController.update();
        intakeRotationController.update();
        outtakeArmController.update();
        outtakeRotationController.update();
        liftController.update(0);
        intakeWristController.update();
        drawerController.update(0);


        // Initialize the hardware variables. Note that the strings used here must correspond
        // to the names assigned during the robot configuration step on the DS or RC devices.
        leftFrontDrive = hardwareMap.get(DcMotor.class, "leftfront");
        leftBackDrive = hardwareMap.get(DcMotor.class, "leftback");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "rightfront");
        rightBackDrive = hardwareMap.get(DcMotor.class, "rightback");

        left_magnetic = hardwareMap.get(TouchSensor.class, "left_magnetic");
        right_magnetic = hardwareMap.get(TouchSensor.class, "right_magnetic");


        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);

        //overclocking chassis motors
        MotorConfigurationType mct1, mct2, mct3, mct4, mct5;
        mct1 = rightBackDrive.getMotorType().clone();
        mct1.setAchieveableMaxRPMFraction(1.0);
        rightBackDrive.setMotorType(mct1);

        mct2 = rightFrontDrive.getMotorType().clone();
        mct2.setAchieveableMaxRPMFraction(1.0);
        rightFrontDrive.setMotorType(mct2);

        mct3 = leftFrontDrive.getMotorType().clone();
        mct3.setAchieveableMaxRPMFraction(1.0);
        leftFrontDrive.setMotorType(mct3);

        mct4 = leftBackDrive.getMotorType().clone();
        mct4.setAchieveableMaxRPMFraction(1.0);
        leftBackDrive.setMotorType(mct4);

        mct5 = leftBackDrive.getMotorType().clone();
        mct5.setAchieveableMaxRPMFraction(1.0);


        setMotorRunningMode(leftFrontDrive, leftBackDrive, rightFrontDrive, rightBackDrive,
                DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        setMotorZeroPowerBehaviour(leftFrontDrive, leftBackDrive, rightFrontDrive, rightBackDrive,
                DcMotor.ZeroPowerBehavior.BRAKE);


        // Wait for the game to start (driver presses START)
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // see why you need previousGamepad1 & 2 on gm0
        Gamepad currentGamepad1 = new Gamepad();
        Gamepad currentGamepad2 = new Gamepad();
        Gamepad previousGamepad1 = new Gamepad();
        Gamepad previousGamepad2 = new Gamepad();

        waitForStart();
        runtime.reset();
        OuttakeArmController.currentStatus = OuttakeArmController.OuttakeArmStatus.CLOUD;
        OuttakeWristController.currentStatus = OuttakeWristController.ServoRotationStatus.TRANSFER;
        OuttakeLift.currentStatus = OuttakeLift.liftStatus.INIT; //
        OuttakeClawController.currentStatus = OuttakeClawController.outtakeClawStatus.OPEN;

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            double max;

            previousGamepad1.copy(currentGamepad1);
            previousGamepad2.copy(currentGamepad2);
            currentGamepad1.copy(gamepad1);
            currentGamepad2.copy(gamepad2);


//Botones Intake
            //ROTATION
            if (currentGamepad2.dpad_left && !previousGamepad2.dpad_left) {
                if (IntakeRotationController.currentStatus == IntakeRotationController.ServoRotationStatus.HORIZONTAL) {
                    IntakeRotationController.currentStatus = IntakeRotationController.ServoRotationStatus.LEFT;
                } else {
                    IntakeRotationController.currentStatus = IntakeRotationController.ServoRotationStatus.HORIZONTAL;
                }
            }

            if (currentGamepad2.dpad_right && !previousGamepad2.dpad_right) {
                if (IntakeRotationController.currentStatus == IntakeRotationController.ServoRotationStatus.HORIZONTAL) {
                    IntakeRotationController.currentStatus = IntakeRotationController.ServoRotationStatus.RIGHT;
                } else {
                    IntakeRotationController.currentStatus = IntakeRotationController.ServoRotationStatus.HORIZONTAL;
                }
            }

            if (currentGamepad2.dpad_up && !previousGamepad2.dpad_up) {
                if (IntakeRotationController.currentStatus != IntakeRotationController.ServoRotationStatus.VERTICAL) {
                    IntakeRotationController.currentStatus = IntakeRotationController.ServoRotationStatus.VERTICAL;
                } else {
                    IntakeRotationController.currentStatus = IntakeRotationController.ServoRotationStatus.HORIZONTAL;
                }
            }

            //EXTENDER DRAWERS Y CERRAR
            if (currentGamepad2.square && !previousGamepad2.square) {
                if (DrawerController.currentStatus == DrawerController.drawerStatus.INIT ) {
                    DrawerController.currentStatus = DrawerController.drawerStatus.COLLECT;
                    IntakeWristController.currentStatus = IntakeWristController.ServoRotationStatus.HOVER;
                }

                else if (IntakeWristController.currentStatus == IntakeWristController.ServoRotationStatus.HOVER) {
                    IntakeWristController.currentStatus = IntakeWristController.ServoRotationStatus.GRAB;
                } else {
                    DrawerController.currentStatus = DrawerController.drawerStatus.INIT;
                    IntakeWristController.currentStatus = IntakeWristController.ServoRotationStatus.INIT;
                    IntakeRotationController.currentStatus = IntakeRotationController.ServoRotationStatus.HORIZONTAL;
                }
            }


            //SCORE SAMPLE BASKET
            if (currentGamepad2.circle && !previousGamepad2.circle) {
                if (OuttakeArmController.currentStatus == OuttakeArmController.OuttakeArmStatus.CLOUD) {
                    OuttakeArmController.currentStatus = OuttakeArmController.OuttakeArmStatus.TRANSFER;
                    OuttakeRotationController.currentStatus = OuttakeRotationController.ServoRotationStatus.INIT;
                    OuttakeWristController.currentStatus = OuttakeWristController.ServoRotationStatus.TRANSFER;
                    DrawerController.currentStatus = DrawerController.drawerStatus.INIT;

                }
                //SCORE SAMPLE BASKET
                else if (OuttakeArmController.currentStatus == OuttakeArmController.OuttakeArmStatus.TRANSFER) {

                    OuttakeArmController.currentStatus = OuttakeArmController.OuttakeArmStatus.SCORE;
                    OuttakeRotationController.currentStatus = OuttakeRotationController.ServoRotationStatus.SCORE;
                    OuttakeWristController.currentStatus = OuttakeWristController.ServoRotationStatus.SCORE;

                }
                //VUELVE A INICIAR
                else {
                    OuttakeArmController.currentStatus = OuttakeArmController.OuttakeArmStatus.CLOUD;
                    OuttakeRotationController.currentStatus = OuttakeRotationController.ServoRotationStatus.INIT;
                    OuttakeWristController.currentStatus = OuttakeWristController.ServoRotationStatus.TRANSFER;
                }
            }

        //ABRE GARRA INTAKE
        if (currentGamepad2.right_bumper && !previousGamepad2.right_bumper) {
            if (IntakeClawController.currentStatus == IntakeClawController.clawStatus.OPEN) {
                IntakeClawController.currentStatus = IntakeClawController.clawStatus.CLOSED;

            } else {
                IntakeClawController.currentStatus = IntakeClawController.clawStatus.OPEN;

            }
        }
        //ABRE GARRA OUTTAKE

        if (currentGamepad2.left_bumper && !previousGamepad2.left_bumper) {
            if (OuttakeClawController.currentStatus == OuttakeClawController.outtakeClawStatus.OPEN) {
                OuttakeClawController.currentStatus = OuttakeClawController.outtakeClawStatus.CLOSED;

            } else {
                OuttakeClawController.currentStatus = OuttakeClawController.outtakeClawStatus.OPEN;

            }
        }

        //LIFT
        if (currentGamepad2.cross && !previousGamepad2.cross) {
            if (OuttakeLift.currentStatus == OuttakeLift.liftStatus.INIT) {
                OuttakeLift.currentStatus = OuttakeLift.liftStatus.HIGH;

            } else if (OuttakeLift.currentStatus == OuttakeLift.liftStatus.HIGH) {
                OuttakeLift.currentStatus = OuttakeLift.liftStatus.INIT;


            } else if (OuttakeLift.currentStatus == OuttakeLift.liftStatus.POWEROFF) {
                OuttakeLift.currentStatus = OuttakeLift.liftStatus.HIGH;
            }
        }
            if (OuttakeLift.currentStatus == OuttakeLift.liftStatus.INIT && right_magnetic.isPressed() && left_magnetic.isPressed()) {
                OuttakeLift.currentStatus = OuttakeLift.liftStatus.POWEROFF;
            }



        //GRAB SPECIMEN
        if (currentGamepad2.dpad_down && !previousGamepad2.dpad_down) {
            if (OuttakeArmController.currentStatus == OuttakeArmController.OuttakeArmStatus.CLOUD) {
                OuttakeArmController.currentStatus = OuttakeArmController.OuttakeArmStatus.GRAB;
                OuttakeRotationController.currentStatus = OuttakeRotationController.ServoRotationStatus.INIT;
                OuttakeWristController.currentStatus = OuttakeWristController.ServoRotationStatus.GRAB;
                //SCORE SPECIMEN
            } else if (OuttakeArmController.currentStatus == OuttakeArmController.OuttakeArmStatus.GRAB) {
                OuttakeArmController.currentStatus = OuttakeArmController.OuttakeArmStatus.SCORE;
                OuttakeRotationController.currentStatus = OuttakeRotationController.ServoRotationStatus.SCORE;
                OuttakeWristController.currentStatus = OuttakeWristController.ServoRotationStatus.SCORE;
                OuttakeLift.currentStatus = OuttakeLift.liftStatus.MEDIUM;
            } else if (OuttakeLift.currentStatus == OuttakeLift.liftStatus.MEDIUM) {
                OuttakeLift.currentStatus = OuttakeLift.liftStatus.INIT;
            } else {
                OuttakeArmController.currentStatus = OuttakeArmController.OuttakeArmStatus.CLOUD;
            }
        }

            //DRIVETRAIN
            // POV Mode uses left joystick to go forward & strafe, and right joystick to rotate.
            double lateral = gamepad1.left_stick_x;  // Movimiento lateral del joystick izquierdo
            double axial = -gamepad1.left_stick_y;   // Movimiento axial del joystick izquierdo
            double yaw = gamepad1.right_stick_x;     // Giro del joystick derecho

// Inicializa la variable de velocidad base
            double speed = 1.0;  // Velocidad normal (puedes ajustarlo como prefieras)

// Si el bumper izquierdo está presionado, reduce la velocidad (por ejemplo, a la mitad)
            if (gamepad1.left_bumper) {
                speed = 0.35;
            }
// Si el bumper derecho está presionado, incrementa la velocidad
            if (gamepad1.right_bumper) {
                speed = 0.75;  // Aumenta la velocidad (no sobrepasar 1.0 si no quieres que los motores se dañen)
            }

// Combina las solicitudes del joystick para cada eje de movimiento
            double leftFrontPower = (axial + lateral + yaw) * speed;
            double rightFrontPower = (axial - lateral - yaw) * speed;
            double leftBackPower = (axial - lateral + yaw) * speed;
            double rightBackPower = (axial + lateral - yaw) * speed;

// Normaliza los valores para que ninguna rueda exceda el 100% de la potencia
            max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
            max = Math.max(max, Math.abs(leftBackPower));
            max = Math.max(max, Math.abs(rightBackPower));

            if (max > 1.0) {
                leftFrontPower /= max;
                rightFrontPower /= max;
                leftBackPower /= max;
                rightBackPower /= max;
            }

// Envía la potencia calculada a las ruedas
            leftFrontDrive.setPower(leftFrontPower);
            rightFrontDrive.setPower(rightFrontPower);
            leftBackDrive.setPower(leftBackPower);
            rightBackDrive.setPower(rightBackPower);


            // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime);
        telemetry.addData("Lift Status", OuttakeLift.currentStatus);
        telemetry.addData("Intake Rotation", IntakeRotationController.currentStatus);
        telemetry.addData("Intake Claw", IntakeClawController.currentStatus);
        telemetry.addData("Outtake Claw", OuttakeClawController.currentStatus);
        telemetry.addData("Outtake Arm", OuttakeArmController.currentStatus);
        telemetry.addData("Outtake Wrist", OuttakeWristController.currentStatus);
        telemetry.addData("Outtake Rotation", OuttakeRotationController.currentStatus);
        telemetry.addData("Intake Wrist", IntakeWristController.currentStatus);
        telemetry.addData("Drawer", DrawerController.currentStatus);
        telemetry.addData("leftlift", OuttakeLift.leftLift.getCurrentPosition());
        telemetry.addData("rightlift", OuttakeLift.rightLift.getCurrentPosition());

            telemetry.addData("left_magnetic", left_magnetic.isPressed());
            telemetry.addData("left_magnetic", left_magnetic.getValue());

            telemetry.addData("right_magnetic", right_magnetic.isPressed());
            telemetry.addData("right_magnetic", right_magnetic.getValue());

            telemetry.update();


        outtakeClawController.update();
        lowClawController.update();
        intakeRotationController.update();
        outtakeArmController.update();
        outtakeWristController.update();
        outtakeRotationController.update();
        liftController.update(0);
        intakeWristController.update();
        drawerController.update(0);


        }
    }
}