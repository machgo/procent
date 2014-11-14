package org.machgo.procent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
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
    private ArrayList<ItemDrop> _drops;
    private ArrayList<Wall> _walls;
    private Background _background;
    private boolean _isRunning;
    private int _fps = 60;
    private int _points;
    private int _activeRound;
    private Shop _shop;

    private GameState _gameState;

    public Game()
    {

        _player = new Player(400, 300, 32, 32);
        _bullets = new ArrayList<Bullet>();
        _enemies = new ArrayList<Enemy>();
        _drops = new ArrayList<ItemDrop>();
        _background = new Background();

        _points = 0;
        _activeRound = 0;

        _gameState = GameState.TITLE_STATE;

        _shop = new Shop(_player);

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
        if (_gameState == GameState.TITLE_STATE)
        {


        }
        else if (_gameState == GameState.MAINGAME_STATE)
        {
            updateMainGame();
        }
        else if (_gameState == GameState.PAUSEGAME_STATE)
        {

        }
    }

    private void updateMainGame()
    {

        boolean playerCollides = false;

        Rectangle nextPlayerPos = _player.nextMove();

        for (Wall wall : _walls)
        {
            if (wall.colidesWith(nextPlayerPos))
                playerCollides = true;
        }

        if (!playerCollides)
            _player.move();


        if (_player.isShooting())
        {
            _bullets.add(_player.shoot());
        }

        if (!_player.isAlive())
        {
            stopGame();
        }


        for (Bullet bullet : _bullets)
        {
            boolean bulletColides = false;
            Rectangle nextBulletPosition = bullet.nextMove();

            for (Wall wall : _walls)
            {
                if (wall.colidesWith(nextBulletPosition))
                {
                    bulletColides = true;
                    bullet.explode();
                }
            }

            if (!bulletColides)
                bullet.move();


            for (Enemy enemy : _enemies)
            {
                if (bullet.colidesWith(enemy) && enemy.isAlive() && !bullet.isExploded())
                {
                    enemy.lowerHealth(1);
                    _points = _points + 100;
                    bullet.explode();

                    if (!enemy.isAlive())
                    {
                        _drops.add(new ItemDrop(enemy.get_x(),enemy.get_y(), 30, 30));
                    }
                }
                if (enemy.colidesWith(_player) && enemy.isAlive())
                    _player.enemyHit(1);
            }
        }

        for (ItemDrop drop : _drops)
        {
            if (drop.colidesWith(_player) && !drop.isTaken())
            {
                _player.addMoney(drop.getDollarValue());
                drop.setTaken();
            }
        }

        boolean noEnemies = true;
        for (Enemy enemy : _enemies)
        {
            boolean enemyColides = false;
            Rectangle nextEnemyPos = enemy.nextMoveToPlayer(_player);
            for (Wall wall : _walls)
            {
                if (wall.colidesWith(nextEnemyPos))
                    enemyColides = true;
            }

            if (enemy.isAlive() && !enemyColides)
            {
                //TODO: collide to wall
                noEnemies = false;
                enemy.moveToPlayer(_player);
            }
        }

        if (noEnemies)
            endRound();
    }

    private void startRound()
    {
        _activeRound++;

        _bullets = new ArrayList<Bullet>();
        _drops = new ArrayList<ItemDrop>();
        _enemies = new ArrayList<Enemy>();
        _walls = new ArrayList<Wall>();

        //set border
        for (int i = 0; i < this.getHeight(); i+=40)
        {
            _walls.add(new Wall(20, i+20, 40, 40));
            _walls.add(new Wall(this.getWidth()-20, i+20, 40, 40));
        }
        for (int i = 0; i < this.getWidth(); i+=40)
        {
            _walls.add(new Wall(i+20, 20, 40, 40));
            _walls.add(new Wall(i+20, this.getHeight()-20, 40, 40));
        }

        _walls.add(new Wall(340,300,40,40));


        for (int i = 50; i < 800; i = i + 80)
        {
            Enemy e1 = new Enemy(i, 50, 20, 20, _activeRound);

            e1.setHealth(3);
            _enemies.add(e1);
        }

        _gameState = GameState.MAINGAME_STATE;
        _player = _shop.getBoostedPlayer();

    }

    private void endRound()
    {
        _gameState = GameState.ROUNDSUMMARY_STATE;
        _shop = new Shop(_player);
    }

    private void startGame()
    {
        _player = new Player(400, 300, 32, 32);
        _player.set_image("assets/player.png");
        _points = 0;
        _activeRound = 0;

        startRound();
    }

    private void stopGame()
    {
        _gameState = GameState.GAMEOVER_STATE;
    }

    private void drawHUD(Graphics2D g2d)
    {

        Font font = new Font("Monospaced.plain", Font.PLAIN, 30);
        g2d.setColor(Color.BLACK);
        g2d.setFont(font);

        g2d.drawString(Integer.toString(_player.get_money()) + "$", this.getWidth()-100, 30);


        if (_player.isOnHitCooldown())
            g2d.setColor(Color.YELLOW);
        else
            g2d.setColor(Color.RED);

        for (int i = 0; i < _player.getMaxHealth(); i++)
        {
            g2d.drawOval(30*i+10, 10, 20, 20);
        }

        for (int i = 0; i < _player.getHealth(); i++)
        {
            g2d.fillOval(30 * i + 10, 10, 20, 20);
        }
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        if (_gameState == GameState.TITLE_STATE)
        {
            Font font = new Font("Serif", Font.PLAIN, 40);
            g2d.setFont(font);
            g2d.drawString("TITLE SCREEN", 100, 100);
        }

        if (_gameState == GameState.PAUSEGAME_STATE)
        {
            Font font = new Font("Serif", Font.PLAIN, 40);
            g2d.setFont(font);
            g2d.drawString("PAUSE", 100, 100);
            drawHUD(g2d);
        }

        if (_gameState == GameState.GAMEOVER_STATE)
        {
            Font font = new Font("Serif", Font.PLAIN, 40);
            g2d.setFont(font);
            g2d.drawString("GAME OVER", 100, 100);
        }

        else if (_gameState == GameState.ROUNDSUMMARY_STATE)
        {
            Font font = new Font("Serif", Font.PLAIN, 40);
            g2d.setFont(font);
            g2d.drawString("End Of Round " + Integer.toString(_activeRound), 100, 100);
            drawHUD(g2d);
            _shop.draw(g2d);
        }

        else if (_gameState == GameState.MAINGAME_STATE)
        {
            _background.draw(g2d);
            _player.draw(g2d);

            for (Bullet bullet : _bullets)
            {
                if (!bullet.isExploded())
                {
                    bullet.draw(g2d);
                }
            }

            for (Enemy enemy : _enemies)
            {
                if (enemy.isAlive())
                {
                    enemy.draw(g2d);
                }
            }

            for (Wall wall : _walls)
            {
                wall.draw(g2d);
            }

            for (ItemDrop drop : _drops)
            {
                if (!drop.isTaken())
                    drop.draw(g2d);
            }
            drawHUD(g2d);

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
        System.out.println("key pressed");

        if (_gameState == GameState.TITLE_STATE)
            startGame();

        else if (_gameState == GameState.PAUSEGAME_STATE)
            _gameState = GameState.MAINGAME_STATE;

        else if (_gameState == GameState.ROUNDSUMMARY_STATE)
        {
            if (key == KeyEvent.VK_ENTER) startRound();
            if (key == KeyEvent.VK_0) _shop.BuyItem(0);
        }

        else if (_gameState == GameState.GAMEOVER_STATE)
        {
            if (key == KeyEvent.VK_ENTER) startGame();
        }


        if (key == KeyEvent.VK_ESCAPE) _gameState = GameState.PAUSEGAME_STATE;

        if (key == KeyEvent.VK_A) _player.startMoving(Direction.LEFT);
        if (key == KeyEvent.VK_D) _player.startMoving(Direction.RIGHT);
        if (key == KeyEvent.VK_W) _player.startMoving(Direction.UP);
        if (key == KeyEvent.VK_S) _player.startMoving(Direction.DOWN);

        if (key == KeyEvent.VK_LEFT) _player.startShooting(Direction.LEFT);
        if (key == KeyEvent.VK_RIGHT) _player.startShooting(Direction.RIGHT);
        if (key == KeyEvent.VK_UP) _player.startShooting(Direction.UP);
        if (key == KeyEvent.VK_DOWN) _player.startShooting(Direction.DOWN);
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();
        System.out.println("key released");

        if (key == KeyEvent.VK_A) _player.stopMoving();
        if (key == KeyEvent.VK_D) _player.stopMoving();
        if (key == KeyEvent.VK_W) _player.stopMoving();
        if (key == KeyEvent.VK_S) _player.stopMoving();

        if (key == KeyEvent.VK_LEFT) _player.stopShooting();
        if (key == KeyEvent.VK_RIGHT) _player.stopShooting();
        if (key == KeyEvent.VK_UP) _player.stopShooting();
        if (key == KeyEvent.VK_DOWN) _player.stopShooting();

    }
}
