package github.mbarrr.mbarrrmanyitems.Items.Handheld.Weapons.Bows;

import com.shampaggon.crackshot.events.WeaponDamageEntityEvent;
import com.shampaggon.crackshot.events.WeaponHitBlockEvent;
import github.mbarrr.mbarrrmanyitems.Items.Handheld.CrackShotItem;
import org.bukkit.event.EventHandler;

public class TeleportationBow extends CrackShotItem {
    /**
     * Generic constructor
     * Handheld items are all crackshot weapons
     *
     * @param itemTitle             Crackshot title of the item
     * @param customModelData       Custom model data, or 0 for none
     * @param requiresEquipListener
     */
    public TeleportationBow(String itemTitle, int customModelData, boolean requiresEquipListener) {
        super(itemTitle, customModelData, requiresEquipListener);
    }

    @Override
    protected void onProjectileHit(WeaponDamageEntityEvent e) {
        super.onProjectileHit(e);

        e.getPlayer().teleport(e.getVictim().getLocation());
    }

    @Override
    protected void projectileHitBlock(WeaponHitBlockEvent e) {
        super.projectileHitBlock(e);

        e.getPlayer().teleport(e.getBlock().getLocation().add(0,2,0));
    }
}
