package org.machgo.procent;

/**
 * Created by marco on 31/10/14.
 */
public class Player extends MoveableSprite
{
    public Player(int x, int y, int height, int width)
    {
        super(x, y, height, width);
    }

    public Bullet shoot()
    {
        Bullet ret = new Bullet(_x, _y, 10, 10);
        ret.setMovement(_orientation);

        return ret;
    }
}
