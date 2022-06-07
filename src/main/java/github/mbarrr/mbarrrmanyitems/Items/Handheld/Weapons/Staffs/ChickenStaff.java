package github.mbarrr.mbarrrmanyitems.Items.Handheld.Weapons.Staffs;

import com.shampaggon.crackshot.events.WeaponDamageEntityEvent;
import github.mbarrr.mbarrrmanyitems.Items.Handheld.CrackShotItem;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;

/**
 * Turns victim into a harmless chicken
 * provided victim is a LivingEntity,
 * not a player, not tameable,
 * and has 40 or less max health
 */
public class ChickenStaff extends CrackShotItem {

    private static final double HEALTH_CUTOFF = 40;

    /**
     * Generic constructor
     * Handheld items are all crackshot weapons
     *
     * @param itemTitle             Crackshot title of the item
     * @param customModelData       Custom model data, or 0 for none
     */
    public ChickenStaff(String itemTitle, int customModelData) {
        super(itemTitle, customModelData, false);
    }

    @Override
    protected void onProjectileHit(WeaponDamageEntityEvent e) {
        super.onProjectileHit(e);
        LivingEntity victim = (LivingEntity) e.getVictim();

        if(victim instanceof Player) return;
        if(!(e.getVictim() instanceof LivingEntity)) return;
        if(victim instanceof Tameable) return;

        double victimMaxHealth = victim.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();

        if(victimMaxHealth > HEALTH_CUTOFF) return;

        Location loc = victim.getLocation();

        Chicken chicken = (Chicken) loc.getWorld().spawnEntity(loc, EntityType.CHICKEN);
        chicken.setCustomName(victim.getCustomName());
        chicken.setPersistent(false);

        victim.remove();
    }
}
