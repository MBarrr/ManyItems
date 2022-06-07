package github.mbarrr.mbarrrmanyitems.Items.Handheld.Weapons.Bows;

import com.shampaggon.crackshot.events.WeaponDamageEntityEvent;
import com.shampaggon.crackshot.events.WeaponHitBlockEvent;
import github.mbarrr.mbarrrmanyitems.Items.Handheld.CrackShotItem;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.potion.PotionEffectType;

public class LightningBow extends CrackShotItem {
    /**
     * Generic constructor
     * Handheld items are all crackshot weapons
     *
     * @param itemTitle             Crackshot title of the item
     * @param customModelData       Custom model data, or 0 for none
     */
    public LightningBow(String itemTitle, int customModelData) {
        super(itemTitle, customModelData, true);

        addEquipEffect(PotionEffectType.SPEED, 1);
        addTargetEffect(PotionEffectType.GLOWING, 1, 1);
    }

    /**
     * When an arrow shot from the bow lands,
     * spawn lightning at the location
     * @param e
     */
    @Override
    protected void projectileHitBlock(WeaponHitBlockEvent e){
        super.projectileHitBlock(e);

        if(!e.getWeaponTitle().equals(getItemTitle())) return;

        Location loc = e.getBlock().getLocation();
        spawnLightning(loc);
    }

    /**
     * When an arrow shot from the bow hits,
     * spawn lightning at the location
     * @param e
     */
    @Override
    protected void onProjectileHit(WeaponDamageEntityEvent e) {
        super.onProjectileHit(e);

        Location loc = e.getVictim().getLocation();
        spawnLightning(loc);
    }

    /**
     * Spawn lightning
     * @param loc location
     */
    private void spawnLightning(Location loc){
        loc.getWorld().strikeLightning(loc);
    }
}
