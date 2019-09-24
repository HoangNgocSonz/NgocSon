package game.player;

import game.GameObject;
import game.Vector2D;
import game.enemy.Enemy;
import game.physics.BoxCollider;
import game.renderer.Renderer;
import tklibs.SpriteUtils;

public class PlayerBullet extends GameObject { // PlayerBulletType2
    public int damage;

    public PlayerBullet() {
//        image = SpriteUtils.loadImage("assets/images/player-bullets/a/1.png");
        renderer = new Renderer("assets/images/player-bullets/a/1.png");
        velocity.set(0, -3);
        hitBox = new BoxCollider(this, 24,  24);
        damage = 1;
    }

    @Override
    public void run() {
        super.run(); // position.add(velocity.x, velocity.y)
        this.deactiveIfNeeded();
        this.checkEnemy();
    }

    private void checkEnemy() {
        Enemy enemy = GameObject.findIntersects(Enemy.class
                , this.hitBox);
        if(enemy != null) {
            enemy.takeDamage(damage);
            this.deactive();
        }
    }

    private void deactiveIfNeeded() {
        if(position.y < -30) {
            this.deactive();
        }
    }
}
