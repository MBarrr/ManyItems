package github.mbarrr.mbarrrmanyitems.Armour.Boots;

import github.mbarrr.mbarrrmanyitems.Armour.ArmourSlot;
import github.mbarrr.mbarrrmanyitems.Armour.CustomArmour;
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

/** TODO Currently works off of the fly permission being handed back to the player upon effect renewal, this should be changed to it's own runnable with a set time to remove inconsistencies
 *
 */

public class DoubleJumpBoots extends CustomArmour {

    public DoubleJumpBoots(int tag, MbarrrManyItems instance) {
        super(ArmourSlot.BOOTS, Material.DIAMOND_BOOTS, tag, instance);

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
