package org.machgo.procent;

/**
 * Created by marco on 14/11/14.
 */
public interface Weapon
{
    public Bullet Shoot(int x, int y, Direction direction);

    public boolean canShoot();

    public void update();

}
