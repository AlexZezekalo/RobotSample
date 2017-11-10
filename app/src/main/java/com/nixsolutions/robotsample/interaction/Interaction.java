package com.nixsolutions.robotsample.interaction;


import android.support.annotation.NonNull;

import com.nixsolutions.robotsample.model.WrapRobot;

public interface Interaction {

    void doInteract(@NonNull WrapRobot robot);
}
