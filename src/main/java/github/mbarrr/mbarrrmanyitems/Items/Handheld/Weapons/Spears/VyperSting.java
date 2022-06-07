package github.mbarrr.mbarrrmanyitems.Items.Handheld.Weapons.Spears;

import github.mbarrr.mbarrrmanyitems.Items.Handheld.CrackShotItem;
import org.bukkit.potion.PotionEffectType;

public class VyperSting extends CrackShotItem {
    /**
     * Generic constructor
     * Handheld items are all crackshot weapons
     *
     * @param itemTitle             Crackshot title of the item
     * @param customModelData       Custom model data, or 0 for none
     * @param requiresEquipListener
     */
    public VyperSting(String itemTitle, int customModelData, boolean requiresEquipListener) {
        super(itemTitle, customModelData, requiresEquipListener);

        addEquipEffect(PotionEffectType.SPEED, 1);

        addTargetEffect(PotionEffectType.POISON, 6, 2);
        addUserEffect(PotionEffectType.SPEED, 2, 2);
    }
}
