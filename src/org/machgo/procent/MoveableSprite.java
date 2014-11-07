package org.machgo.procent;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by marco on 31/10/14.
 */
public class MoveableSprite
{
    protected int _x;
    protected int _y;
    protected int _height;
    protected int _width;
    private BufferedImage _image;
    protected int _speed;
    protected Direction _orientation;

    public MoveableSprite(int x, int y, int height, int width)
    {
        this._x = x;
        this._y = y;
        this._height = height;
        this._width = width;
        this._speed = 20;
        this._orientation = Direction.DOWN;
    }

    public boolean colidesWith(MoveableSprite sprite)
    {
        if (this.getRectangle().intersects(sprite.getRectangle()))
        {
            return true;
        }

        return false;
    }

    public boolean colidesWith(Rectangle rect)
    {
        if (this.getRectangle().intersects(rect))
        {
            return true;
        }

        return false;
    }

    public Rectangle getRectangle()
    {
        Rectangle ret = new Rectangle();
        ret.x = _x - (_width / 2);
        ret.y = _y - (_height / 2);
        ret.height = _height;
        ret.width = _width;

        return ret;
    }


    public void draw (Graphics2D g2d)
    {


    }

    public void move(Direction dir)
    {
        _orientation = dir;
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

    public Rectangle nextMove(Direction dir)
    {
        _orientation = dir;
        Point pos = new Point();
        pos.x = _x;
        pos.y = _y;

        switch (dir)
        {
            case LEFT:
                pos.x = _x - _speed;
                break;
            case RIGHT:
                pos.x = _x + _speed;
                break;
            case UP:
                pos.y = _y - _speed;
                break;
            case DOWN:
                pos.y = _y + _speed;
                break;
            default:
                break;
        }

        Rectangle ret = new Rectangle();
        ret.x = pos.x - (_width / 2);
        ret.y = pos.y - (_height / 2);
        ret.height = _height;
        ret.width = _width;

        return ret;
    }

    public int get_x()
    {
        return _x;
    }

    public void set_x(int _x)
    {
        this._x = _x;
    }

    public int get_y()
    {
        return _y;
    }

    public void set_y(int _y)
    {
        this._y = _y;
    }

    public BufferedImage get_image()
    {
        return _image;
    }

    public void set_image(BufferedImage _image)
    {
        this._image = _image;
    }

    public void set_image(String filepath)
    {
        File img = new File(filepath);
        try
        {
            _image = ImageIO.read(img);
        }
        catch (Exception ex)
        {

        }
    }
}
