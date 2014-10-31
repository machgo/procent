package org.machgo.procent;

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
}
