package org.machgo.procent;

import java.awt.*;

/**
 * Created by marco on 07/11/14.
 */
public class Wall extends MoveableSprite
{
    public Wall(int x, int y, int height, int width)
    {
        super(x, y, height, width);
    }

    @Override
    public void draw(Graphics2D g2d)
    {
        super.draw(g2d);
        g2d.setColor(Color.GRAY);
        Rectangle eRect = this.getRectangle();
        g2d.fillRect((int) eRect.getX(), (int) eRect.getY(), (int) eRect.getWidth(), (int) eRect.getHeight());
    }
}
