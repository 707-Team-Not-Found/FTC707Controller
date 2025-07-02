/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.subsystems;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Robot {
    public Drive driveBase;
    Gamepad gamepad1;
    Gamepad gamepad2;
    public Slide slides;
    public Pivot pivot;
    public Claw claw;

    public Robot(HardwareMap map, Gamepad gamepad1, Gamepad gamepad2) {
        driveBase = new Drive(map);
        slides = new Slide(map);
        pivot = new Pivot(map);
        claw = new Claw(map);
        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;
    }

    public void driveWithGamePad1(){

        double forward = gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x;
        double turn = gamepad1.right_stick_x;

        boolean controlSpeed = gamepad1.x;

        
        driveBase.drive(forward, strafe, turn);
    }

    public void driveWithGamePad1Test(HardwareMap map){

        boolean YButton = gamepad1.y;
        boolean BButton = gamepad1.b;
        boolean AButton = gamepad1.a;
        boolean XButton = gamepad1.x;

        if(YButton){map.get(DcMotor.class, "frontLeftMotor").setPower(1);}
        if(BButton){map.get(DcMotor.class, "backLeftMotor").setPower(1);}
        if(AButton){map.get(DcMotor.class, "frontRightMotor").setPower(1);}
        if(XButton){map.get(DcMotor.class, "backRightMotor").setPower(1);}
    }

    public void driveWithGamePad2() {
        double forward = gamepad2.left_stick_y;
        double strafe = gamepad2.left_stick_x;
        double turn = gamepad2.right_stick_x;

        if(Math.abs(forward) > 0.1 || Math.abs(strafe) > 0.1 || Math.abs(turn) > 0.1){
            driveBase.drive(forward*0.3, strafe*0.3, turn*0.3);
        }
    }

    public void controlWithGamePad2() {
        double LTAnalogue = gamepad2.left_trigger;
        double RTAnalogue = gamepad2.right_trigger;

        boolean LBumper = gamepad2.left_bumper;
        boolean RBumper = gamepad2.right_bumper;

        boolean DPADLeft = gamepad2.dpad_left;
        boolean DPADRight = gamepad2.dpad_right;
        boolean DPADUp = gamepad2.dpad_up;

        boolean YButton = gamepad2.y;
        boolean BButton = gamepad2.b;

        slides.setSlidePosition(LTAnalogue, RTAnalogue, DPADLeft, DPADRight, DPADUp);
        slides.update();

        pivot.updatePivotServoAngle();
        pivot.pivotControl(LBumper, RBumper, DPADLeft, DPADRight, DPADUp);

        claw.clawControl(YButton, BButton);
    }
}
