package com.mygdx.game.model.anima;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Алексей on 24.04.2016.
 */

public class Animation {
    private Array<TextureRegion> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int frame;
    private float x;
    private float y;
    private boolean completed;

    public Animation(TextureRegion region, int frameCount, float cycleTime, float x, float y) {
        frames = new Array<TextureRegion>();
        int frameWidth = region.getRegionWidth() / frameCount;

        for (int i = 0; i < frameCount; i++) {
            frames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight()));
        }

        this.x = x;
        this.y = y;
        this.frameCount = frameCount;
        this.maxFrameTime = cycleTime / frameCount;
        this.frame = 0;
        this.completed = false;
    }

    public void update(float dt) {
        currentFrameTime += dt;

        if (currentFrameTime > maxFrameTime) {
            frame++;
            currentFrameTime = 0;
        }

        if (frame >= frameCount)
            completed = true;
    }

    public void draw(SpriteBatch batch) {
        if (!completed)
            batch.draw(getFrame(), x, y);
    }

    public TextureRegion getFrame() {
        return frames.get(frame);
    }

    public boolean isCompleted() {
        return completed;
    }
}
