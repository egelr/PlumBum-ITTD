package org.firstinspires.ftc.teamcode;
import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.hardware.Claw;
import org.firstinspires.ftc.teamcode.hardware.FlipServo;
import org.firstinspires.ftc.teamcode.hardware.IntakeArm;
import org.firstinspires.ftc.teamcode.hardware.IntakeClaw;
import org.firstinspires.ftc.teamcode.hardware.IntakeSlides;
import org.firstinspires.ftc.teamcode.hardware.Lift;
import org.firstinspires.ftc.teamcode.hardware.Pivot;
import org.firstinspires.ftc.teamcode.hardware.SpecimenArmAngle;
import org.firstinspires.ftc.teamcode.hardware.SpecimenClaw;
import org.firstinspires.ftc.teamcode.hardware.SpecimenPivot;
import org.firstinspires.ftc.teamcode.hardware.TransferClaw;
import org.firstinspires.ftc.teamcode.MecanumDrive;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


@Config
@Autonomous(name = "AutoSpecimen", group = "Autonomous")
public class AutoSpecimen extends LinearOpMode {

    @Override
    public void runOpMode() {
        Pose2d initialPose = new Pose2d(0, 0, Math.toRadians(0));
        Pose2d Pose1 = new Pose2d(0, 0, Math.toRadians(180));

        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        Claw claw = new Claw(hardwareMap);
        Lift lift = new Lift(hardwareMap);
        IntakeSlides IntakeSlides = new IntakeSlides(hardwareMap);
        IntakeClaw intakeClaw = new IntakeClaw(hardwareMap);
        IntakeArm intakeArm = new IntakeArm(hardwareMap);
        FlipServo flipServo = new FlipServo(hardwareMap);
        TransferClaw transferClaw = new TransferClaw(hardwareMap);
        Pivot pivot = new Pivot(hardwareMap);
        SpecimenClaw specimenClaw = new SpecimenClaw(hardwareMap);
        SpecimenPivot specimenPivot = new SpecimenPivot(hardwareMap);
        SpecimenArmAngle specimenArmAngle = new SpecimenArmAngle(hardwareMap);



        TrajectoryActionBuilder firstSpecimenHangTrajectory = drive.actionBuilder(initialPose)
                .setReversed(false)
                .strafeTo(new Vector2d(29, 13))
                .lineToX(32 + Variables.specimenAdjustable);
        TrajectoryActionBuilder secondSpecimenIntakeTrajectory = drive.actionBuilder(new Pose2d(32 + Variables.specimenAdjustable,13,Math.toRadians(0)))
                .setReversed(true)
                .lineToX(29)
                .strafeTo(new Vector2d(16, -32));
        TrajectoryActionBuilder thirdSpecimenIntakeTrajectory = drive.actionBuilder(new Pose2d(16, -32,Math.toRadians(0)))
                .setReversed(false)
                .turn(Math.toRadians(-5));
        TrajectoryActionBuilder thirdSpecimenIntakeTrajectory2 = drive.actionBuilder(new Pose2d(16, -32,Math.toRadians(5)))
                .setReversed(false)
                .strafeTo(new Vector2d(16, -43));
        TrajectoryActionBuilder secondSpecimenPickupTrajectory = drive.actionBuilder(new Pose2d(16, -43,Math.toRadians(0)))
                .setReversed(false)
                .strafeTo(new Vector2d(-2, -27));
        TrajectoryActionBuilder secondSpecimenHangTrajectory = drive.actionBuilder(new Pose2d(-2, -27,Math.toRadians(0)))
                .setReversed(false)
                .strafeTo(new Vector2d(31, 12.5))
                .turn(Math.toRadians(-4))
                .lineToX(33 + Variables.specimenAdjustable);
        TrajectoryActionBuilder secondSpecimenBackupTrajectory = drive.actionBuilder(new Pose2d(33 + Variables.specimenAdjustable, 12.5,Math.toRadians(-4)))
                .setReversed(true)
                .lineToX(31);
        TrajectoryActionBuilder thirdSpecimenPickupTrajectory = drive.actionBuilder(new Pose2d(31, 12.5,Math.toRadians(0)))
                .setReversed(true)
                .strafeTo(new Vector2d(-3, -27));
        TrajectoryActionBuilder thirdSpecimenHangTrajectory = drive.actionBuilder(new Pose2d(-3, -27,Math.toRadians(0)))
                .setReversed(false)
                .strafeTo(new Vector2d(31, 11))
                .turn(Math.toRadians(-5))
                .lineToX(34 + Variables.specimenAdjustable);
        TrajectoryActionBuilder thirdSpecimenBackupTrajectory = drive.actionBuilder(new Pose2d(34 + Variables.specimenAdjustable, 11,Math.toRadians(-5)))
                .setReversed(true)
                .lineToX(31);
        TrajectoryActionBuilder fourthSpecimenPickupTrajectory = drive.actionBuilder(new Pose2d(31, 11,Math.toRadians(-3)))
                .setReversed(true)
                .strafeTo(new Vector2d(-2.5, -27));
        TrajectoryActionBuilder fourthSpecimenHangTrajectory = drive.actionBuilder(new Pose2d(-3, -27,Math.toRadians(-3)))
                .setReversed(false)
                .strafeTo(new Vector2d(31, 11))
                .turn(Math.toRadians(-5))
                .lineToX(35 + Variables.specimenAdjustable);
        TrajectoryActionBuilder parkTrajectory = drive.actionBuilder(new Pose2d(35 + Variables.specimenAdjustable, 11,Math.toRadians(-5)))
                .setReversed(true)
                .lineToX(20);


        //Actions that need to happen on init


        Action firstSpecimenHangTrajectoryAction;
        Action secondSpecimenIntakeTrajectoryAction;
        Action thirdSpecimenIntakeTrajectoryAction;
        Action thirdSpecimenIntakeTrajectory2Action;
        Action secondSpecimenPickupTrajectoryAction;
        Action secondSpecimenHangTrajectoryAction;
        Action secondSpecimenHangTrajectory2Action;
        Action secondSpecimenBackupTrajectoryAction;
        Action thirdSpecimenPickupTrajectoryAction;
        Action thirdSpecimenHangTrajectoryAction;
        Action thirdSpecimenBackupTrajectoryAction;
        Action fourthSpecimenPickupTrajectoryAction;
        Action fourthSpecimenHangTrajectoryAction;
        Action parkTrajectoryAction;


        Actions.runBlocking(claw.closeClaw());
        Actions.runBlocking(flipServo.downFlip());
        Actions.runBlocking(intakeClaw.openIntakeClaw());
        Actions.runBlocking(transferClaw.openTransferClaw());
        Actions.runBlocking(intakeArm.intakeArmUp());
        Actions.runBlocking(pivot.PivotN());
        Actions.runBlocking(IntakeSlides.SlidePark());
        Actions.runBlocking(lift.liftPark());
        Actions.runBlocking(specimenClaw.closeSpecimenClaw());
        Actions.runBlocking(specimenPivot.normal());
        Actions.runBlocking(specimenArmAngle.grabSpecimen());

        firstSpecimenHangTrajectoryAction = firstSpecimenHangTrajectory.build();
        secondSpecimenIntakeTrajectoryAction = secondSpecimenIntakeTrajectory.build();
        thirdSpecimenIntakeTrajectoryAction = thirdSpecimenIntakeTrajectory.build();
        thirdSpecimenIntakeTrajectory2Action = thirdSpecimenIntakeTrajectory2.build();
        secondSpecimenPickupTrajectoryAction = secondSpecimenPickupTrajectory.build();
        secondSpecimenHangTrajectoryAction = secondSpecimenHangTrajectory.build();
        secondSpecimenBackupTrajectoryAction = secondSpecimenBackupTrajectory.build();
        thirdSpecimenPickupTrajectoryAction = thirdSpecimenPickupTrajectory.build();
        thirdSpecimenHangTrajectoryAction = thirdSpecimenHangTrajectory.build();
        thirdSpecimenBackupTrajectoryAction = thirdSpecimenBackupTrajectory.build();
        fourthSpecimenPickupTrajectoryAction = fourthSpecimenPickupTrajectory.build();
        fourthSpecimenHangTrajectoryAction = fourthSpecimenHangTrajectory.build();
        parkTrajectoryAction = parkTrajectory.build();


        while (!isStopRequested() && !opModeIsActive()) {

            waitForStart();

            if (isStopRequested()) return;


            Actions.runBlocking(
                    new SequentialAction(

                            //Actions for hanging first specimen

                            new ParallelAction(
                                    specimenArmAngle.hangSpecimen(),
                                    specimenPivot.inverted(),
                                    firstSpecimenHangTrajectoryAction
                            ),
                            specimenClaw.openSpecimenClaw(),
                            //new SleepAction(0.1),

                            //Actions for intaking second sample

                            new ParallelAction(
                                    secondSpecimenIntakeTrajectoryAction,
                                    new SequentialAction(
                                            new SleepAction(0.3),
                                            new ParallelAction(
                                                    specimenArmAngle.grabSpecimen(),
                                                    specimenPivot.normal()
                                            )
                                    )
                            ),
                            new ParallelAction(
                                    IntakeSlides.SlideExtendSmall(),
                                    intakeArm.intakeArmDown(),
                                    intakeClaw.openIntakeClaw()
                            ),
                            intakeArm.intakeArmGrab(),
                            new SleepAction(0.1),
                            intakeClaw.closeIntakeClaw(),
                            new SleepAction(0.2),
                            intakeArm.intakeArmSt(),
                            new SleepAction(0.3),
                            new ParallelAction(
                                    intakeArm.intakeArmUp(),
                                    IntakeSlides.SlidePark()
                            ),

                            //Actions for intaking third sample

                            new ParallelAction(
                                    transferClaw.closeTransferClaw(),
                                    intakeClaw.openIntakeClaw(),
                                    thirdSpecimenIntakeTrajectoryAction
                            ),
                            new SleepAction(0.25),
                            new ParallelAction(
                                    flipServo.basketFlip(),
                                    thirdSpecimenIntakeTrajectory2Action,
                                    IntakeSlides.SlideExtendMedium(),
                                    intakeArm.intakeArmDown(),
                                    intakeClaw.openIntakeClaw()
                            ),
                            transferClaw.openTransferClaw(),
                            intakeArm.intakeArmGrab(),
                            new SleepAction(0.1),
                            intakeClaw.closeIntakeClaw(),
                            flipServo.downFlip(),
                            new SleepAction(0.3),
                            intakeArm.intakeArmSt(),
                            new SleepAction(0.3),
                            new ParallelAction(
                                    intakeArm.intakeArmUp(),
                                    IntakeSlides.SlidePark()
                            ),
                            transferClaw.closeTransferClaw(),
                            new SleepAction(0.3),
                            intakeClaw.openIntakeClaw(),
                            flipServo.basketFlip(),
                            new SleepAction(0.75),
                            transferClaw.openTransferClaw(),
                            new SleepAction(0.2),

                            //Hanging second specimen

                            new ParallelAction(
                                    secondSpecimenPickupTrajectoryAction,
                                    flipServo.downFlip()
                            ),
                            specimenClaw.closeSpecimenClaw(),
                            new SleepAction(0.2),
                            new ParallelAction(
                                    specimenArmAngle.hangSpecimen(),
                                    specimenPivot.inverted(),
                                    secondSpecimenHangTrajectoryAction
                            ),
                            specimenClaw.openSpecimenClaw(),
                            secondSpecimenBackupTrajectoryAction,

                            //Hanging third specimen

                            new ParallelAction(
                                    thirdSpecimenPickupTrajectoryAction,
                                    specimenArmAngle.grabSpecimen(),
                                    specimenPivot.normal()
                            ),
                            specimenClaw.closeSpecimenClaw(),
                            new SleepAction(0.2),
                            new ParallelAction(
                                    specimenArmAngle.hangSpecimen(),
                                    specimenPivot.inverted(),
                                    thirdSpecimenHangTrajectoryAction
                            ),
                            specimenClaw.openSpecimenClaw(),
                            thirdSpecimenBackupTrajectoryAction,

                            //Hanging fourth specimen

                            new ParallelAction(
                                    fourthSpecimenPickupTrajectoryAction,
                                    specimenArmAngle.grabSpecimen(),
                                    specimenPivot.normal()
                            ),
                            specimenClaw.closeSpecimenClaw(),
                            new SleepAction(0.2),
                            new ParallelAction(
                                    specimenArmAngle.hangSpecimen(),
                                    specimenPivot.inverted(),
                                    fourthSpecimenHangTrajectoryAction
                            ),

                            new ParallelAction(
                                    specimenClaw.openSpecimenClaw(),
                                    parkTrajectoryAction,
                                    specimenArmAngle.parkSpecimen()
                            )


                    ));

        }
    }
}