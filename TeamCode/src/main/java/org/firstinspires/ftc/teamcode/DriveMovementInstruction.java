package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.Pivot;
import org.firstinspires.ftc.teamcode.subsystems.Robot;

public class DriveMovementInstruction extends MovementInstruction{
    private Robot autoRobot;
    private double forward;
    private double strafe;
    private double turn;
    private double time;
    ElapsedTime elapsedTime = new ElapsedTime();
    double previousTime = 0;
    public DriveMovementInstruction(Robot robot, double forward, double strafe, double turn, double time) {
        super();
        this.autoRobot = robot;
        this.forward = forward;
        this.strafe = strafe;
        this.turn = turn;
        this.time = time;
    }

    @Override
    public void executeMovement() {
        double currentTime = elapsedTime.milliseconds();

        if (currentTime - previousTime < time){
            autoRobot.driveBase.drive(forward, strafe, turn);
        }

        previousTime = currentTime;
    }

    @Override
    public boolean isComplete() {
        double currentTime = elapsedTime.milliseconds();
        return currentTime - previousTime >= time;
    }

}
