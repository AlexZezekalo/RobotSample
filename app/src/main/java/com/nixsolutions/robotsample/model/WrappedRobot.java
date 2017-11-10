package com.nixsolutions.robotsample.model;


import android.support.annotation.NonNull;

import com.nixsolutions.robotsdk.IRobot;

public class WrappedRobot {

    @NonNull
    private String robotUid;

    @NonNull
    private IRobot robot;

    public WrappedRobot(@NonNull String robotUid, @NonNull IRobot robot) {
        this.robotUid = robotUid;
        this.robot = robot;
    }

    @NonNull
    public String getRobotUid() {
        return robotUid;
    }

    @NonNull
    public IRobot getRobot() {
        return robot;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("WrapRobot{");
        sb.append("robotUid='").append(robotUid).append('\'');
        sb.append(", robot=").append(robot);
        sb.append('}');
        return sb.toString();
    }
}
