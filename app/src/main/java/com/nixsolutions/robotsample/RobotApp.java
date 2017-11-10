package com.nixsolutions.robotsample;


import android.app.Application;

import com.nixsolutions.robotsdk.RobotSdk;

public class RobotApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        RobotSdk.init(this);
    }
}
