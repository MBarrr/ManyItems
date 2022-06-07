package github.mbarrr.mbarrrmanyitems.Items.Handheld.Weapons.Bows;

import github.mbarrr.mbarrrmanyitems.Items.Handheld.CrackShotItem;
import org.bukkit.potion.PotionEffectType;

/**
 * Low damage bow with a fast shoot speed
 * Gives the user speed when equipped
 * Poisons and confuses victim on hit
 */

public class TribalBow extends CrackShotItem {
    /**
     * Generic constructor
     * Handheld items are all crackshot weapons
     *
     *
     *
     *
     * @param itemTitle             Crackshot title of the item
     * @param customModelData       Custom model data, or 0 for none
     * @param requiresEquipListener
     */
    public TribalBow(String itemTitle, int customModelData, boolean requiresEquipListener) {
        super(itemTitle, customModelData, requiresEquipListener);

        addEquipEffect(PotionEffectType.SPEED, 1);
        addTargetEffect(PotionEffectType.POISON, 5, 2);
        addTargetEffect(PotionEffectType.CONFUSION, 1, 1);
    }
}
