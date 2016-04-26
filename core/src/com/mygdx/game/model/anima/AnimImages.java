package com.mygdx.game.model.anima;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Алексей on 24.04.2016.
 */

public class AnimImages {
    private static Texture bang;
    private static Texture bigBang;

    static {
        bang = new Texture("bang.png");
        bigBang = new Texture("bigbang.png");
    }

    public static TextureRegion getBang() {
        return new TextureRegion(bang);
    }

    public static TextureRegion getBigBang() {
        return new TextureRegion(bigBang);
    }
}
