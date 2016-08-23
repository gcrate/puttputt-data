package com.gcrate.puttputt.entity;

/**
 *
 * @author gcrate
 */
public class DataOnly {
    private double speed = -1;
    private int angle = 999;
    private int score = -1;

    public DataOnly() {
    }

    public DataOnly(double speed, int angle, int score) {
        this.speed = speed;
        this.angle = angle;
        this.score = score;
    }

    
    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    
}
