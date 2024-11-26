package org.firstinspires.ftc.teamcode.Hardware;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.autoyellow;
import org.firstinspires.ftc.teamcode.variables;

public class IntakeSlides {
    private DcMotor iSlide;

    public IntakeSlides(HardwareMap hardwareMap) {
        iSlide = hardwareMap.get(DcMotor.class, "IntakeMotor");
        iSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        iSlide.setDirection(DcMotorSimple.Direction.FORWARD);
        iSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public class SlideExtend implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                iSlide.setTargetPosition(variables.intakeSlideExtendPos);
                iSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                iSlide.setPower(1);
                initialized = true;
            }

            if (iSlide.getCurrentPosition()< variables.intakeSlideExtendPos) {
                return true;
            } else {
                iSlide.setPower(0);
                return false;
            }
        }
    }
    public  Action SlideExtend() {
        return new IntakeSlides.SlideExtend();
    }
    public class SlideExtend2 implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                iSlide.setTargetPosition(variables.intakeSlideExtendPos2);
                iSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                iSlide.setPower(1);
                initialized = true;
            }
            if (iSlide.getCurrentPosition()< variables.intakeSlideExtendPos2) {
                return true;
            } else {
                iSlide.setPower(0);
                return false;
            }
        }
    }
    public  Action SlideExtend2() {
        return new IntakeSlides.SlideExtend2();
    }
    public class SlideExtendSmall implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                iSlide.setTargetPosition(variables.intakeSlideExtendPosSmall);
                iSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                iSlide.setPower(1);
                initialized = true;
            }
            if (iSlide.getCurrentPosition()< variables.intakeSlideExtendPosSmall) {
                return true;
            } else {
                iSlide.setPower(0);
                return false;
            }
        }
    }
    public  Action SlideExtendSmall() {
        return new IntakeSlides.SlideExtendSmall();
    }
    public class SlidePark implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                iSlide.setTargetPosition(0);
                iSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                iSlide.setPower(1);
                initialized = true;
            }

            if (iSlide.getCurrentPosition() > 0) {
                return true;
            } else {
                iSlide.setPower(0);
                return false;
            }

        }
    }
    public Action SlidePark() {
        return new IntakeSlides.SlidePark();
    }

}