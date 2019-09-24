package game.enemy;

import game.GameObject;
import game.physics.BoxCollider;
import game.player.Player;
import game.renderer.Renderer;
import tklibs.SpriteUtils;

public class Enemy extends GameObject {
    public int hp;
    public int damage;

    public Enemy() {
//        image = SpriteUtils.loadImage("assets/images/enemies/level0/pink/0.png");
//        renderer = new Renderer("assets/images/enemies/level0/pink/0.png");
        renderer = new Renderer("assets/images/enemies/level0/pink");
        position.set(0, -50);
        velocity.set(0, 5);
        velocity.setAngle(Math.toRadians(25));
        hitBox = new BoxCollider(this, 28, 28);
        hp = 3;
        damage = 1;
    }

    public void takeDamage(int damage) {
        hp -= damage;
        if(hp <= 0) {
            hp = 0;
            this.deactive();
        }
    }

    @Override
    public void run() {
        super.run(); // velocity
        this.move();
        this.checkPlayer();
    }

    private void checkPlayer() {
        Player player = GameObject.findIntersects(Player.class
                , this.hitBox);
        if(player != null) {
            player.takeDamage(damage);
            this.deactive();
        }
    }

    private void move() {
        // this.velocity.x > 0
        if(this.onGoingRight() && this.outOfBoundRight()) {
            this.reverseVelocityX();
        }
        if(this.onGoingLeft() && this.outOfBoundLeft()) {
            this.reverseVelocityX();
        }
        this.deactiveIfNeeded();
    }

    @Override
    public void reset() {
        super.reset(); // active = true
        position.set(0, -50);
        velocity.set(0, 5);
        velocity.setAngle(Math.toRadians(25));
        hp = 3;
    }

    private void deactiveIfNeeded() {
        if(position.y > 600) {
            this.deactive();
        }
    }

    private void reverseVelocityX() {
        velocity.x = -velocity.x;
    }

    private boolean outOfBoundRight() {
        return position.x > 384 - 32;
    }

    private boolean outOfBoundLeft() {
        return position.x < 0;
    }

    private boolean onGoingRight() {
        return velocity.x > 0;
    }

    private boolean onGoingLeft() {
        return velocity.x < 0;
    }
}
