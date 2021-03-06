package github.mbarrr.mbarrrmanyitems.Items.Handheld.Weapons.Staffs;

import github.mbarrr.mbarrrmanyitems.Items.Handheld.CrackShotItem;
import org.bukkit.potion.PotionEffectType;

/**
 * Levitates and slows target
 */

public class WodnerStaff extends CrackShotItem {
    /**
     * Generic constructor
     * Handheld items are all crackshot weapons
     *
     * @param itemTitle             Crackshot title of the item
     * @param customModelData       Custom model data, or 0 for none
     * @param requiresEquipListener
     */
    public WodnerStaff(String itemTitle, int customModelData, boolean requiresEquipListener) {
        super(itemTitle, customModelData, requiresEquipListener);

        addTargetEffect(PotionEffectType.LEVITATION, 4, 1);
        addTargetEffect(PotionEffectType.GLOWING, 4, 1);
    }
}
