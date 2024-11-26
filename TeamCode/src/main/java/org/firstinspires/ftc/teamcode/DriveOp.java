package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Hardware.FlipServo;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "DriveOp")
public class DriveOp extends LinearOpMode {

    //int liftLastPosition;
    //Creating the variables for Motors
    /*private Motor LMLeft;
    private Motor LMRight;
    private Motor IntakeMotor;*/

    private Motor fL, fR, bL, bR;
    //Creating drive speed variable
    public double drive_speed = 0.5;

    @Override
    public void runOpMode() throws InterruptedException {

        //Creating Drivetrain Motors and Setting their behaviour to "brake"
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

        //Creating Lift/Slides and Intake Motors, Setting their behaviour to "brake"
        /*LMLeft = new Motor(hardwareMap, "LiftMotorLeft");
        LMRight = new Motor(hardwareMap, "LiftMotorRight");
        IntakeMotor = new Motor(hardwareMap, "IntakeMotor");

        LMLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        LMLeft.setInverted(true);
        LMRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        IntakeMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);


        LMLeft.setPositionCoefficient(0.05);
        LMRight.setPositionCoefficient(0.05);
        IntakeMotor.setPositionCoefficient(0.05);

        LMLeft.setPositionTolerance(50);
        LMRight.setPositionTolerance(50);
        IntakeMotor.setPositionTolerance(50);

        LMLeft.resetEncoder();
        LMRight.resetEncoder();
        IntakeMotor.resetEncoder();
        //Resetting the encoders of Lift and Intake Motors
        LMLeft.setTargetPosition(0);
        LMRight.setTargetPosition(0);
        IntakeMotor.setTargetPosition(0);
        LMLeft.setRunMode(Motor.RunMode.PositionControl);
        LMRight.setRunMode(Motor.RunMode.PositionControl);
        IntakeMotor.setRunMode(Motor.RunMode.PositionControl);

        //Creating Servos

        Servo clawServo = hardwareMap.get(Servo.class, "clawServo");
        Servo intakePivotServo = hardwareMap.get(Servo.class, "intakePivotServo");
        Servo intakeArmServo = hardwareMap.get(Servo.class, "intakeArmServo");
        Servo intakeClawServo = hardwareMap.get(Servo.class, "intakeClawServo");

        Servo transferClawServo = hardwareMap.get(Servo.class, "transferClawServo");

        Servo flipServo = hardwareMap.get(Servo.class, "flipServo");*/
        //Creating timer variables
        ElapsedTime timer = new ElapsedTime();
        ElapsedTime wait = new ElapsedTime();

        waitForStart();

        while (!isStopRequested()) {

            //Drivetrain controls
            drive.driveRobotCentric(
                    -gamepad1.left_stick_x * drive_speed,
                    gamepad1.left_stick_y * drive_speed,
                    -gamepad1.right_stick_x * drive_speed,
                    false

            );

        }
    }
}

