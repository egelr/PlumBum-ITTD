package org.firstinspires.ftc.teamcode.Hardware;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.autoyellow;
import org.firstinspires.ftc.teamcode.variables;

public class Pivot {
    private Servo pivot;

    public Pivot(HardwareMap hardwareMap) {
        pivot = hardwareMap.get(Servo.class, "intakePivotServo");
    }

    public class PivotN implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            pivot.setPosition(variables.pivotVerticalAngle/300);
            return false;
        }
    }

    public Action PivotN() {
        return new Pivot.PivotN();
    }
}
