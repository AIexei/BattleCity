package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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


    public static TextureRegion getBlackDigit(int digit) {
        digit %= 10;
        int x = digit * 21;

        return (new TextureRegion(bRegion, x, 0, 21, 25));
    }


    public static void drawMinus(SpriteBatch batch, int x, int y) {
        TextureRegion region = new TextureRegion(wRegion, 210, 0, 21, 25);
        batch.draw(region, x, y);
    }


    public static void drawNumber(SpriteBatch batch, int number, int x, int y) {
        String num = Integer.toString(number);

        for (int i = 0; i < num.length(); i++) {
            int digit = (int)(num.charAt(i)- '0');
            TextureRegion region = new TextureRegion(wRegion, digit * 21, 0, 21, 25);
            batch.draw(region, x + i*21, y);
        }
    }
}
