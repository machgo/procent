package org.machgo.procent;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

/**
 * Created by marco on 31/10/14.
 */
public class Bullet extends MoveableSprite
{
    private Direction _movement;

    public Bullet(int x, int y, int height, int width)
    {
        super(x, y, height, width);
        _movement = Direction.DOWN;
        this._speed = 30;
    }


    public void setMovement(Direction dir)
    {
        _movement = dir;
    }

    public void move()
    {
        switch (_movement)
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

    @Override
    public void draw(Graphics2D g2d)
    {
        super.draw(g2d);
        Rectangle bulletRect = this.getRectangle();

        double rotationRequired = Math.toRadians(0);
        if (_movement == Direction.UP)
            rotationRequired = Math.toRadians(180);
        if (_movement == Direction.LEFT)
            rotationRequired = Math.toRadians(90);
        if (_movement == Direction.RIGHT)
            rotationRequired = Math.toRadians(270);

        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, (int) bulletRect.getWidth()/2, (int) bulletRect.getWidth()/2);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        g2d.drawImage(op.filter(this.get_image(), null), (int) bulletRect.getX(), (int) bulletRect.getY(), (int) bulletRect.getWidth(), (int) bulletRect.getHeight(), null);
    }
}
