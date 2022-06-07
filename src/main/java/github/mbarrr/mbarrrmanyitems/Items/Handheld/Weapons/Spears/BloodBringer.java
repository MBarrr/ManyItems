package github.mbarrr.mbarrrmanyitems.Items.Handheld.Weapons.Spears;

import github.mbarrr.mbarrrmanyitems.Items.Handheld.CrackShotItem;
import org.bukkit.potion.PotionEffectType;

public class BloodBringer extends CrackShotItem {
    /**
     * Generic constructor
     * Handheld items are all crackshot weapons
     *
     * @param customModelData       Custom model data, or 0 for none
     */
    public BloodBringer(int customModelData) {
        super("BloodBringer", customModelData, true);

        addTargetEffect(PotionEffectType.WITHER, 4, 1);
        addTargetEffect(PotionEffectType.HUNGER, 4, 2);
    }
}
