package com.mygdx.game.model.anima;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by ������� on 24.04.2016.
 */

public class AnimImages {
    private static Texture bang;
    private static Texture bigBang;
    private static Texture tankDef;

    static {
        bang = new Texture("other/bang.png");
        bigBang = new Texture("other/bigbang.png");
        tankDef = new Texture("other/defence.png");
    }

    public static TextureRegion getBang() {
        return new TextureRegion(bang);
    }

    public static TextureRegion getBigBang() {
        return new TextureRegion(bigBang);
    }

    public static TextureRegion getTankDef() {
        return new TextureRegion(tankDef);
    }
}
