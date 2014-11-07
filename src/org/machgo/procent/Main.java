package org.machgo.procent;

import javax.swing.*;

public class Main
{

    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        Game game = new Game();
        frame.add(game);
        frame.addKeyListener(game);
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        game.run();

    }
}
