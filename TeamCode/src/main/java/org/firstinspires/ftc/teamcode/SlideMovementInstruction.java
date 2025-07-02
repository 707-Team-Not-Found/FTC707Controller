package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.Pivot;
import org.firstinspires.ftc.teamcode.subsystems.Robot;
import org.firstinspires.ftc.teamcode.subsystems.Slide;

public class SlideMovementInstruction extends MovementInstruction{

    private Slide slideUnit;
    private double targetPosition;
    private double time;
    ElapsedTime elapsedTime = new ElapsedTime();
    double previousTime = 0;
    public SlideMovementInstruction(Robot robot, double targetPosition, double time) {
        super();
        this.slideUnit = robot.slides;
        this.targetPosition = targetPosition;
        this.time = time;
    }

    @Override
    public void executeMovement() {
        double currentTime = elapsedTime.milliseconds();

        if (currentTime - previousTime < time){
            slideUnit.setTargetPosition(targetPosition);
        }

        previousTime = currentTime;
    }

    @Override
    public boolean isComplete() {
        double currentTime = elapsedTime.milliseconds();
        return currentTime - previousTime >= time;
    }

}
