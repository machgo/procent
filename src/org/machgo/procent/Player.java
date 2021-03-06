package org.machgo.procent;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

/**
 * Created by marco on 31/10/14.
 */
public class Player extends MoveableSprite
{
    private boolean _moving;
    private boolean _shooting;

    private int _shootingCooldown;
    private int _sinceLastShoot;

    private Weapon _weapon;

    private Direction _movingDirection;
    private Direction _shootingDirection;

    private int _health;
    private int _maxHealth;
    private int _hitCooldown;

    private int _maxSpeed;

    private int _money;

    public Player(int x, int y, int height, int width)
    {
        super(x, y, height, width);
        this._moving = false;
        this._speed = 0;
        this._maxSpeed = 10;
        this._shootingCooldown = 30;
        this._hitCooldown = 0;
        this._health = 3;
        this._maxHealth = 3;
        this._sinceLastShoot = this._shootingCooldown; //for instant first shot
        this._movingDirection = Direction.DOWN;
        this._shootingDirection = Direction.DOWN;
        this._money = 0;
        this._weapon = new WeaponPistol();
    }

    public Bullet shoot()
    {
        Bullet ret = _weapon.Shoot(_x, _y, _shootingDirection);

        return ret;
    }

    public boolean isShooting()
    {
        if (_shooting && _weapon.canShoot())
        {
            return true;
        }
        return false;
    }

    @Override
    public void draw(Graphics2D g2d)
    {
        super.draw(g2d);
        Rectangle playerRect = this.getRectangle();

        double rotationRequired = Math.toRadians(0);
        if (_orientation == Direction.UP)
            rotationRequired = Math.toRadians(180);
        if (_orientation == Direction.LEFT)
            rotationRequired = Math.toRadians(90);
        if (_orientation == Direction.RIGHT)
            rotationRequired = Math.toRadians(270);

        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, (int) playerRect.getWidth()/2, (int) playerRect.getWidth()/2);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        g2d.drawImage(op.filter(AssetLoader.PlayerImage(), null),(int) playerRect.getX(), (int) playerRect.getY(), (int) playerRect.getWidth(), (int) playerRect.getHeight(), null);
    }

    public void move()
    {
        if (this._moving)
        {
            if (this._speed < this._maxSpeed)
                this._speed++;

            super.move(this._movingDirection);
        }
        else
        {
            if (this._speed > 0)
                this._speed--;
        }

        if (this._shooting)
            this._orientation = this._shootingDirection;

        _weapon.update();
        if (_hitCooldown > 0)
            _hitCooldown--;
    }

    public Rectangle nextMove()
    {
        return this.nextMove(this._movingDirection);
    }

    public void enemyHit(int healthLost)
    {
        if (_hitCooldown == 0)
        {
            _hitCooldown = 60;
            _health -= healthLost;
        }
    }

    public Boolean isOnHitCooldown()
    {
        if (_hitCooldown > 0)
            return true;
        return false;
    }

    public boolean isAlive()
    {
        if (_health > 0)
            return true;
        else
            return false;
    }

    public int getHealth()
    {
        return _health;
    }

    public void addHealth(int addedHealth)
    {
        if (_health != _maxHealth)
            _health+=addedHealth;
    }

    public int getMaxHealth()
    {
        return _maxHealth;
    }


    public void startMoving(Direction dir)
    {
        this._moving = true;
        this._movingDirection = dir;
    }

    public void stopMoving()
    {
        this._moving = false;
    }

    public void startShooting(Direction dir)
    {
        this._shootingDirection = dir;
        this._shooting = true;
    }

    public void stopShooting()
    {
        this._shooting = false;
    }

    @Override
    public void move(Direction dir)
    {
        switch (dir)
        {
            case LEFT:
                _x = _x - _speed;
                break;
            case RIGHT:
                _x = _x + _speed;
                break;
            case UP:
                _y = _y - _speed;
                break;
            case DOWN:
                _y = _y + _speed;
                break;
            default:
                break;
        }
    }

    public int get_money()
    {
        return _money;
    }

    public void set_money(int _money)
    {
        this._money = _money;
    }

    public void addMoney(int amount)
    {
        this._money += amount;
    }

    public void removeMoney(int amount)
    {
        this._money -= amount;
    }

    public Weapon get_weapon()
    {
        return _weapon;
    }

    public void set_weapon(Weapon _weapon)
    {
        this._weapon = _weapon;
    }

}
