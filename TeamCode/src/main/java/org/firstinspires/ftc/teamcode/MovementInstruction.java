package org.firstinspires.ftc.teamcode;

public abstract class MovementInstruction {
    protected MovementInstruction (){}

    public abstract void executeMovement();

    public abstract boolean isComplete();
}
