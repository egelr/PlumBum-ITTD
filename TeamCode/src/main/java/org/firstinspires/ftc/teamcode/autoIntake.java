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

//Autonomous for hanging 3 specimens and parking (63 points)

@Config
@Autonomous(name = "AutoIntake", group = "Autonomous")
public class autoIntake extends LinearOpMode {

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


        TrajectoryActionBuilder firstSpecimenHangTrajectory = drive.actionBuilder(initialPose)
                .setReversed(true)
                .lineToX(-29.5);
        TrajectoryActionBuilder firstSpecimenHangBackUpTrajectory = drive.actionBuilder(new Pose2d(-29.5,0,Math.toRadians(0)))
                .setReversed(false)
                .lineToX(-29);
        TrajectoryActionBuilder firstsampleDeliveryTrajectory = drive.actionBuilder(new Pose2d(-29,0,Math.toRadians(0)))
                .setReversed(false)
                .splineToSplineHeading(new Pose2d(-11.5, 25, Math.toRadians(142)), Math.toRadians(0));
        TrajectoryActionBuilder firstsample2DeliveryTrajectory = drive.actionBuilder(new Pose2d(-11.5, 25, Math.toRadians(142)))
                .setReversed(false)
                .turn(Math.toRadians(-100));
        TrajectoryActionBuilder secondsampleDeliveryTrajectory = drive.actionBuilder(new Pose2d(-11.5, 25, Math.toRadians(42)))
                .setReversed(false)
                .splineToSplineHeading(new Pose2d(-15, 36, Math.toRadians(138)), Math.toRadians(0));
        TrajectoryActionBuilder secondSpecimenUploadTrajectory = drive.actionBuilder(new Pose2d(-5,42,Math.toRadians(85)))
                .setReversed(true)
                .splineToSplineHeading(new Pose2d(4,30,Math.toRadians(180)),Math.toRadians(0));
        TrajectoryActionBuilder secondSpecimenStraightenTrajectory = drive.actionBuilder(new Pose2d(4,30,Math.toRadians(180)))
                .setReversed(false)
                .splineToSplineHeading(new Pose2d(-10, -5, Math.toRadians(0)),Math.toRadians(0));
        TrajectoryActionBuilder secondSpecimenHangTrajectory = drive.actionBuilder(new Pose2d(-10, -5, Math.toRadians(0)))
                .setReversed(true)
                .lineToX(-28);
        TrajectoryActionBuilder secondSpecimenHangBackUpTrajectory = drive.actionBuilder(new Pose2d(-28, -5, Math.toRadians(0)))
                .setReversed(false)
                .lineToX(-27);
        TrajectoryActionBuilder thirdSpecimenUploadTrajectory = drive.actionBuilder(new Pose2d(-27, -5, Math.toRadians(0)))
                .setReversed(false)
                .splineToSplineHeading(new Pose2d(4, 30, Math.toRadians(180)),Math.toRadians(0));
        TrajectoryActionBuilder thirdSpecimenStraightenTrajectory = drive.actionBuilder(new Pose2d(4, 30, Math.toRadians(180)))
                .setReversed(false)
                .splineToSplineHeading(new Pose2d(-10, -2.5, Math.toRadians(0)),Math.toRadians(0));
        TrajectoryActionBuilder thirdSpecimenHangTrajectory = drive.actionBuilder(new Pose2d(-10, -2.5, Math.toRadians(0)))
                .setReversed(true)
                .lineToX(-28);
        TrajectoryActionBuilder parkTrajectory = drive.actionBuilder(new Pose2d(-38, -2.5, Math.toRadians(0)))
                .setReversed(false)
                .splineToSplineHeading(new Pose2d(0, 30, Math.toRadians(0)),Math.toRadians(0));

        //Actions that need to happen on init
        Actions.runBlocking(claw.closeClaw());
        Actions.runBlocking(flipServo.downFlip());
        Actions.runBlocking(intakeClaw.openIntakeClaw());
        Actions.runBlocking(transferClaw.openTransferClaw());
        Actions.runBlocking(intakeArm.intakeArmUp());
        Actions.runBlocking(pivot.PivotN());
        Actions.runBlocking(IntakeSlides.SlidePark());
        Actions.runBlocking(lift.liftPark());

        Action firstSpecimenHangTrajectoryAction;
        Action firstSpecimenHangBackUpTrajectoryAction;
        Action firstsampleDeliveryTrajectoryAction;
        Action firstsample2DeliveryTrajectoryAction;
        Action secondsampleDeliveryTrajectoryAction;
        Action secondSpecimenUploadTrajectoryAction;
        Action secondSpecimenStraightenTrajectoryAction;
        Action secondSpecimenHangTrajectoryAction;
        Action secondSpecimenHangBackUpTrajectoryAction;
        Action thirdSpecimenUploadTrajectoryAction;
        Action thirdSpecimenStraightenTrajectoryAction;
        Action thirdSpecimenHangTrajectoryAction;
        Action parkTrajectoryAction;


        firstSpecimenHangTrajectoryAction = firstSpecimenHangTrajectory.build();
        firstSpecimenHangBackUpTrajectoryAction = firstSpecimenHangBackUpTrajectory.build();
        firstsampleDeliveryTrajectoryAction = firstsampleDeliveryTrajectory.build();
        firstsample2DeliveryTrajectoryAction = firstsample2DeliveryTrajectory.build();
        secondsampleDeliveryTrajectoryAction = secondsampleDeliveryTrajectory.build();
        secondSpecimenUploadTrajectoryAction = secondSpecimenUploadTrajectory.build();
        secondSpecimenStraightenTrajectoryAction = secondSpecimenStraightenTrajectory.build();
        secondSpecimenHangTrajectoryAction = secondSpecimenHangTrajectory.build();
        secondSpecimenHangBackUpTrajectoryAction = secondSpecimenHangBackUpTrajectory.build();
        thirdSpecimenUploadTrajectoryAction = thirdSpecimenUploadTrajectory.build();
        thirdSpecimenStraightenTrajectoryAction = thirdSpecimenStraightenTrajectory.build();
        thirdSpecimenHangTrajectoryAction = thirdSpecimenHangTrajectory.build();
        parkTrajectoryAction = parkTrajectory.build();


        while (!isStopRequested() && !opModeIsActive()) {

            waitForStart();

            if (isStopRequested()) return;


            Actions.runBlocking(
                    new SequentialAction(

                            //Actions for hanging first specimen

                            new ParallelAction(
                                    lift.liftUp(),
                                    firstSpecimenHangTrajectoryAction
                            ),
                            lift.liftDown(),
                            new ParallelAction(
                                    claw.openClaw(),
                                    firstSpecimenHangBackUpTrajectoryAction
                            ),

                            //Actions for delivering sample to human player

                            new SleepAction(0.1),
                            new ParallelAction(
                                    lift.liftPark(),
                                    firstsampleDeliveryTrajectoryAction
                            ),
                            new ParallelAction(
                                    IntakeSlides.SlideExtend(),
                                    intakeArm.intakeArmDown(),
                                    pivot.Pivot1Colour()
                            ),
                            intakeArm.intakeArmGrab(),
                            new SleepAction(0.25),
                            intakeClaw.closeIntakeClaw(),
                            new SleepAction(0.3),
                            new ParallelAction(
                                    IntakeSlides.SlidePark(),
                                    pivot.PivotN(),
                                    intakeArm.intakeArmDown(),
                                    firstsample2DeliveryTrajectoryAction
                                    ),
                            intakeClaw.openIntakeClaw(),
                            secondsampleDeliveryTrajectoryAction
                            /*,
                            new ParallelAction(
                                    transferClaw.closeTransferClaw(),
                                    new SleepAction(0.3),
                                    intakeClaw.openIntakeClaw()
                            ),
                            flipServo.basketFlip(),
                            new SleepAction(1.3),
                            transferClaw.openTransferClaw(),
                            new SleepAction(0.3),
                            new ParallelAction(
                                    flipServo.downFlip(),
                                    secondsampleDeliveryTrajectoryAction
                            ),
                            new ParallelAction(
                                    IntakeSlides.SlideExtendSmall(),
                                    intakeArm.intakeArmDown()
                            ),
                            intakeArm.intakeArmGrab(),
                            new SleepAction(0.25),
                            intakeClaw.closeIntakeClaw(),
                            new SleepAction(0.3),
                            intakeArm.intakeArmSt(),
                            new SleepAction(0.4),
                            new ParallelAction(
                                    intakeArm.intakeArmUp(),
                                    IntakeSlides.SlidePark()
                            ),
                            new ParallelAction(
                                    transferClaw.closeTransferClaw(),
                                    new SleepAction(0.3),
                                    intakeClaw.openIntakeClaw()
                            ),
                            flipServo.basketFlip(),
                            new SleepAction(1.3),
                            transferClaw.openTransferClaw(),
                            new SleepAction(0.5)/*,

                            //Actions for hanging second specimen

                            secondSpecimenUploadTrajectoryAction,
                            claw.closeClaw(),
                            new SleepAction(0.1),
                            lift.liftUp(),
                            secondSpecimenStraightenTrajectoryAction,
                            secondSpecimenHangTrajectoryAction,
                            lift.liftDown(),
                            new ParallelAction(
                                    claw.openClaw(),
                                    secondSpecimenHangBackUpTrajectoryAction
                            ),

                            //Actions for hanging third specimen

                            new ParallelAction(
                                    lift.liftSpeciment(),
                                    thirdSpecimenUploadTrajectoryAction
                            ),
                            claw.closeClaw(),
                            new SleepAction(0.1),
                            lift.liftUp(),
                            thirdSpecimenStraightenTrajectoryAction,
                            thirdSpecimenHangTrajectoryAction,
                            lift.liftDown(),

                            //Actions for parking

                            new ParallelAction(
                                    claw.openClaw(),
                                    parkTrajectoryAction,
                                    lift.liftPark()
                            )*/
                    ));
        }
    }
}
