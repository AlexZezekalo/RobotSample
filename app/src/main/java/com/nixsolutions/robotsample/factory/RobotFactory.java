package com.nixsolutions.robotsample.factory;

import com.nixsolutions.robotsample.model.WrappedRobot;
import com.nixsolutions.robotsdk.IRobot;
import com.nixsolutions.robotsdk.RobotSdk;

import java.util.UUID;

public class RobotFactory {

    public WrappedRobot createNewRobot() {
        IRobot iRobot = RobotSdk.Factory.createRobot();
        String uid = UUID.randomUUID().toString();
        return new WrappedRobot(uid, iRobot);
    }

}
