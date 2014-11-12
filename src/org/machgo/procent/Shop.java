package org.machgo.procent;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by marco on 07/11/14.
 */
public class Shop
{
    private ArrayList<Item> _availableItems;
    private Player _player;

    public Shop(Player player)
    {
        _player = player;
        _availableItems = new ArrayList<Item>();

        populateShop(1);

    }

    private void populateShop(int round)
    {
        _availableItems.add(new Heart());
    }

    public Player getBoostedPlayer()
    {
        return _player;
    }

    public void BuyItem(int selection)
    {
        Item item = _availableItems.get(selection);
        if (_player.get_money() >= item.getPrice() )
        {
            _player.removeMoney(item.getPrice());
            _player = item.applyToPlayer(_player);
        }
    }

    public void draw (Graphics2D g2d)
    {
        g2d.drawString("ITEM SHOP, please buy your stuff now...", 150, 150);

        for (int i = 0; i < _availableItems.size(); i++)
        {
            int height = 200+(30*i);

            g2d.drawString(Integer.toString(i) + " ---> HEART", 200, height);
            g2d.drawString(Integer.toString(_availableItems.get(i).getPrice()) + "$", 600, height);
        }

    }
}
