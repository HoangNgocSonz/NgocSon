package game.player;

import game.GameObject;
import game.KeyEventPress;
import game.Vector2D;
import game.physics.BoxCollider;
import game.player.item.Item;
import game.renderer.Renderer;
import tklibs.Mathx;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends GameObject {
    public int hp;
    public boolean immune;
    public int numberBullet;

    public Player() {
//        image = SpriteUtils.loadImage("assets/images/players/straight/0.png");
        renderer = new Renderer("assets/images/players/straight/0.png");
//        position = new Vector2D(200, 500);
        position.set(200, 500);
        hitBox = new BoxCollider(this, 32, 48);
        hp = 3;
        immune = false;
        numberBullet = 3;
    }

    int renderCount = 0;
    @Override
    public void render(Graphics g) {
        if(immune) {
            renderCount++;
            if(renderCount % 3 == 0) {
                super.render(g);
            }
        } else {
            super.render(g);
        }
    }

    int count = 0; // dem so khung hinh
    @Override
    public void run() { // 60 fps >> 60 vien dan dc tao ra 1s >> 3 vien duoc tao ra 1s
        this.move();
        this.limitPosition();
        this.fire();
        this.checkImmune();
        this.checkItem();
    }

    private void checkItem() {
        Item item = GameObject.findIntersects(Item.class, hitBox);
        if(item != null) {
            item.deactive();
            item.powerUp(this);
        }
    }

    int immuneCount = 0;
    private void checkImmune() {
        if(immune) {
            immuneCount++;
            if(immuneCount > 120) {
                immune = false;
            }
        } else {
            immuneCount = 0;
        }
    }

    public void takeDamage(int damage) {
        if(!immune) {
            hp -= damage;
            if(hp <= 0) {
                hp = 0;
                this.deactive();
            } else {
                // roi vao trang thai bat tu
                immune = true;
            }
        }
    }

    private void fire() {
        count++;
        if(KeyEventPress.isFirePress && count > 20) {
//            PlayerBullet bullet = GameObject.recycle(PlayerBullet.class);
//            bullet.position.set(this.position.x, this.position.y);
//            bullet.velocity.setAngle(Math.toRadians(-90));
//
//            PlayerBullet bullet2 = GameObject.recycle(PlayerBullet.class);
//            bullet2.position.set(this.position.x - 10, this.position.y);
//            bullet2.velocity.setAngle(Math.toRadians(-135));
//
//            PlayerBullet bullet3 = GameObject.recycle(PlayerBullet.class);
//            bullet3.position.set(this.position.x + 10, this.position.y);
//            bullet3.velocity.setAngle(Math.toRadians(-45));
            double fromX = this.position.x - 10;
            double toX = this.position.x + 10;
            double stepX = (toX - fromX) / (numberBullet - 1);
            double fromAngle = -135;
            double toAngle = -45;
            double stepAngle = (toAngle - fromAngle) / (numberBullet - 1);
            for (int i = 0; i < numberBullet; i++) {
                PlayerBullet bullet = GameObject.recycle(PlayerBullet.class);
                bullet.position.set(fromX + (stepX * i), this.position.y);
                bullet.velocity.setAngle(Math.toRadians(fromAngle + (stepAngle * i)));
            }

            count = 0;
        }
    }

    private void limitPosition() {
        position.x = Mathx.clamp(position.x, 0, 384 - 32);
        position.y = Mathx.clamp(position.y, 0, 600 - 48);
    }

    private void move() {
        if(KeyEventPress.isUpPress) {
            position.y--;
        }
        if(KeyEventPress.isLeftPress) {
            position.x--;
        }
        if(KeyEventPress.isRightPress) {
            position.x++;
        }
        if(KeyEventPress.isDownPress) {
            position.y++;
        }
    }
}
