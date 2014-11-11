package org.machgo.procent;

public class Heart extends Item
{
    @Override
    public Player applyToPlayer(Player player)
    {
        super.applyToPlayer(player);
        player.addHealth(1);
        return player;
    }
}
