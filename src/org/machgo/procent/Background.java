package org.machgo.procent;

import java.awt.*;

/**
 * Created by marco on 14/11/14.
 */
public class Background
{
    private int _textureWidth, _textureHeight;

    public Background()
    {
        _textureHeight = 64;
        _textureWidth = 64;
    }

    public void draw (Graphics2D g2d)
    {
        Rectangle bounds = g2d.getDeviceConfiguration().getBounds();

        for (int x = 0; x < bounds.getWidth(); x+=_textureWidth)
        {
            for (int y = 0; y < bounds.getHeight(); y += _textureHeight)
            {
                g2d.drawImage(AssetLoader.Background1Image(), x, y, _textureWidth, _textureHeight, null);
            }
        }
    }
}
