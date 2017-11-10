package com.nixsolutions.robotsample.interaction;

import android.support.annotation.NonNull;

import com.nixsolutions.robotsample.model.WrapRobot;

import java.util.UUID;


public abstract class BaseInteraction implements Interaction {

    @NonNull
    private String interactionUid;

    @NonNull
    private String robotUid = "";

    BaseInteraction() {
        this.interactionUid = UUID.randomUUID().toString();
    }

    @NonNull
    public String getRobotUid() {
        return robotUid;
    }

    private void setRobotUid(@NonNull String robotUid) {
        this.robotUid = robotUid;
    }

    @NonNull
    public String getInteractionUid() {
        return interactionUid;
    }

    public void setInteractionUid(@NonNull String interactionUid) {
        this.interactionUid = interactionUid;
    }

    @Override
    public void doInteract(@NonNull WrapRobot robot) {
        setRobotUid(robot.getRobotUid());
    }
}
