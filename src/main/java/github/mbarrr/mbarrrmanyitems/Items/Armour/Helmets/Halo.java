package github.mbarrr.mbarrrmanyitems.Items.Armour.Helmets;

import github.mbarrr.mbarrrmanyitems.MbarrrManyItems;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

/**
 * Halo
 * Provides little protection,
 * gives wearer speed and no fall damage
 */
public class Halo extends Helmet {

    public Halo(MbarrrManyItems instance) {
        super(8, instance, "Halo");

        String title = "Halo";
        List<String> lore = new ArrayList<>();

        setDisplayAttributes(title, lore);

        addEffect(PotionEffectType.SPEED, 1);
    }

    /**
     * If wearer is damaged by fall damage,
     * cancel event
     * @param e
     */
    @EventHandler
    protected void fallDamageEvent(EntityDamageEvent e){
        EntityDamageEvent.DamageCause damageCause = e.getCause();

        if(damageCause.equals(EntityDamageEvent.DamageCause.FALL)){
            e.setCancelled(true);
        }
    }
}