package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Robot {
    Drive driveBase;
    Gamepad gamepad1;
    Gamepad gamepad2;
    Robot(HardwareMap map, Gamepad gamepad1, Gamepad gamepad2) {
        Drive driveBase = new Drive(map);
        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;
    }

    void driveWithGamePad1(){

        double forward = gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x;
        double turn = gamepad1.right_stick_x;

        driveBase.drive(forward, strafe, turn);
    }

    void intakeWithGamePad2() {

    }
}
