package github.mbarrr.mbarrrmanyitems.Items.Handheld.Weapons.Bows;

import com.shampaggon.crackshot.events.WeaponDamageEntityEvent;
import github.mbarrr.mbarrrmanyitems.Items.Handheld.CrackShotItem;
import org.bukkit.entity.LivingEntity;

/**
 * Sets target alight, deals high damage
 *
 */

public class DemonicBow extends CrackShotItem {

    private final static int FIRE_SECONDS = 5*20;

    /**
     * Generic constructor
     * Handheld items are all crackshot weapons
     *
     * @param customModelData       Custom model data, or 0 for none

     */
    public DemonicBow(int customModelData) {
        super("Demonic Bow", customModelData, true);
    }

    /**
     * Sets victim on fire
     * @param e Event
     */
    @Override
    protected void onProjectileHit(WeaponDamageEntityEvent e) {
        super.onProjectileHit(e);
        e.getVictim().setFireTicks(FIRE_SECONDS);
    }
}
