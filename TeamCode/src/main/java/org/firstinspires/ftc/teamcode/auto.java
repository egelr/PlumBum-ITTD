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

import org.firstinspires.ftc.teamcode.Hardware.Claw;
import org.firstinspires.ftc.teamcode.Hardware.FlipServo;
import org.firstinspires.ftc.teamcode.Hardware.IntakeArm;
import org.firstinspires.ftc.teamcode.Hardware.IntakeClaw;
import org.firstinspires.ftc.teamcode.Hardware.IntakeSlides;
import org.firstinspires.ftc.teamcode.Hardware.Lift;
import org.firstinspires.ftc.teamcode.Hardware.Pivot;
import org.firstinspires.ftc.teamcode.Hardware.TransferClaw;
import org.firstinspires.ftc.teamcode.MecanumDrive;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
@Autonomous(name = "Auto", group = "Autonomous")
public class auto extends LinearOpMode {

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


        TrajectoryActionBuilder tab1 = drive.actionBuilder(initialPose)
                .setReversed(true)
                .lineToX(-29.5);
        TrajectoryActionBuilder tab0 = drive.actionBuilder(new Pose2d(-29.5,0,Math.toRadians(0)))
                .setReversed(false)
                .lineToX(-29);
        TrajectoryActionBuilder tab12 = drive.actionBuilder(new Pose2d(-29,0,Math.toRadians(0)))
                .setReversed(false)
                .lineToX(-23)
                .splineToSplineHeading(new Pose2d(-23, 26, Math.toRadians(85)), Math.toRadians(0))
                .strafeTo(new Vector2d(-46, 26))
                .strafeTo(new Vector2d(-46, 42))
                .strafeTo(new Vector2d(-5, 42));
        TrajectoryActionBuilder tab2 = drive.actionBuilder(new Pose2d(-5,42,Math.toRadians(85)))
                .setReversed(true)
                .splineToSplineHeading(new Pose2d(4,30,Math.toRadians(180)),Math.toRadians(0));
        TrajectoryActionBuilder tab3 = drive.actionBuilder(new Pose2d(4,30,Math.toRadians(180)))
                .setReversed(false)
                .splineToSplineHeading(new Pose2d(-10, -5, Math.toRadians(0)),Math.toRadians(0));
        TrajectoryActionBuilder tab4 = drive.actionBuilder(new Pose2d(-10, -5, Math.toRadians(0)))
                .setReversed(true)
                .lineToX(-28);
        TrajectoryActionBuilder tab5 = drive.actionBuilder(new Pose2d(-28, -5, Math.toRadians(0)))
                .setReversed(false)
                .lineToX(-27);
        TrajectoryActionBuilder tab6 = drive.actionBuilder(new Pose2d(-27, -5, Math.toRadians(0)))
                .setReversed(false)
                        .splineToSplineHeading(new Pose2d(4, 30, Math.toRadians(180)),Math.toRadians(0));
        TrajectoryActionBuilder tab7 = drive.actionBuilder(new Pose2d(4, 30, Math.toRadians(180)))
                .setReversed(false)
                .splineToSplineHeading(new Pose2d(-10, -2.5, Math.toRadians(0)),Math.toRadians(0));
        TrajectoryActionBuilder tab8 = drive.actionBuilder(new Pose2d(-10, -2.5, Math.toRadians(0)))
                .setReversed(true)
                .lineToX(-28);
        TrajectoryActionBuilder tab9 = drive.actionBuilder(new Pose2d(-38, -2.5, Math.toRadians(0)))
                .setReversed(false)
                .splineToSplineHeading(new Pose2d(0, 30, Math.toRadians(0)),Math.toRadians(0));

        // actions that need to happen on init; for instance, a claw tightening.
        Actions.runBlocking(claw.closeClaw());
        Actions.runBlocking(flipServo.downFlip());
        Actions.runBlocking(intakeClaw.openIntakeClaw());
        Actions.runBlocking(transferClaw.openTransferClaw());
        Actions.runBlocking(intakeArm.intakeArmUp());
        Actions.runBlocking(pivot.PivotN());


        while (!isStopRequested() && !opModeIsActive()) {

            waitForStart();

            if (isStopRequested()) return;

            Action trajectoryActionChosen;
            Action trajectoryActionChosen0;
            Action trajectoryActionChosen12;
            Action trajectoryActionChosen1;
            Action trajectoryActionChosen3;
            Action trajectoryActionChosen4;
            Action trajectoryActionChosen5;
            Action trajectoryActionChosen6;
            Action trajectoryActionChosen7;
            Action trajectoryActionChosen8;
            Action trajectoryActionChosen9;


            trajectoryActionChosen = tab1.build();
            trajectoryActionChosen0 = tab0.build();
            trajectoryActionChosen12 = tab12.build();
            trajectoryActionChosen1 = tab2.build();
            trajectoryActionChosen3 = tab3.build();
            trajectoryActionChosen4 = tab4.build();
            trajectoryActionChosen5 = tab5.build();
            trajectoryActionChosen6 = tab6.build();
            trajectoryActionChosen7 = tab7.build();
            trajectoryActionChosen8 = tab8.build();
            trajectoryActionChosen9 = tab9.build();


            Actions.runBlocking(
                    new SequentialAction(
                            new ParallelAction(
                                    lift.liftUp(),
                                    trajectoryActionChosen
                            ),
                            lift.liftDown(),
                            new ParallelAction(
                                    claw.openClaw(),
                                    trajectoryActionChosen0
                                    ),
                            new SleepAction(0.1),
                            trajectoryActionChosen12,
                            new ParallelAction(
                                    lift.liftSpeciment(),
                                    trajectoryActionChosen1
                            ),
                            claw.closeClaw(),
                            new SleepAction(0.1),
                            lift.liftUp(),
                            trajectoryActionChosen3,
                            lift.liftUp(),
                            trajectoryActionChosen4,
                            lift.liftDown(),
                            new ParallelAction(
                                    claw.openClaw(),
                                    trajectoryActionChosen5
                            ),
                            new ParallelAction(
                                    lift.liftSpeciment(),
                                    trajectoryActionChosen6
                           ),
                            claw.closeClaw(),
                            new SleepAction(0.1),
                            lift.liftUp(),
                            trajectoryActionChosen7,
                            lift.liftUp(),
                            trajectoryActionChosen8,
                            lift.liftDown(),
                            new ParallelAction(
                            claw.openClaw(),
                                    trajectoryActionChosen9,
                                    lift.liftPark()
                            )
                            ));
        }
    }
}