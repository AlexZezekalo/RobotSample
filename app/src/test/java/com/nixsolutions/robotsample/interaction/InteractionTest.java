package com.nixsolutions.robotsample.interaction;


import com.nixsolutions.robotsample.model.WrappedRobot;
import com.nixsolutions.robotsdk.IRobot;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

public class InteractionTest {

    private static final String UID = "mock_uid";

    @Mock
    private IRobot mockRobot;

    private WrappedRobot wrappedRobot;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        wrappedRobot = new WrappedRobot(UID, mockRobot);
    }

    @Test
    public void moveTest() throws Exception {
        Interaction move = new MoveInteraction(10d);
        move.doInteract(wrappedRobot);

        verify(mockRobot).move(eq(10d));
    }

    @Test
    public void turnTest() throws Exception {
        double angle = 180d;
        Interaction turn = new TurnInteraction(angle);
        turn.doInteract(wrappedRobot);

        verify(mockRobot).turn(eq(angle));
    }

    @Test
    public void beepTest() throws Exception {
        Interaction beep = new BeepInteraction();
        beep.doInteract(wrappedRobot);

        verify(mockRobot).beep();
    }



}
