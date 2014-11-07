package org.machgo.procent;

import java.awt.*;

/**
 * Created by marco on 31/10/14.
 */
public class ItemDrop extends MoveableSprite
{
    private boolean _taken;
    private int _dollarValue;

    public ItemDrop(int x, int y, int height, int width)
    {
        super(x, y, height, width);
        this._taken = false;
        this._dollarValue = 10;
    }

    @Override
    public void draw(Graphics2D g2d)
    {
        super.draw(g2d);
        Rectangle eRect = this.getRectangle();
        g2d.fillOval((int) eRect.getX(), (int) eRect.getY(), (int) eRect.getWidth(), (int) eRect.getHeight());
    }

    public void setTaken()
    {
        this._taken = true;
    }

    public boolean isTaken()
    {
        return _taken;
    }

    public int getDollarValue()
    {
        return this._dollarValue;
    }
}
