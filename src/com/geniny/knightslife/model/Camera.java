package com.geniny.knightslife.model;

public class Camera {

    private float cameraX = 0f;
    private float cameraY = 0f;

    public void update(float newCaX , float newCamY)
    {
        this.cameraX = newCaX;
        this.cameraY = newCamY;
    }

    public float getCameraX() {
        return cameraX;
    }

    public float getCameraY() {
        return cameraY;
    }
}
