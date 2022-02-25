package github.mbarrr.mbarrrmanyitems.Armour;

import github.mbarrr.mbarrrmanyitems.MbarrrManyItems;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

/** TODO
 *
 *
 */

public class DoubleJumpBoots extends CustomArmour{

    public DoubleJumpBoots(ArmourSlot armourSlot, Material material, int tag, MbarrrManyItems instance) {
        super(armourSlot, material, tag, instance);

        List<String> lore = new ArrayList<>();
        lore.add("§6Grants the wearer the ability to double jump!");

        setDisplayAttributes("§eDJB", lore);

        addEnchantment(Enchantment.FROST_WALKER, 1);
    }

    @EventHandler
    public void playerJumpEvent(PlayerToggleFlightEvent e){
        //get player from flight event
        Player p = e.getPlayer();

        //return if player isn't wearing armour
        if(!isPlayerWearingArmour(p)) return;

        //Cancel event so player is not flyilng, set player velocity/'boost' player, then disallow flight
        e.setCancelled(true);
        p.setVelocity(p.getVelocity().add(new Vector(0, 1, 0)));
        spawnParticles(e.getPlayer());
        p.setAllowFlight(false);
    }

    //Allow player ability to fly with 0 fly speed
    @Override
    protected void playerAddEffect(Player player){
        super.playerAddEffect(player);
        player.setAllowFlight(true);
        player.setFlySpeed(0);
    }

    //Renew player ability to fly
    @Override
    protected void playerRenewEffect(Player player){
        super.playerRenewEffect(player);
        player.setAllowFlight(true);
    }

    //Remove player effects as well as ability to fly
    @Override
    protected void removePlayerEffect(Player player) {
        super.removePlayerEffect(player);
        player.setAllowFlight(false);
    }

    private void spawnParticles(Player player){
        player.getWorld().spawnParticle(Particle.CLOUD, player.getLocation().subtract(0,2,0), 10);
    }
}
