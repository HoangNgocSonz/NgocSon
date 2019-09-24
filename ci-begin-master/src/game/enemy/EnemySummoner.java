package game.enemy;

import game.GameObject;

public class EnemySummoner extends GameObject {
    public EnemySummoner() { }

    int count = 0;
    @Override
    public void run() {
        count++;
        if(count > 120) {
//            Enemy enemy = new Enemy();
            Enemy enemy = GameObject.recycle(Enemy.class);
            count = 0;
        }
        System.out.println(GameObject.objects.size());
    }
}
