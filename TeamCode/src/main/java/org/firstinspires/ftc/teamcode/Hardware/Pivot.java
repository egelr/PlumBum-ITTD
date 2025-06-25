package org.firstinspires.ftc.teamcode.hardware;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Variables;

public class Pivot {
    private Servo pivot;

    public Pivot(HardwareMap hardwareMap) {
        pivot = hardwareMap.get(Servo.class, "intakePivotServo");
    }

    public class PivotN implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            pivot.setPosition(Variables.pivotVerticalAngle/300);
            return false;
        }
    }

    public Action PivotN() {
        return new Pivot.PivotN();
    }
    public class Pivot1yellow implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            pivot.setPosition(Variables.pivot1yellowAngle/300);
            return false;
        }
    }

    public Action Pivot1yellow() {
        return new Pivot.Pivot1yellow();
    }
    public class Pivot3yellow implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            pivot.setPosition(Variables.pivot3yellowAngle/300);
            return false;
        }
    }

    public Action Pivot3yellow() {
        return new Pivot.Pivot3yellow();
    }

    public class Pivot1Colour implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            pivot.setPosition(Variables.pivot1ColourAngle/300);
            return false;
        }
    }

    public Action Pivot1Colour(){return new Pivot.Pivot1Colour();}
}