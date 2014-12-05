package org.machgo.procent;

/**
 * Created by marco on 14/11/14.
 */
public class WeaponRocket extends WeaponPistol
{
    public WeaponRocket()
    {
        this._cooldown = 100;
        this._sinceLastShoot = 600;
        this._price = 100;
        this._name = "Rocket Launcher";
    }

    @Override
    public Bullet Shoot(int x, int y, Direction direction)
    {
        super.Shoot(x, y, direction);
        Bullet ret = new Bullet(x, y, 160, 160);
        ret.setMovement(direction);
        ret.set_image("assets/bullet.png");

        return ret;
    }

}
