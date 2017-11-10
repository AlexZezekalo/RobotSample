package com.nixsolutions.robotsample.interaction;


import android.support.annotation.NonNull;

import com.nixsolutions.robotsample.model.WrappedRobot;

public interface Interaction {

    void doInteract(@NonNull WrappedRobot robot);

    String getInteractionId();
}
