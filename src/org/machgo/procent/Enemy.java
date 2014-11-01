package org.machgo.procent;

import java.awt.*;

/**
 * Created by marco on 31/10/14.
 */
public class Enemy extends MoveableSprite
{
    private int _maxHealth;
    private int _health;

    public Enemy(int x, int y, int height, int width)
    {
        super(x, y, height, width);
        this._speed = 1;
    }

    public void setHealth(int health)
    {
        _health = health;
        _maxHealth = health;
    }

    public void die()
    {
        _health = 0;
    }

    public boolean isAlive()
    {
        if (_health > 0)
            return true;
        return false;
    }

    public void lowerHealth(int health)
    {
        _health = _health - health;
    }

    public void drawHealthbar(Graphics2D g2d)
    {
        int barLength = 40;
        int barHealthLength = barLength * (_health/_maxHealth);


        int xRect = _x-20;
        int yRect = _y-20;
        g2d.drawRect(xRect, yRect, barLength, 5);
        g2d.fillRect(xRect,yRect, barHealthLength, 5);
    }

    //Simple AI, go to player :)
    public void moveToPlayer(Player player)
    {
        int xDistance = player.get_x() - this.get_x();
        int yDistance = player.get_y() - this.get_y();

        if (Math.abs(xDistance) > Math.abs(yDistance))
        {
            if (xDistance < 0)
                move(Direction.LEFT);
            else
                move(Direction.RIGHT);
        }
        else
        {
            if (yDistance < 0)
                move(Direction.UP);
            else
                move(Direction.DOWN);
        }
    }

    @Override
    public void draw(Graphics2D g2d)
    {
        super.draw(g2d);
        Rectangle eRect = this.getRectangle();
        g2d.fillRect((int) eRect.getX(), (int) eRect.getY(), (int) eRect.getWidth(), (int) eRect.getHeight());
        this.drawHealthbar(g2d);
    }
}
