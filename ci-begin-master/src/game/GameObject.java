package game;

import game.physics.BoxCollider;
import game.player.Player;
import game.player.PlayerBullet;
import game.renderer.Renderer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameObject { // Player, Background, PlayerBullet..
    // quan li doi tuong (static)
    public static ArrayList<GameObject> objects = new ArrayList<>();

    public static <E extends GameObject> E recycle(Class<E> cls) {
        // 1. tim phan tu bi deactive >> reset phan tu >> tra ve
        // 2. neu khong tim thay phan tu bi deactive >> tao moi >> tra ve
        E object = findInactive(cls);
        if(object != null) {
            object.reset();
            return object;
        }
        try {
            object = cls.getConstructor().newInstance();
            return object;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static <E extends GameObject> E findInactive(Class<E> cls) {
        // cls ~ Player.class || Background.class..
        // E ~ Player || Background..
        for(int i = 0; i < objects.size(); i++) {
            GameObject object = objects.get(i);
            // object ~ cls
            // object.active == false
            if(cls.isAssignableFrom(object.getClass())
                && !object.active) {
                return (E)object;
            }
        }
        return null;
    }

    public static <E extends GameObject> E findIntersects(Class<E> cls
            , BoxCollider hitBox) {
        for (int i = 0; i < objects.size(); i++) {
            GameObject object = objects.get(i);
            // 1. active
            // 2. object ~ cls
            // 3. object.hitBox != null && object.hitBox.intersects(hitBox)
            if(object.active
                    && cls.isAssignableFrom(object.getClass())
                    && object.hitBox != null
                    && object.hitBox.intersects(hitBox)) {
                return (E) object;
            }
        }
        return null;
    }
    // dinh nghia doi tuong
//    public BufferedImage image; // = null
    public Renderer renderer;
    public Vector2D position;
    public boolean active;
    public Vector2D velocity;
    public BoxCollider hitBox; // = null
    public Vector2D anchor;

    public GameObject() {
        objects.add(this);
        position = new Vector2D(); // (0, 0)
        active = true;
        velocity = new Vector2D(); // (0, 0)
        anchor = new Vector2D(0.5, 0.5);
    }

    public void render(Graphics g) {
        if(renderer != null) {
            renderer.render(g, this);
        }
    }

    public void run() {
//        position.add(velocity.x, velocity.y);
        position.add(velocity);
    }

    public void deactive() {
        active = false;
    }

    public void reset() {
        active = true;
    }
}
