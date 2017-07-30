package com.bitdecay.game.animation;

/**
 * Created by Monday on 7/29/2017.
 */
public enum AnimationDirection {
    UP,
    UPRIGHT,
    RIGHT,
    DOWNRIGHT,
    DOWN,
    DOWNLEFT,
    LEFT,
    UPLEFT;

    public static boolean moreThanNinetyOff(AnimationDirection moveDirection, AnimationDirection shootDirection) {
        switch (moveDirection) {
            case UP:
                return !shootDirection.isOneOf(LEFT, UPLEFT, UP, UPRIGHT, RIGHT);
            case UPRIGHT:
                return !shootDirection.isOneOf(UPLEFT, UP, UPRIGHT, RIGHT, DOWNRIGHT);
            case RIGHT:
                return !shootDirection.isOneOf(UP, UPRIGHT, RIGHT, DOWNRIGHT, DOWN);
            case DOWNRIGHT:
                return !shootDirection.isOneOf(UPRIGHT, RIGHT, DOWNRIGHT, DOWN, DOWNLEFT);
            case DOWN:
                return !shootDirection.isOneOf(RIGHT, DOWNRIGHT, DOWN, DOWNLEFT, LEFT);
            case DOWNLEFT:
                return !shootDirection.isOneOf(DOWNRIGHT, DOWN, DOWNLEFT, LEFT, UPRIGHT);
            case LEFT:
                return !shootDirection.isOneOf(DOWN, DOWNLEFT, LEFT, UPLEFT, UP);
            case UPLEFT:
                return !shootDirection.isOneOf(DOWNLEFT, LEFT, UPLEFT, UP, UPRIGHT);
        }
        return false;
    }

    private boolean isOneOf(AnimationDirection... choices) {
        for (AnimationDirection choice : choices) {
            if (this == choice) {
                return true;
            }
        }
        return false;
    }

    public static AnimationDirection flip(AnimationDirection moveDirection) {
        switch (moveDirection) {
            case UP:
                return DOWN;
            case UPRIGHT:
                return DOWNLEFT;
            case RIGHT:
                return LEFT;
            case DOWNRIGHT:
                return UPLEFT;
            case DOWN:
                return UP;
            case DOWNLEFT:
                return UPRIGHT;
            case LEFT:
                return RIGHT;
            case UPLEFT:
                return DOWNRIGHT;
        }
        return DOWN;
    }
}
