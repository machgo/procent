package org.machgo.procent;

import java.awt.*;

/**
 * Created by marco on 28/11/14.
 */
public class Boss extends Enemy
{
    public Boss(int x, int y, int height, int width, int speed)
    {
        super(x, y, height, width, speed);
    }

    @Override
    public void draw(Graphics2D g2d)
    {
        super.draw(g2d);
        Rectangle eRect = this.getRectangle();

        g2d.drawImage(AssetLoader.Enemy2Image(), (int) eRect.getX(), (int) eRect.getY(),
                (int) eRect.getWidth(), (int) eRect.getHeight(), null);

        this.drawHealthbar(g2d);
    }
}
