package github.mbarrr.mbarrrmanyitems.Items.Handheld.Weapons.Staffs;

import github.mbarrr.mbarrrmanyitems.Items.Handheld.CrackShotItem;
import org.bukkit.potion.PotionEffectType;

/**
 * Freezes target
 */
public class IceStaff extends CrackShotItem {
    /**
     * Generic constructor
     * Handheld items are all crackshot weapons
     *
     * @param itemTitle             Crackshot title of the item
     * @param customModelData       Custom model data, or 0 for none
     * @param requiresEquipListener
     */
    public IceStaff(String itemTitle, int customModelData, boolean requiresEquipListener) {
        super(itemTitle, customModelData, requiresEquipListener);


        addTargetEffect(PotionEffectType.SLOW, 6, 2);
    }
}
