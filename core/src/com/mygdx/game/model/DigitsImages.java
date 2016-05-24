package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Алексей on 20.05.2016.
 */

public class DigitsImages {
    private static TextureRegion bRegion;
    private static TextureRegion wRegion;


    static {
        wRegion = new TextureRegion(new Texture("text/wDigits.png"));
        bRegion = new TextureRegion(new Texture("text/bDigits.png"));
    }


    public static TextureRegion getWhiteDigit(int digit) {
        int x = digit * 20;

        return (new TextureRegion(wRegion, x, 0, 20, 25));
    }


    public static TextureRegion getBlackDigit(int digit) {
        int x = digit * 20;

        return (new TextureRegion(bRegion, x, 0, 20, 25));
    }


}
