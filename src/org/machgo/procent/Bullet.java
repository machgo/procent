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
    private boolean _exploded;
    private Point _startingPoint;
    private int _range;

    public Bullet(int x, int y, int height, int width)
    {
        super(x, y, height, width);
        _movement = Direction.DOWN;
        this._speed = 10;
        this._exploded = false;
        _startingPoint = new Point(x,y);
        _range = 300;
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
        checkRange(new Point(_x, _y));
    }

    private void checkRange(Point positionNow)
    {
        if (_startingPoint.x - _range > positionNow.x || _startingPoint.x + _range < positionNow.x)
            explode();
        if (_startingPoint.y - _range > positionNow.y || _startingPoint.y + _range < positionNow.y)
            explode();
    }

    public Rectangle nextMove()
    {
        return this.nextMove(this._movement);
    }

    public void explode()
    {
        this._exploded = true;
    }

    public boolean isExploded ()
    {
        return this._exploded;
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
