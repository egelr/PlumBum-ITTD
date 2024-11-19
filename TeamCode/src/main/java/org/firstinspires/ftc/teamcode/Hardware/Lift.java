package org.firstinspires.ftc.teamcode.Hardware;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.variables;

public class Lift {

private Motor lift1;
private Motor lift2;
        public Lift(HardwareMap hardwareMap) {
            lift1 = new Motor (hardwareMap, "LiftMotorRight");
            lift1.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
            //lift1.setDirection(DcMotorSimple.Direction.FORWARD);
            lift2 = new Motor (hardwareMap, "LiftMotorLeft");
            lift2.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
            lift2.setInverted(true);

            //lift1.setRunMode(Motor.RunMode.PositionControl);
            //lift2.setRunMode(Motor.RunMode.PositionControl);

            lift1.setPositionCoefficient(0.05);
            lift2.setPositionCoefficient(0.05);

            lift1.setPositionTolerance(50);
            lift2.setPositionTolerance(50);

            lift1.resetEncoder();
            lift2.resetEncoder();
        }

        public class LiftUp implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    lift1.setTargetPosition(variables.liftUpPos);
                    lift1.setRunMode(Motor.RunMode.PositionControl);
                    lift2.setTargetPosition(variables.liftUpPos);
                    lift2.setRunMode(Motor.RunMode.PositionControl);
                    lift1.set(0.9);
                    lift2.set(0.9);
                    initialized = true;
                }
                if (!lift1.atTargetPosition()) {
                    return true;
                } else {
                    lift1.set(0.1);
                    lift2.set(0.1);
                    return false;
                }
            }

        }

        public Action liftUp() {
            return new LiftUp();
        }
        public class LiftBasket implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    lift1.setTargetPosition(variables.liftBasketPos);
                    lift1.setRunMode(Motor.RunMode.PositionControl);
                    lift2.setTargetPosition(variables.liftBasketPos);
                    lift2.setRunMode(Motor.RunMode.PositionControl);
                    lift1.set(0.9);
                    lift2.set(0.9);
                    initialized = true;
                }
                if (!lift1.atTargetPosition()) {
                    return true;
                } else {
                    lift1.set(0.1);
                    lift2.set(0.1);
                    return false;
                }
            }

        }

        public Action liftBasket() {
            return new LiftBasket();
        }

        public class LiftDown implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    lift1.setTargetPosition(variables.liftDownPos);
                    lift1.setRunMode(Motor.RunMode.PositionControl);
                    lift2.setTargetPosition(variables.liftDownPos);
                    lift2.setRunMode(Motor.RunMode.PositionControl);
                    lift1.set(-1);
                    lift2.set(-1);
                    initialized = true;
                }
                if (!lift1.atTargetPosition()) {
                    return true;
                } else {
                    lift1.set(0.1);
                    lift2.set(0.1);
                    return false;
                }
            }
        }

        public Action liftDown() {
            return new LiftDown();
        }

        //liftPark
        public class LiftPark implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    lift1.setTargetPosition(0);
                    lift1.setRunMode(Motor.RunMode.PositionControl);
                    lift2.setTargetPosition(0);
                    lift2.setRunMode(Motor.RunMode.PositionControl);
                    lift1.set(0.9);
                    lift2.set(0.9);
                    initialized = true;
                }
                if (!lift1.atTargetPosition()) {
                    return true;
                } else {
                    lift1.set(0.1);
                    lift2.set(0.1);
                    return false;
                }
            }
        }

        public Action liftPark() {
            return new Lift.LiftPark();
        }


        //liftSpeciment
        public class LiftSpeciment implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    lift1.setTargetPosition(variables.liftSpecimenPos);
                    lift1.setRunMode(Motor.RunMode.PositionControl);
                    lift2.setTargetPosition(variables.liftSpecimenPos);
                    lift2.setRunMode(Motor.RunMode.PositionControl);
                    lift1.set(0.9);
                    lift2.set(0.9);
                    initialized = true;
                }
                if (!lift1.atTargetPosition()) {
                    return true;
                } else {
                    lift1.set(0.1);
                    lift2.set(0.1);
                    return false;
                }
            }
        }

        public Action liftSpeciment() {
            return new Lift.LiftSpeciment();
        }
    }
