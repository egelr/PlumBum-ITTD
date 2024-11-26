package org.firstinspires.ftc.teamcode;

public class variables {

    //Arm Servo angles for TeleOP and Autonomous
    public static final double armServoAngleUp = 275;
    public static final double armServoAngleDown = armServoAngleUp - 149;
    public static final double armServoAngleGrab = armServoAngleUp - 166;
    public static final double armServoAngleStraight = armServoAngleUp - 75;


    //Intake claw angles for TeleOP and Autonomous
    public static final double intakeClawServoAngleClosed = 265;
    public static final double intakeClawServoAngleOpened = intakeClawServoAngleClosed - 85;


    //Transfer claw angles for TeleOP and Autonomous
    public static final double transferClawServoAngleClosed = 90;
    public static final double transferClawServoAngleOpened = transferClawServoAngleClosed - 60;


    //Outtake (flip) servo angles for TeleOp and Autonomous
    public static final double flipServoAngleDown = 249;
    public static final double flipServoAngleUp = flipServoAngleDown - 144;
    public static final double flipServoAngleBasket = flipServoAngleDown - 174;


    //Pivot servo angles for TeleOP and Autonomous
    public static final double pivotVerticalAngle = 140;
    public static final double pivotHorizontalAngle = pivotVerticalAngle + 90;


    //Specimen Claw angles for TeleOP and Autonomous
    public static final double specimenClawAngleClosed = 293;
    public static final double specimenClawAngleOpened = specimenClawAngleClosed - 73;


    //Intake Slides extension positions for TeleOP and Autonomous
    public static final int intakeSlideExtendPos = 1300;
    public static final int intakeSlideExtendPos2 = 1350;
    public static final int intakeSlideExtendPosSmall = 950;


    //Lift Slides positions for TeleOP and Autonomous
    public static final int liftUpPos = 1550;
    public static final int liftBasketPos = 1650;
    public static final int liftDownPos = 1100;
    public static final int liftSpecimenPos = 200;


}
