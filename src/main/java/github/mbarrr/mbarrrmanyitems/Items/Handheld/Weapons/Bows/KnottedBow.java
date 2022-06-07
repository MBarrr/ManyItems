package github.mbarrr.mbarrrmanyitems.Items.Handheld.Weapons.Bows;

import github.mbarrr.mbarrrmanyitems.Items.Handheld.CrackShotItem;
import org.bukkit.potion.PotionEffectType;

/**
 * heavy bow, deals medium damage and applies
 * levitation to target
 */

public class KnottedBow extends CrackShotItem {
    /**
     * Generic constructor
     * Handheld items are all crackshot weapons
     *
     * @param customModelData       Custom model data, or 0 for none
     */
    public KnottedBow(int customModelData) {
        super("KnottedBow", customModelData, true);

        addTargetEffect(PotionEffectType.LEVITATION, 2, 1);
        addTargetEffect(PotionEffectType.CONFUSION, 2, 1);
    }




}
