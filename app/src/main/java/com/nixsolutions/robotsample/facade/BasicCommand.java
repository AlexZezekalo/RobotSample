package com.nixsolutions.robotsample.facade;


public interface BasicCommand {

    void move(String robotId, Double distance);

    void turn(String robotId, Double angle);

    void beep(String robotId);

    String createNewRobot();
}
