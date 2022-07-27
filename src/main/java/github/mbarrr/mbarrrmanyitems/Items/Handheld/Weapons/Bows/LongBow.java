package github.mbarrr.mbarrrmanyitems.Items.Handheld.Weapons.Bows;


import github.mbarrr.mbarrrmanyitems.Items.Handheld.CrackShotItem;

/**
 * Very long range bow,
 * made of ironwood
 */
public class LongBow extends CrackShotItem {
    /**
     * Generic constructor
     * Handheld items are all crackshot weapons
     *
     * @param customModelData       Custom model data, or 0 for none
     */
    public LongBow(int customModelData) {
        super("Long Bow", customModelData, true);
    }
}
