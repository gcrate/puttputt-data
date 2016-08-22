package com.gcrate.puttputt.entity;

/**
 *
 * @author gcrate
 */
public class Player {
    private int id;
    private String image;
    private double speed = -1;
    private int angle = 999;
    private int score = -1;

    public Player() {
    }

    public Player(int id, String image) {
        this.id = id;
        this.image = image;
    }

    public Player(int id, String image, double speed, int angle, int score) {
        this.id = id;
        this.image = image;
        this.speed = speed;
        this.angle = angle;
        this.score = score;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
