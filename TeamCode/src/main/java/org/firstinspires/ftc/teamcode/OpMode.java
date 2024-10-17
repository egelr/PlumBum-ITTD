package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "OpMode")
public class OpMode extends LinearOpMode {

    public double drive_speed = 1;
    private SimpleServo clawServo;
    private SimpleServo intakeAngleServo;
    public CRServo intakeSpinServo;
    private DcMotor LMLeft;
    private DcMotor LMRight;
    private DcMotor IntakeMotor;
    private Motor fL, fR, bL, bR;

    @Override
    public void runOpMode() throws InterruptedException {

        //Creating Drivetrain Motors
        fL = new Motor(hardwareMap, "leftFront", Motor.GoBILDA.RPM_312);
        fR = new Motor(hardwareMap, "rightFront", Motor.GoBILDA.RPM_312);
        bL = new Motor(hardwareMap, "leftBack", Motor.GoBILDA.RPM_312);
        bR = new Motor(hardwareMap, "rightBack", Motor.GoBILDA.RPM_312);
        fL.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        fR.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        bL.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        bR.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        //Creating the Mecanum Drivetrain
        MecanumDrive drive = new MecanumDrive(fL, fR, bL, bR);


        LMLeft = hardwareMap.get(DcMotor.class, "LiftMotorLeft"); //new DcMotor(hardwareMap, "LiftMotorLeft");
        LMRight = hardwareMap.get(DcMotor.class, "LiftMotorRight");
        IntakeMotor = hardwareMap.get(DcMotor.class, "IntakeMotor");//new DcMotor(hardwareMap, "LiftMotorRight");


        LMLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LMLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        LMRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        IntakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        LMLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LMRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        IntakeMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        intakeSpinServo = hardwareMap.get(CRServo.class, "intakeSpinServo");


        clawServo = new SimpleServo(
                hardwareMap, "clawServo", 0, 300,
                AngleUnit.DEGREES
        );
        intakeAngleServo = new SimpleServo(
                hardwareMap, "intakeAngleServo", 0, 300,
                AngleUnit.DEGREES
        );


        ElapsedTime timer = new ElapsedTime();
        ElapsedTime wait = new ElapsedTime();


        waitForStart();

        while (!isStopRequested()) {

            drive.driveRobotCentric(
                    -gamepad1.left_stick_x * drive_speed,
                    gamepad1.left_stick_y * drive_speed,
                    -gamepad1.right_stick_x * drive_speed,
                    false

            );
            //Drivetrain Motors speed Change
            if (gamepad1.right_trigger > 0.5) {
                drive_speed = 0.35;
            } else {
                drive_speed = 1;
            }

            if (gamepad1.dpad_up)
            {
                LMLeft.setTargetPosition(200);
                LMRight.setTargetPosition(200);
                LMLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                LMRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                timer.reset();
                while((LMLeft.isBusy()||LMRight.isBusy()) && timer.seconds()<2)
                {
                    LMLeft.setPower(1);
                    LMRight.setPower(1);
                }
                clawServo.turnToAngle(293);
                wait.reset();
                while (wait.seconds() < 0.2) {}
                timer.reset();
                LMLeft.setTargetPosition(1550);
                LMRight.setTargetPosition(1550);
                LMLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                LMRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                timer.reset();
                while((LMLeft.isBusy()||LMRight.isBusy()) && timer.seconds()<2)
                {
                    LMLeft.setPower(1);
                    LMRight.setPower(1);
                }
            }

            if (gamepad1.dpad_down)
            {
                LMLeft.setTargetPosition(1100);
                LMRight.setTargetPosition(1100);
                LMLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                LMRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                timer.reset();
                while((LMLeft.isBusy()||LMRight.isBusy()) && timer.seconds()<2)
                {
                    LMLeft.setPower(1);
                    LMRight.setPower(1);
                }
                clawServo.turnToAngle(220);
                wait.reset();
                while (wait.seconds() < 0.2) {}
                timer.reset();
                LMLeft.setTargetPosition(0);
                LMRight.setTargetPosition(0);
                LMLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                LMRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                timer.reset();
                while((LMLeft.isBusy()||LMRight.isBusy()) && timer.seconds()<2)
                {
                    LMLeft.setPower(1);
                    LMRight.setPower(1);
                }
            }

            if (gamepad1.right_bumper)
            {
                intakeSpinServo.setPower(1);
                timer.reset();
            }
            if (gamepad1.left_bumper)
            {
                intakeSpinServo.setPower(-1);
                timer.reset();
            }
            if (timer.seconds() > 0.1)
            {
                intakeSpinServo.setPower(0);
            }

            if(gamepad1.triangle)
            {
                intakeAngleServo.turnToAngle(0);
            }
            if(gamepad1.cross)
            {
                intakeAngleServo.turnToAngle(300 );
            }
            if(gamepad1.share)
            {
                LMLeft.setTargetPosition(1450);
                LMRight.setTargetPosition(1450);
                LMLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                LMRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                timer.reset();
                while((LMLeft.isBusy()||LMRight.isBusy()) && timer.seconds()<2)
                {
                    LMLeft.setPower(1);
                    LMRight.setPower(1);
                }
            }
            if(gamepad1.options)
            {
                LMLeft.setTargetPosition(0);
                LMRight.setTargetPosition(0);
                LMLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                LMRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                timer.reset();
                while((LMLeft.isBusy()||LMRight.isBusy()) && timer.seconds()<2)
                {
                    LMLeft.setPower(1);
                    LMRight.setPower(1);
                }
            }


            LMLeft.setPower(0.1);
            LMRight.setPower(0.1);
            IntakeMotor.setPower(0);
            telemetry.addData("Status: ", LMLeft.getCurrentPosition());
            telemetry.addData("Statusintake", IntakeMotor.getCurrentPosition());
            telemetry.update();

           /* if(gamepad1.circle)
            {
                pos1 = 1900;
                IntakeMotor.setTargetPosition(pos1);
                IntakeMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                timer.reset();
                while (IntakeMotor.isBusy() && timer.seconds() < 2) {
                    IntakeMotor.setPower(1);

                }
            }
            if(gamepad1.triangle)
            {
                pos1 = 0;
                IntakeMotor.setTargetPosition(pos1);
                IntakeMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                timer.reset();
                while (IntakeMotor.isBusy() && timer.seconds() < 2) {
                    IntakeMotor.setPower(1);

                }
            }


            if (gamepad1.share) {
                pos = pos + 1900;
                LMLeft.setTargetPosition(pos);
                LMRight.setTargetPosition(pos);
                LMLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                LMRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                timer.reset();
                while ((LMLeft.isBusy()|| LMRight.isBusy()) && timer.seconds() < 2) {
                    LMLeft.setPower(1);
                    LMRight.setPower(1);

                }
            }

            if (gamepad1.options) {
                LMLeft.setTargetPosition(0);
                LMLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                LMRight.setTargetPosition(0);
                LMRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                timer.reset();
                while ((LMLeft.isBusy()|| LMRight.isBusy()) && timer.seconds() < 2) {
                    LMLeft.setPower(1);
                    LMRight.setPower(1);
                }
            }
            if(gamepad1.guide)
            {
                pos = pos + 200;
                LMLeft.setTargetPosition(pos);
                LMLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                LMRight.setTargetPosition(pos);
                LMRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                timer.reset();
                while((LMLeft.isBusy()||LMRight.isBusy()) && timer.seconds()<2)
                {
                    LMLeft.setPower(1);
                    LMRight.setPower(1);
                }
            }
            if(gamepad1.cross)
            {
                pos = pos - 200;
                LMLeft.setTargetPosition(pos);
                LMLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                LMRight.setTargetPosition(pos);
                LMRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                timer.reset();
                while((LMLeft.isBusy()||LMRight.isBusy()) && timer.seconds()<2)
                {
                    LMLeft.setPower(1);
                    LMRight.setPower(1);
                }
            }
            if(gamepad1.square)
            {
                //pos = pos+300;
                LMLeft.setTargetPosition(580);
                LMLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                LMRight.setTargetPosition(580);
                LMRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                timer.reset();
                while((LMLeft.isBusy()||LMRight.isBusy()) && timer.seconds()<2)
                {
                    LMLeft.setPower(1);
                    LMRight.setPower(1);
                }
            }
            if(gamepad1.dpad_down)
            {
                //pos = pos - 450;
                LMLeft.setTargetPosition(30);
                LMLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                LMRight.setTargetPosition(30);
                LMRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                timer.reset();
                while((LMLeft.isBusy()||LMRight.isBusy()) && timer.seconds()<2)
                {
                    LMLeft.setPower(1);
                    LMRight.setPower(1);
                }
            }

            if (gamepad1.circle)
            {
                clawServo.turnToAngle(220);
            }
            if (gamepad1.triangle) {
                clawServo.turnToAngle(293);
            }
            */
        }

    }
}

