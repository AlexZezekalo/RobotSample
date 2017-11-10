package com.nixsolutions.robotsample.interaction;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

import com.nixsolutions.robotsample.model.WrappedRobot;

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

    public void setInteractionUid(@NonNull String interactionUid) {
        this.interactionUid = interactionUid;
    }

    @Override
    @CallSuper
    public void doInteract(@NonNull WrappedRobot robot) {
        setRobotUid(robot.getRobotUid());
    }

    @Override
    public String getInteractionId() {
        return interactionUid;
    }
}
