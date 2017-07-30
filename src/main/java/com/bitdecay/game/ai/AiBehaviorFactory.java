package com.bitdecay.game.ai;

import java.util.Arrays;
import java.util.Random;

public class AiBehaviorFactory {
    private static Random rnd = new Random();
    private AiBehaviorFactory(){}

    public static AbstractAiBehavior fromName(String behavior){
        switch(behavior){
            case "TiredChase":
                return new RotatingAiBehavior(1 + rnd.nextFloat(), Arrays.asList(new ChasePlayerAiCommand(), new WaitAiCommand()));
            default: throw new RuntimeException("Could not find an Ai Behavior called: " + behavior);
        }
    }
}
