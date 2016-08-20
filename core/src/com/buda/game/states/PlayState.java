package com.buda.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.buda.game.PapePlanes;
import com.buda.game.sprites.Plane;
import com.buda.game.sprites.Tube;

/**
 * Created by buda on 8/18/16.
 */
public class PlayState extends State {

    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -50;

    private Plane plane;
    private Texture bg;
    private Texture ground;


    private Vector2 groundPos1, groundPos2;
    private Array<Tube> tubes;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        plane = new Plane(50, 300);
        cam.setToOrtho(false, PapePlanes.WIDTH / 2, PapePlanes.HEIGHT / 2);
        bg = new Texture("menuscenery.jpg");
        ground = new Texture("ground.png");
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth /2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth/2) + ground.getWidth(), GROUND_Y_OFFSET);


        tubes = new Array<Tube>();

        for (int i = 1; i <= TUBE_COUNT; i++) {
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }

    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            plane.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        plane.update(dt);
        cam.position.x = plane.getPosition().x + 80;


        for (int i = 0; i < tubes.size; i++) {

            Tube tube = tubes.get(i);

            if (cam.position.x - (cam.viewportWidth / 2) > tube.getPosTopFence().x + tube.getTopFence().getWidth()) {
                tube.reposition(tube.getPosTopFence().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }
            if (tube.collides(plane.getBounds())) {
                gsm.set(new PlayState(gsm));
            }
        }

        if (plane.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET){
            gsm.set(new PlayState(gsm));
        }

        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);
        sb.draw(plane.getTexture(), plane.getPosition().x, plane.getPosition().y);

        for (Tube tube : tubes) {
            sb.draw(tube.getTopFence(), tube.getPosTopFence().x, tube.getPosTopFence().y);
            sb.draw(tube.getBottomFence(), tube.getPosBottomFence().x, tube.getPosBottomFence().y);
        }
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);

        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        plane.dispose();
        ground.dispose();
        for (Tube tube : tubes)
            tube.dispose();
        System.out.println("Play state disposed");
    }

    private void updateGround(){
        if(cam.position.x - (cam.viewportWidth /2) > groundPos1.x + ground.getWidth())
            groundPos1.add(ground.getWidth() * 2, 0);
        if(cam.position.x - (cam.viewportWidth /2) > groundPos2.x + ground.getWidth())
            groundPos2.add(ground.getWidth() * 2, 0);
    }

}
