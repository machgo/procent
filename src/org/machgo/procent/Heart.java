package org.machgo.procent;

public class Heart extends Item
{
    public Heart()
    {
        super();
        this._price = 50;
    }

    @Override
    public Player applyToPlayer(Player player)
    {
        super.applyToPlayer(player);
        player.addHealth(1);
        return player;
    }
}
