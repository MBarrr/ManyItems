package github.mbarrr.mbarrrmanyitems.Items.Handheld.Weapons.Staffs;

import com.shampaggon.crackshot.events.WeaponDamageEntityEvent;
import github.mbarrr.mbarrrmanyitems.Items.Handheld.CrackShotItem;
import org.bukkit.potion.PotionEffectType;

public class SunStaff extends CrackShotItem {

    private static final int FIRE_SECONDS = 6;

    /**
     * Generic constructor
     * Handheld items are all crackshot weapons
     *
     * @param customModelData       Custom model data, or 0 for none
     */
    public SunStaff(int customModelData) {
        super("SunStaff", customModelData, false);

        addTargetEffect(PotionEffectType.GLOWING, 6, 1);
        addUserEffect(PotionEffectType.GLOWING, 2, 1);
    }

    /**
     * Sets victim on fire
     * @param e Event
     */
    @Override
    protected void onProjectileHit(WeaponDamageEntityEvent e) {
        super.onProjectileHit(e);
        e.getVictim().setFireTicks(FIRE_SECONDS*20);
    }
}
