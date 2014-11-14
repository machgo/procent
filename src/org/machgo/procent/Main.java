package org.machgo.procent;

import javax.swing.*;
import java.awt.*;

public class Main
{

    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        Game game = new Game();
        frame.add(game);
        frame.addKeyListener(game);
        frame.setSize(1024, 768);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 -frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        game.run();

    }
}
