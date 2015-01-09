package org.machgo.procent;

import java.awt.*;

/**
 * Created by marco on 14/11/14.
 */
public class WeaponPistol extends Item implements Weapon
{
    protected int _cooldown;
    protected int _sinceLastShoot;

    public WeaponPistol()
    {
        this._cooldown = 30;
        this._sinceLastShoot = 30;
        this._name = "Pistol";
    }

    @Override
    public Player applyToPlayer(Player player)
    {
        player = super.applyToPlayer(player);
        player.set_weapon(this);
        return player;
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
    public int timeUntilCanShoot()
    {
        return _cooldown - _sinceLastShoot;
    }

    @Override
    public void update()
    {
        _sinceLastShoot++;
    }

    @Override
    public void drawItem(Graphics2D g2d, int x, int y)
    {
        g2d.drawString(this._name, x, y);
    }
}
