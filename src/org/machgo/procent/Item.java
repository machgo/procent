package org.machgo.procent;

/**
 * Created by marco on 07/11/14.
 */
public class Item
{
    protected int _price;
    protected String _name;

    public Item()
    {

    }

    public Player applyToPlayer (Player player)
    {
        return player;
    }

    public int getPrice()
    {
        return _price;
    }

    public String getName() { return _name; }
}

