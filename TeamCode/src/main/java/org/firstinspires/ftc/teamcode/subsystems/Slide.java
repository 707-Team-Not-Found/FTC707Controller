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

/*y is open claw, B is close claw
RT is Up, LT is Down for slide
DPAD Left is Intake Pos, Right is Outtake Pos, Up is Default.
*/

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Slide {

    DcMotor slideMotor;
    PID PIDController;
    final boolean SLIDE_MOTOR_REVERSE = false;
    final double KP = 0;
    final double KI = 0;
    final double KD = 0;

    double targetPosition;
    double positionStep = 20; //change to fit
    double minPosition = 0; //change to fit
    double maxPosition = 3000; //change to fit

    final int OUTTAKEPOS = 0; //change to outtake position
    final int INTAKEPOS = 0; //change to intake position
    final int DEFAULTPOS = 0; //change to default position
    double currentPosition;
    Slide(HardwareMap map) {
        slideMotor = map.get(DcMotor.class, "slideMotor");
        slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slideMotor.setDirection(SLIDE_MOTOR_REVERSE ? DcMotorSimple.Direction.REVERSE : DcMotorSimple.Direction.FORWARD);

        PIDController = new PID(KP, KI, KD);

        targetPosition = slideMotor.getCurrentPosition();
    }
    void setSlidePosition(double LTTrigger, double RTTrigger, boolean DPADLeft, boolean DPADRight, boolean DPADUp) {

        //method ONLY sets target position
        if (!DPADLeft && !DPADRight && !DPADUp) {
            targetPosition += positionStep * (RTTrigger - LTTrigger);
            targetPosition = Math.max(minPosition, Math.min(maxPosition, targetPosition));



        } else if (DPADLeft){
            targetPosition = INTAKEPOS;

        } else if (DPADRight) {
            targetPosition = OUTTAKEPOS;

        } else {
            targetPosition = DEFAULTPOS;

        }
    }
    public void update() {
        //move slide to target position
        currentPosition = slideMotor.getCurrentPosition();

        double power = PIDController.update(targetPosition, currentPosition);

        power = Math.max(-1.0, Math.min(1.0, power));

        slideMotor.setPower(power);
    }
}
