package github.mbarrr.mbarrrmanyitems.Items.Armour.Helmets;

import github.mbarrr.mbarrrmanyitems.MbarrrManyItems;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;


/**
 * TODO Night vision effect flashes due to the fact the potion effect is soon to run out, change this.
 *
 */

public class SlimeHead extends Helmet {

    public SlimeHead(int tag, MbarrrManyItems instance) {
        super(tag, instance);

        addEffect(PotionEffectType.NIGHT_VISION, 1);

        String title = "§aSlime Head";
        List<String> lore = new ArrayList<>();
        lore.add("§2A disguise, maybe?");

        setDisplayAttributes(title, lore);



    }

    @EventHandler
    protected void slimeTargetEvent(EntityTargetLivingEntityEvent e){
        Entity entity = e.getEntity();
        LivingEntity target = e.getTarget();

        //return if entity is not slime or if targeted entity is not player
        if(!(entity instanceof Slime)) return;
        if(!(target instanceof Player)) return;

        //check if player is wearing armour
        if(!isPlayerWearingArmour((Player) target)) return;


        //player is wearing armour
        e.setCancelled(true);
    }



}
