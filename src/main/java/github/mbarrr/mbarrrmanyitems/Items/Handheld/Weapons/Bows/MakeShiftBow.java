package github.mbarrr.mbarrrmanyitems.Items.Handheld.Weapons.Bows;

import github.mbarrr.mbarrrmanyitems.Items.Handheld.CrackShotItem;

/**
 * Craftable bow. Deals medium damage, is slow to fire,
 * but provides some sort of hunting buff, maybe yields more meat
 */

public class MakeShiftBow extends CrackShotItem {
    /**
     * Generic constructor
     * Handheld items are all crackshot weapons
     *
     * @param customModelData       Custom model data, or 0 for none
     */
    public MakeShiftBow(int customModelData) {
        super("Make-Shift Bow", customModelData, false);
    }
}
