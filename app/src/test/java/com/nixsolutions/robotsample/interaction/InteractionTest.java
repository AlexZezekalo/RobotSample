package com.nixsolutions.robotsample.interaction;


import com.nixsolutions.robotsample.model.WrapRobot;
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
    IRobot testRobot;

    WrapRobot wrapRobot;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        wrapRobot = new WrapRobot(UID, testRobot);
    }

    @Test
    public void moveTest() throws Exception {
        Move move = new Move(10D);
        move.doInteract(wrapRobot);

        verify(testRobot).move(eq(10D));
    }

    @Test
    public void turnTest() throws Exception {
        Turn turn = new Turn(180D);
        turn.doInteract(wrapRobot);

        verify(testRobot).turn(eq(180D));
    }

    @Test
    public void beepTest() throws Exception {
        Beep beep = new Beep();
        beep.doInteract(wrapRobot);

        verify(testRobot).beep();
    }



}
