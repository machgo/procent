package org.machgo.procent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Created by marco on 31/10/14.
 */
public class Game extends JPanel implements Runnable, KeyListener
{
    private Player _player;
    private ArrayList<Enemy> _enemies;
    private ArrayList<Bullet> _bullets;
    private boolean _isRunning;
    private int _fps = 30;

    public Game()
    {
        _player = new Player(400, 300, 20, 20);
        _bullets = new ArrayList<Bullet>();
        _enemies = new ArrayList<Enemy>();

        //TEST ENEMIES
        for (int i = 10; i < 800; i = i + 40)
        {
            Enemy e1 = new Enemy(i, 50, 20, 20);
            e1.setHealth(100);
            _enemies.add(e1);
        }

        addKeyListener(this);
        _isRunning = true;
    }

    @Override
    public void run()
    {
        while (_isRunning)
        {
            long time = System.currentTimeMillis();

            update();
            draw();

            time = (1000 / _fps) - (System.currentTimeMillis() - time);

            if (time > 0)
            {
                try
                {
                    Thread.sleep(time);
                } catch (Exception e)
                {
                }
            }
        }
    }

    private void draw()
    {
        repaint();
    }

    private void update()
    {
        for (Bullet bullet : _bullets)
        {
            bullet.move();

            for (Enemy enemy : _enemies)
            {
                if (bullet.colidesWith(enemy))
                    enemy.die();
            }
        }
        for (Enemy enemy : _enemies)
        {
            if (enemy.isAlive())
            {
                enemy.moveToPlayer(_player);
            }
        }
    }

    private void shoot(Player player)
    {
        _bullets.add(player.shoot());
    }

    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;

        Rectangle playerRect = _player.getRectangle();
        g2d.drawRect((int) playerRect.getX(), (int) playerRect.getY(), (int) playerRect.getWidth(), (int) playerRect.getHeight());

        for (Bullet bullet : _bullets)
        {
            Rectangle bRect = bullet.getRectangle();
            g2d.drawOval((int) bRect.getX(), (int) bRect.getY(), (int) bRect.getWidth(), (int) bRect.getHeight());
        }

        for (Enemy enemy : _enemies)
        {
            if (enemy.isAlive())
            {
                Rectangle eRect = enemy.getRectangle();
                g2d.fillRect((int) eRect.getX(), (int) eRect.getY(), (int) eRect.getWidth(), (int) eRect.getHeight());
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) _player.move(Direction.LEFT);
        if (key == KeyEvent.VK_RIGHT) _player.move(Direction.RIGHT);
        if (key == KeyEvent.VK_UP) _player.move(Direction.UP);
        if (key == KeyEvent.VK_DOWN) _player.move(Direction.DOWN);
        if (key == KeyEvent.VK_SPACE) shoot(_player);
    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }
}
