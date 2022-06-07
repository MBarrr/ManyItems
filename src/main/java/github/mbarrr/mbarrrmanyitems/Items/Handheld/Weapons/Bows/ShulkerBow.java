package github.mbarrr.mbarrrmanyitems.Items.Handheld.Weapons.Bows;

import com.shampaggon.crackshot.events.WeaponDamageEntityEvent;
import com.shampaggon.crackshot.events.WeaponHitBlockEvent;
import github.mbarrr.mbarrrmanyitems.Items.Handheld.CrackShotItem;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.ShulkerBullet;
import org.bukkit.event.EventHandler;

public class ShulkerBow extends CrackShotItem {


    private static final int NUM_SHULKER_BULLETS = 3;

    /**
     * Generic constructor
     * Handheld items are all crackshot weapons
     *
     * @param itemTitle             Crackshot title of the item
     * @param customModelData       Custom model data, or 0 for none
     * @param requiresEquipListener
     */
    public ShulkerBow(String itemTitle, int customModelData, boolean requiresEquipListener) {
        super(itemTitle, customModelData, requiresEquipListener);
    }

    /**
     *
     * When an arrow shot from the bow lands,
     * spawn shulker bullets in the amount:
     * NUM_SHULKER_BULLETS.
     * and set bounce to true
     *
     * @param e
     */
    @Override
    protected void projectileHitBlock(WeaponHitBlockEvent e){
        super.projectileHitBlock(e);

        if(!e.getWeaponTitle().equals(getItemTitle())) return;
        Location loc = e.getBlock().getLocation();

        spawnShulkers(loc, e.getPlayer());
    }

    /**
     *
     * When an arrow shot from the bow hits a player,
     * spawn shulker bullets in the amount:
     * NUM_SHULKER_BULLETS.
     * and set bounce to true
     *
     **/

    @Override
    protected void onProjectileHit(WeaponDamageEntityEvent e) {
        super.onProjectileHit(e);

        spawnShulkers(e.getVictim().getLocation(), e.getPlayer());
    }

    /**
     *  Spawn shulkers in the amount of
     *  NUM_SHULKER_BULLETS and set them
     *  to bounce
     * @param loc Location to spawn shulkers
     * @param shooter Player who shot the bow
     */
    private void spawnShulkers(Location loc, Player shooter){
        for(int i = 0; i < NUM_SHULKER_BULLETS; i++){
            ShulkerBullet shulkerBullet = (ShulkerBullet) loc.getWorld().spawnEntity(loc, EntityType.SHULKER_BULLET);
            shulkerBullet.setBounce(true);
            shulkerBullet.setShooter(shooter);

        }
    }
}
