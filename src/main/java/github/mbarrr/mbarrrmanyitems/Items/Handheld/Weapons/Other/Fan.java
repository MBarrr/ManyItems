package github.mbarrr.mbarrrmanyitems.Items.Handheld.Weapons.Other;

import com.shampaggon.crackshot.events.WeaponDamageEntityEvent;
import github.mbarrr.mbarrrmanyitems.Items.Handheld.CrackShotItem;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

/**
 * Pushes enemies away, deals no damage
 *
 */

public class Fan extends CrackShotItem {


    private static final int VELOCITY = 5;

    /**
     * Generic constructor
     * Handheld items are all crackshot weapons
     *
     * @param itemTitle             Crackshot title of the item
     * @param customModelData       Custom model data, or 0 for none
     */
    public Fan(String itemTitle, int customModelData) {
        super(itemTitle, customModelData, true);

        addEquipEffect(PotionEffectType.SPEED, 1);
    }

    /**
     * Push victim back by
     * a factor of VELOCITY,
     * in the direction in which
     * the player is looking
     * @param e
     */
    @Override
    protected void onProjectileHit(WeaponDamageEntityEvent e) {
        super.onProjectileHit(e);

        Player player = e.getPlayer();
        Entity victim = e.getVictim();

        Vector knockbackVector = player.getLocation().getDirection().multiply(VELOCITY);
        Vector victimVelocity = victim.getVelocity();

        victim.setVelocity(victimVelocity.add(knockbackVector));
    }
}

