package github.mbarrr.mbarrrmanyitems.Items.Handheld.Weapons.Swords;

import com.shampaggon.crackshot.events.WeaponShootEvent;
import github.mbarrr.mbarrrmanyitems.Items.Handheld.CrackShotItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO implement max health debuff
 * The ebony blade is a high tier weapon,
 * it deals high damage with a fast attack speed.
 * While weilding the weapon, it's user loses 4 hearts
 * of maximum health, as well as speed and invisibilty
 */

public class EbonyBlade extends CrackShotItem {

    private static double HEALTH_DEBUFF = 1.5; //Vulnerability Debuff


    /**
     * Generic constructor
     * Handheld items are all crackshot weapons
     *
     * ItemTitle: EbonyBlade
     */
    public EbonyBlade() {
        super("Ebony Blade", 2, true);

        addTargetEffect(PotionEffectType.BLINDNESS, 20, 1);
        addUserEffect(PotionEffectType.WITHER, 20, 1);

        addEquipEffect(PotionEffectType.SPEED, 1);
        addEquipEffect(PotionEffectType.BLINDNESS, 1);
    }

    @Override
    protected void onUserHurt(EntityDamageByEntityEvent e) {
        super.onUserHurt(e);
        if (!(e.getEntity() instanceof Player)) return;
        if(!(((Player) e.getEntity()).getEquipment().getItemInMainHand().equals(getItem()))) return;

        e.setDamage(e.getDamage()*HEALTH_DEBUFF);
    }
}
