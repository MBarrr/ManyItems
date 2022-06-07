package github.mbarrr.mbarrrmanyitems.Items.Handheld.Weapons.Other;

import github.mbarrr.mbarrrmanyitems.Items.Handheld.CrackShotItem;
import org.bukkit.potion.PotionEffectType;

/**
 * Junk weapon
 */

public class CanoePaddle extends CrackShotItem {
    /**
     * Generic constructor
     * Handheld items are all crackshot weapons
     *
     * @param customModelData       Custom model data, or 0 for none
     */
    public CanoePaddle(int customModelData) {
        super("CanoePaddle", customModelData, true);

        addEquipEffect(PotionEffectType.SLOW, 1);
    }
}
