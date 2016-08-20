package com.buda.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by buda on 8/18/16.
 */
public class Plane {

    private static final int GRAVITY = -15;
    private static final int MOVEMENT =  100;

    private Rectangle bounds;
    private Vector3 position;
    private Vector3 velocity;
    private Texture plane;



    public Plane(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        plane = new Texture("plane.png");
        bounds = new Rectangle(x, y, plane.getWidth(), plane.getHeight());
    }

    public void update(float dt) {

        if(position.y > 0) {
            velocity.add(0, GRAVITY, 0);
        }

        velocity.scl(dt);
        position.add(MOVEMENT *dt , velocity.y, 0);

        if (position.y < 0) {
            position.y = 0;
        }

        velocity.scl(1 / dt);
        bounds.setPosition(position.x, position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return plane;
    }

    public void jump() {
        velocity.y = 250;
    }

    public Rectangle getBounds(){
        return  bounds;
    }

    public void dispose(){
        plane.dispose();
    }

}
