package github.mbarrr.mbarrrmanyitems.Items.Handheld.Weapons.Knuckles;

import github.mbarrr.mbarrrmanyitems.Items.Handheld.CrackShotItem;
import org.bukkit.potion.PotionEffectType;

/**
 * High tier weapon
 * Fast fire rate, medium damage
 * Dropped by the alpha dire wolf
 */

public class DireClaws extends CrackShotItem {
    /**
     * Generic constructor
     * Handheld items are all crackshot weapons
     *
     * @param customModelData       Custom model data, or 0 for none
     */
    public DireClaws(int customModelData) {
        super("Wolf Claws", customModelData, true);

        addEquipEffect(PotionEffectType.SPEED, 1);
        addEquipEffect(PotionEffectType.NIGHT_VISION, 1);

        addUserEffect(PotionEffectType.JUMP, 2, 1);
        addUserEffect(PotionEffectType.SPEED, 2, 2);
    }


}
