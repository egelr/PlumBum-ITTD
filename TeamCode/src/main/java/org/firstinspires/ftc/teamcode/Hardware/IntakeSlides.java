package org.firstinspires.ftc.teamcode.Hardware;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.autoyellow;
import org.firstinspires.ftc.teamcode.variables;

public class IntakeSlides {
    private Motor iSlide;

    public IntakeSlides(HardwareMap hardwareMap) {
        iSlide = new Motor (hardwareMap, "IntakeMotor");
        iSlide.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        //iSlide.setDirection(DcMotorSimple.Direction.FORWARD);
        //iSlide.setRunMode(Motor.RunMode.PositionControl);

        iSlide.setPositionCoefficient(0.05);

        iSlide.setPositionTolerance(50);

        iSlide.resetEncoder();
    }
    public class SlideExtend implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                iSlide.setTargetPosition(variables.intakeSlideExtendPos);
                iSlide.setRunMode(Motor.RunMode.PositionControl);
                iSlide.set(0.9);
                initialized = true;
            }

            if (!iSlide.atTargetPosition()) {
                return true;
            } else {
                iSlide.set(0.1);
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
                iSlide.setRunMode(Motor.RunMode.PositionControl);
                iSlide.set(0.9);
                initialized = true;
            }
            if (!iSlide.atTargetPosition()) {
                return true;
            } else {
                iSlide.set(0.1);
                return false;
            }
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
                iSlide.setRunMode(Motor.RunMode.PositionControl);
                iSlide.set(0.9);
                initialized = true;
            }

            if (!iSlide.atTargetPosition()) {
                return true;
            } else {
                iSlide.set(0.1);
                return false;
            }

        }
    }
    public Action SlidePark() {
        return new IntakeSlides.SlidePark();
    }

}
