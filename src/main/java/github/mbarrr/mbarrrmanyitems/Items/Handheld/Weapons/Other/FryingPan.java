package github.mbarrr.mbarrrmanyitems.Items.Handheld.Weapons.Other;

import github.mbarrr.mbarrrmanyitems.Items.Handheld.CrackShotItem;

/**
 * Craftable weapon with low damage and low attack speed
 *
 */

public class FryingPan extends CrackShotItem {
    /**
     * Generic constructor
     * Handheld items are all crackshot weapons
     *
     * @param customModelData       Custom model data, or 0 for none
     */
    public FryingPan(int customModelData) {
        super("Frying Pan", customModelData, false);
    }
}