package org.machgo.procent;

/**
 * Created by marco on 14/11/14.
 */
public class WeaponPistol implements Weapon
{
    protected int _cooldown;
    private int _sinceLastShoot;

    public WeaponPistol()
    {
        this._cooldown = 30;
        this._sinceLastShoot = 30;
    }

    @Override
    public Bullet Shoot(int x, int y, Direction direction)
    {
        Bullet ret = new Bullet(x, y, 16, 16);
        ret.setMovement(direction);
        ret.set_image("assets/bullet.png");

        _sinceLastShoot = 0;

        return ret;
    }

    @Override
    public boolean canShoot()
    {
        if (_sinceLastShoot >= _cooldown)
            return true;
        return false;
    }

    @Override
    public void update()
    {
        _sinceLastShoot++;
    }
}
