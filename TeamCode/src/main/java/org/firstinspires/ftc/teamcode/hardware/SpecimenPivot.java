package org.firstinspires.ftc.teamcode.hardware;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Variables;

public class SpecimenPivot{
    private Servo specimenPivotServo;

    public SpecimenPivot(HardwareMap hardwareMap) {
        specimenPivotServo = hardwareMap.get(Servo.class, "specimenPivotServo");
    }

    public class normal implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            specimenPivotServo.setPosition(Variables.pivotNormalPos);
            return false;
        }
    }
    public Action normal() {
        return new SpecimenPivot.normal();
    }
    public class inverted implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            specimenPivotServo.setPosition(Variables.pivotInvertedPos);
            return false;
        }
    }
    public Action inverted() {
        return new SpecimenPivot.inverted();
    }

}