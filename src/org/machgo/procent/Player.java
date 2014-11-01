package org.machgo.procent;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

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
        Bullet ret = new Bullet(_x, _y, 16, 16);
        ret.setMovement(_orientation);
        ret.set_image("assets/bullet.png");

        return ret;
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

        g2d.drawImage(op.filter(this.get_image(), null),(int) playerRect.getX(), (int) playerRect.getY(), (int) playerRect.getWidth(), (int) playerRect.getHeight(), null);
    }
}
