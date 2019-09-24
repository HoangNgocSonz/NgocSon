package game.player.item;

import game.player.Player;
import game.renderer.Renderer;
import tklibs.Mathx;

public class ItemBullet extends Item {
    public ItemBullet() {
        renderer = new Renderer("assets/images/items/power-up-blue.png");
        velocity.set(0, Mathx.random(2, 5));
    }

    @Override
    public void powerUp(Player player) {
        player.numberBullet += 2;
        player.numberBullet = Mathx.clamp(player.numberBullet, 3, 11);
    }

    @Override
    public void reset() {
        super.reset();
        velocity.set(0, Mathx.random(2, 5));
    }
}
