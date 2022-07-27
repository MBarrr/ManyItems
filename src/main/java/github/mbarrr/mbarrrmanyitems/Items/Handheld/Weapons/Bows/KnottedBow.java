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
     */
    public KnottedBow() {
        super("Knotted Bow", 10, true);

        addTargetEffect(PotionEffectType.LEVITATION, 2, 1);
        addTargetEffect(PotionEffectType.CONFUSION, 2, 1);
    }




}
