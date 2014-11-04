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
    private int _fps = 60;
    private int _points;

    public Game()
    {
        _player = new Player(400, 300, 32, 32);
        _player.set_image("assets/player.png");
        _bullets = new ArrayList<Bullet>();
        _enemies = new ArrayList<Enemy>();

        _points = 0;

        //TEST ENEMIES
//        for (int i = 10; i < 800; i = i + 40)
//        {
//            Enemy e1 = new Enemy(i, 50, 20, 20);
//            e1.setHealth(100);
//            _enemies.add(e1);
//        }

        Enemy e1 = new Enemy(50, 50, 20, 20);
        e1.setHealth(100);
        _enemies.add(e1);

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
        revalidate();
        repaint();
    }

    private void update()
    {
        for (Bullet bullet : _bullets)
        {
            bullet.move();

            for (Enemy enemy : _enemies)
            {
                if (bullet.colidesWith(enemy) && enemy.isAlive())
                {
                    enemy.lowerHealth(20);
                    _points = _points + 100;
                }
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
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        _player.draw(g2d);

        for (Bullet bullet : _bullets)
        {
            bullet.draw(g2d);
        }

        for (Enemy enemy : _enemies)
        {
            if (enemy.isAlive())
            {
                enemy.draw(g2d);
            }
        }

        Font font = new Font("Serif", Font.PLAIN, 96);
        g2d.setFont(font);
        g2d.drawString(Integer.toString(_points), 100, 100);
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
