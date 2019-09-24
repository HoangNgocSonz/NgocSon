package game.player.item;

import game.player.Player;
import game.renderer.Renderer;
import tklibs.Mathx;

public class ItemHp extends Item {
    public ItemHp() {
        renderer = new Renderer("assets/images/items/power-up-red.png");
        velocity.set(0, Mathx.random(2, 5));
    }

    @Override
    public void powerUp(Player player) {
        player.hp++;
    }

    @Override
    public void reset() {
        super.reset();
        velocity.set(0, Mathx.random(2, 5));
    }
}
