package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Алексей on 20.05.2016.
 */

public class DigitsImages {
    private static TextureRegion region;


    static {
        region = new TextureRegion(new Texture("other/digits.png"));
    }


    public static TextureRegion getDigit(int digit) {
        int y = (digit < 5) ? 0 : 25;
        digit %= 5;

        return (new TextureRegion(region, 25*digit, y, 25, 25));
    }
}
