package com.nixsolutions.robotsample.factory;

import com.nixsolutions.robotsample.model.WrapRobot;
import com.nixsolutions.robotsdk.IRobot;
import com.nixsolutions.robotsdk.RobotSdk;

import java.util.UUID;

public class RobotFactory {

    public WrapRobot createNewRobot() {
        IRobot iRobot = RobotSdk.Factory.createRobot();
        String uid = UUID.randomUUID().toString();
        return new WrapRobot(uid, iRobot);
    }

}
