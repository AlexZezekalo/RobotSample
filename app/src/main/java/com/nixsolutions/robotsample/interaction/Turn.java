package com.nixsolutions.robotsample.interaction;

import android.support.annotation.NonNull;

import com.nixsolutions.robotsample.model.WrapRobot;


public class Turn extends BaseInteraction {

    @NonNull
    private Double angle;

    public Turn(@NonNull Double angle) {
        this.angle = angle;
    }

    @Override
    public void doInteract(@NonNull WrapRobot robot) {
        super.doInteract(robot);
        robot.getRobot().turn(angle);
    }
}
