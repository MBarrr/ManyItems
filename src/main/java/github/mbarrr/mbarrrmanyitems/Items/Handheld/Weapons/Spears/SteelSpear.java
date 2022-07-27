package github.mbarrr.mbarrrmanyitems.Items.Handheld.Weapons.Spears;

import github.mbarrr.mbarrrmanyitems.Items.Handheld.CrackShotItem;

/**
 * Craftable weapon
 * Medium damage, low speed
 * Throwable
 */

public class SteelSpear extends CrackShotItem {
    /**
     * Generic constructor
     * Handheld items are all crackshot weapons
     *
     * @param customModelData       Custom model data, or 0 for none
     */
    public SteelSpear(int customModelData) {
        super("Steel Spear", customModelData, false);
    }
}
