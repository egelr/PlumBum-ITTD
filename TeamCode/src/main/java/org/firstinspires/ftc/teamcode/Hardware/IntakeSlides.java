package org.firstinspires.ftc.teamcode.Hardware;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.autoyellow;

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
                iSlide.setTargetPosition(1280);
                iSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                iSlide.setPower(1);
                initialized = true;
            }

            /*double pos1 = iSlide.getCurrentPosition();
            packet.put("liftPos", pos1);
            if (pos1 < 1280) {
                return true;
            } else {
                iSlide.setPower(0.1);
                return false;
            }*/
            return false;
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
                iSlide.setTargetPosition(1350);
                iSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                iSlide.setPower(1);
                initialized = true;
            }

//            double pos1 = iSlide.getCurrentPosition();
//            packet.put("liftPos", pos1);
//            if (pos1 < 1350) {
//                return true;
//            } else {
//                iSlide.setPower(0);
//                return false;
//            }
            return false;
        }
    }
    public  Action SlideExtend2() {
        return new IntakeSlides.SlideExtend2();
    }
    public class SlidePark implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                iSlide.setTargetPosition(0);
                iSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                iSlide.setPower(-0.8);
                initialized = true;
            }

//            double pos1 = iSlide.getCurrentPosition();
//            packet.put("SlidePos", pos1);
//            if (pos1 > 0) {
//                return true;
//            } else {
//                iSlide.setPower(0);
//                return false;
//            }
            return false;
        }
    }
    public Action SlidePark() {
        return new IntakeSlides.SlidePark();
    }

}
