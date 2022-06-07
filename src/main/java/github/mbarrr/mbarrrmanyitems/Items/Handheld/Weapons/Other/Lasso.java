package github.mbarrr.mbarrrmanyitems.Items.Handheld.Weapons.Other;

import com.shampaggon.crackshot.events.WeaponDamageEntityEvent;
import github.mbarrr.mbarrrmanyitems.Items.Handheld.CrackShotItem;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Lasso extends CrackShotItem {
    /**
     * Generic constructor
     * Handheld items are all crackshot weapons
     *
     * @param itemTitle             Crackshot title of the item
     * @param customModelData       Custom model data, or 0 for none
     * @param requiresEquipListener
     */

    private static final int VELOCITY = 2;

    public Lasso(String itemTitle, int customModelData, boolean requiresEquipListener) {
        super(itemTitle, customModelData, requiresEquipListener);
    }

        /**
         * Push victim towards player
         * a factor of VELOCITY,
         * @param e
         */
        @Override
        protected void onProjectileHit(WeaponDamageEntityEvent e) {
            super.onProjectileHit(e);

            Player player = e.getPlayer();
            Entity victim = e.getVictim();



            Vector knockbackVector = victim.getLocation().toVector().subtract(player.getLocation().toVector());

            knockbackVector.normalize();

            Vector victimVelocity = victim.getVelocity();
            victim.setVelocity(victimVelocity.add(knockbackVector));
        }
}
