package com.nixsolutions.robotsdk;


import android.content.Context;
import android.support.annotation.NonNull;

public class RobotSdk {

    public static void init(Context context) {
        //impl
    }

    public static class Factory {

        @NonNull
        public static IRobot createRobot() {
            return new RobotImpl();
        }
    }

}
