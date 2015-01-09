package org.machgo.procent;

import java.awt.*;

/**
 * Created by marco on 14/11/14.
 */
public interface Weapon
{
    public Bullet Shoot(int x, int y, Direction direction);

    public boolean canShoot();

    public int timeUntilCanShoot();

    public void update();

    public void drawItem(Graphics2D g2d, int x, int y);

}

