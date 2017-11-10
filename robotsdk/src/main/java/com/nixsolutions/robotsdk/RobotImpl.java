package com.nixsolutions.robotsdk;


import com.nixsolutions.robotsdk.util.Logger;

public class RobotImpl implements IRobot {

    private static final Logger logger = Logger.createLogger(RobotImpl.class);

    @Override
    public void move(double distance) {
        logger.info("Robot is moving to the distance " + distance + " meters...");
    }

    @Override
    public void turn(double angle) {
        logger.info("Robot is turning to the angle " + angle + " degrees...");
    }

    @Override
    public void beep() {
        logger.info("Robot is beeping...");
    }
}
