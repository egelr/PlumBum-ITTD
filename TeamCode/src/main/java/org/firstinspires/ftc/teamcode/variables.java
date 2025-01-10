package org.firstinspires.ftc.teamcode;

public class variables {

    //Arm Servo angles for TeleOP and Autonomous
    public static final double armServoAngleUp = 289;
    public static final double x = 275;
    public static final double armServoAngleDown = x - 147;
    public static final double armServoAngleGrab = x - 163;
    public static final double armServoAngleStraight = x - 58;


    //Intake claw angles for TeleOP and Autonomous
    public static final double intakeClawServoAngleClosed = 271;
    public static final double intakeClawServoAngleOpened = intakeClawServoAngleClosed - 85;


    //Transfer claw angles for TeleOP and Autonomous
    public static final double transferClawServoAngleClosed = 90;
    public static final double transferClawServoAngleOpened = transferClawServoAngleClosed - 60;


    //Outtake (flip) servo angles for TeleOp and Autonomous
    public static final double flipServoAngleDown = 249;
    public static final double flipServoAngleUp = flipServoAngleDown - 144;
    public static final double flipServoAngleUpTeleOp = flipServoAngleDown - 150;
    public static final double flipServoAngleBasket = flipServoAngleDown - 174;


    //Pivot servo angles for TeleOP and Autonomous
    public static final double pivotVerticalAngle = 67;
    public static final double pivotHorizontalAngle = pivotVerticalAngle + 90; //3
    public static final double pivot1yellowAngle = pivotVerticalAngle + 15;
    public static final double pivot3yellowAngle = pivotVerticalAngle - 23;
    public static final double pivot1ColourAngle = pivotVerticalAngle + 26;




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
    public static final int liftDownPos = 1050;
    public static final int liftSpecimenPos = 200;


}
