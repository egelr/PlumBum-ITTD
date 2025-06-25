package org.firstinspires.ftc.teamcode.hardware;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Variables;

public class IntakeSlides {
    private DcMotor iSlide;
    private ElapsedTime timer = new ElapsedTime();
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
                iSlide.setTargetPosition(Variables.intakeSlideExtendPos);
                iSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                iSlide.setPower(1);
                timer.reset();
                initialized = true;
            }

            if (iSlide.getCurrentPosition()< Variables.intakeSlideExtendPos && timer.seconds() < 1.5) {
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
                iSlide.setTargetPosition(Variables.intakeSlideExtendPos2);
                iSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                iSlide.setPower(1);
                timer.reset();
                initialized = true;
            }
            if (iSlide.getCurrentPosition()< Variables.intakeSlideExtendPos2 && timer.seconds() < 1.5) {
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
                iSlide.setTargetPosition(Variables.intakeSlideExtendPosSmall);
                iSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                iSlide.setPower(1);
                timer.reset();
                initialized = true;
            }
            if (iSlide.getCurrentPosition()< Variables.intakeSlideExtendPosSmall && timer.seconds() < 1.5) {
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
    public class SlideExtendMedium implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                iSlide.setTargetPosition(Variables.intakeSlideExtendPosMedium);
                iSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                iSlide.setPower(1);
                timer.reset();
                initialized = true;
            }
            if (iSlide.getCurrentPosition()< Variables.intakeSlideExtendPosMedium && timer.seconds() < 1.5) {
                return true;
            } else {
                iSlide.setPower(0);
                return false;
            }
        }
    }
    public  Action SlideExtendMedium() {
        return new IntakeSlides.SlideExtendMedium();
    }
    public class SlidePark implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                iSlide.setTargetPosition(0);
                iSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                iSlide.setPower(1);
                timer.reset();
                initialized = true;
            }

            if (iSlide.getCurrentPosition() > 0 && timer.seconds() < 1.5) {
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