package com.buda.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by buda on 8/19/16.
 */
public class Tube {

    public static final int TUBE_WIDTH = 52;

    private static final int FUCTUATION = 130;
    private static final int TUBE_GAP = 100;
    private static final int LOWEST_OPENING = 120;


    private Texture bottomFence, topFence;
    private Vector2 posBottomFence, posTopFence;
    private Random rand;
    private Rectangle boundsTop, boundsBot;



    public Tube(float x) {

        bottomFence = new Texture("fence.png");
        topFence = new Texture("top.png");

        rand = new Random();
        posTopFence = new Vector2(x, rand.nextInt(FUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBottomFence = new Vector2(x, posTopFence.y - TUBE_GAP - bottomFence.getHeight());

        boundsTop = new Rectangle(posTopFence.x, posTopFence.y, topFence.getWidth(), topFence.getHeight());
        boundsBot = new Rectangle(posBottomFence.x, posBottomFence.y, bottomFence.getWidth(), bottomFence.getHeight());

    }

    public Texture getBottomFence() {
        return bottomFence;
    }

    public Texture getTopFence() {
        return topFence;
    }

    public Vector2 getPosBottomFence() {
        return posBottomFence;
    }

    public Vector2 getPosTopFence() {return posTopFence;}

    public void reposition(float x){
        posTopFence.set(x, rand.nextInt(FUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBottomFence.set(x, posTopFence.y - TUBE_GAP - bottomFence.getHeight());
        boundsTop.setPosition(posTopFence.x, posTopFence.y);
        boundsBot.setPosition(posBottomFence.x, posBottomFence.y);
    }

    public boolean collides(Rectangle player){
        return  player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }

    public void dispose(){
        topFence.dispose();
        bottomFence.dispose();
    }

}
