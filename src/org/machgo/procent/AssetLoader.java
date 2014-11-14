package org.machgo.procent;

import sun.java2d.pipe.BufferedBufImgOps;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by marco on 04/11/14.
 */
public class AssetLoader
{
    private static BufferedImage _playerImage = null;
    private static BufferedImage _enemyImage = null;
    private static BufferedImage _bulletImage = null;
    private static BufferedImage _background1Image = null;
    private static BufferedImage _wall1Image = null;
    private static BufferedImage _coinImage = null;
    private static BufferedImage _enemy1Image = null;

    public static BufferedImage PlayerImage()
    {
        if (_playerImage == null)
            _playerImage = AssetLoader.LoadImage("assets/player.png");

        return _playerImage;
    }

    public static BufferedImage EnemyImage()
    {
        if (_enemyImage == null)
            _enemyImage = AssetLoader.LoadImage("assets/enemy.png");

        return _enemyImage;
    }

    public static BufferedImage BulletImage()
    {
        if (_bulletImage == null)
            _bulletImage = AssetLoader.LoadImage("assets/bullet.png");

        return _bulletImage;
    }

    public static BufferedImage Background1Image()
    {
        if (_background1Image == null)
            _background1Image = AssetLoader.LoadImage("assets/background1.png");

        return _background1Image;
    }

    public static BufferedImage Wall1Image()
    {
        if (_wall1Image == null)
            _wall1Image = AssetLoader.LoadImage("assets/wall1.png");

        return _wall1Image;
    }

    public static BufferedImage CoinImage()
    {
        if (_coinImage == null)
            _coinImage = AssetLoader.LoadImage("assets/coin.png");

        return _coinImage;
    }

    public static BufferedImage Enemy1Image()
    {
        if (_enemy1Image == null)
            _enemy1Image = AssetLoader.LoadImage("assets/enemy1.png");

        return _enemy1Image;
    }

    public static BufferedImage RotateImage(BufferedImage origImage, int rotation, int x, int y)
    {
        AffineTransform tx = AffineTransform.getRotateInstance(rotation, y, x);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        return op.filter(origImage, null);
    }

    private static BufferedImage LoadImage (String filepath)
    {
        File imageFile = new File(filepath);
        try
        {
            BufferedImage image = ImageIO.read(imageFile);
            return image;
        }
        catch (Exception ex)
        {

        }
        return null;
    }
}
