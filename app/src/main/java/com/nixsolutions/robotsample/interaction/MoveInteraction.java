package com.nixsolutions.robotsample.interaction;

import android.support.annotation.NonNull;

import com.nixsolutions.robotsample.model.WrappedRobot;


public class MoveInteraction extends BaseInteraction {

    @NonNull
    private double distance;

    public MoveInteraction(@NonNull double distance) {
        this.distance = distance;
    }

    @Override
    public void doInteract(@NonNull WrappedRobot robot) {
        super.doInteract(robot);
        robot.getRobot().move(distance);
    }
}
