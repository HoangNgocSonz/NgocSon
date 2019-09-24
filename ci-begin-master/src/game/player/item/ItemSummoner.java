package game.player.item;

import game.GameObject;
import tklibs.Mathx;

public class ItemSummoner extends GameObject {
    public ItemSummoner() {

    }

    @Override
    public void run() {
        super.run();
        summonItem();
    }

    int count = 0;
    private void summonItem() {
        count++;
        if(count > 120) {
            int itemType = Mathx.random(1, 2);
            switch (itemType) {
                case 1: {
                    // summon hp
                    ItemHp item = GameObject.recycle(ItemHp.class);
                    item.position.set(Mathx.random(20, 360), -30);
                    break;
                }
                case 2: {
                    // summon bullet
                    ItemBullet item = GameObject.recycle(ItemBullet.class);
                    item.position.set(Mathx.random(20, 360), -30);
                    break;
                }
            }
            count = 0;
        }
    }
}
