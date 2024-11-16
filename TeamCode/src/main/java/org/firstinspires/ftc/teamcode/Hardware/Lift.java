package org.firstinspires.ftc.teamcode.Hardware;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

    public class Lift {
        private DcMotor lift1;
        private DcMotor lift2;

        public Lift(HardwareMap hardwareMap) {
            lift1 = hardwareMap.get(DcMotor.class, "LiftMotorRight");
            lift1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            lift1.setDirection(DcMotorSimple.Direction.FORWARD);
            lift2 = hardwareMap.get(DcMotor.class, "LiftMotorLeft");
            lift2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            lift2.setDirection(DcMotorSimple.Direction.REVERSE);

            lift1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            lift2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }

        public class LiftUp implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    lift1.setTargetPosition(1550);
                    lift1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    lift2.setTargetPosition(1550);
                    lift2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    lift1.setPower(1);
                    lift2.setPower(1);
                    initialized = true;
                }

//                double pos = lift1.getCurrentPosition();
//                packet.put("liftPos", pos);
//                if (pos < 1550) {
//                    return true;
//                } else {
//                    lift1.setPower(0);
//                    lift2.setPower(0);
//
//                    return false;
//                }
                return false;
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
                    lift1.setTargetPosition(1550);
                    lift1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    lift2.setTargetPosition(1550);
                    lift2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    lift1.setPower(1);
                    lift2.setPower(1);
                    initialized = true;
                }

//                double pos = lift1.getCurrentPosition();
//                packet.put("liftPos", pos);
//                if (pos < 1550) {
//                    return true;
//                } else {
//                    lift1.setPower(0);
//                    lift2.setPower(0);
//
//                    return false;
//                }
                return false;
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
                    lift1.setTargetPosition(1100);
                    lift1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    lift2.setTargetPosition(1100);
                    lift2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    lift1.setPower(-1);
                    lift2.setPower(-1);
                    initialized = true;
                }

//                double pos = lift1.getCurrentPosition();
//                packet.put("liftPos", pos);
//                if (pos > 1120) {
//                    return true;
//                } else {
//                    lift1.setPower(0);
//                    lift2.setPower(0);
//
//                    return false;
//                }
                return false;
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
                    lift1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    lift2.setTargetPosition(0);
                    lift2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    lift1.setPower(-0.8);
                    lift2.setPower(-0.8);
                    initialized = true;
                }

//                double pos = lift1.getCurrentPosition();
//                packet.put("liftPos", pos);
//                if (pos > 0) {
//                    return true;
//                } else {
//                    lift1.setPower(0);
//                    lift2.setPower(0);
//
//                    return false;
//                }
                return false;
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
                    lift1.setTargetPosition(200);
                    lift1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    lift2.setTargetPosition(200);
                    lift2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    lift1.setPower(-0.8);
                    lift2.setPower(-0.8);
                    initialized = true;
                }

//                double pos = lift1.getCurrentPosition();
//                packet.put("liftPos", pos);
//                if (pos > 200) {
//                    return true;
//                } else {
//                    lift1.setPower(0);
//                    lift2.setPower(0);
//
//                    return false;
//                }
                return false;
            }
        }

        public Action liftSpeciment() {
            return new Lift.LiftSpeciment();
        }
    }
