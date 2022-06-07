package github.mbarrr.mbarrrmanyitems.Items.Handheld.Weapons.Heavy;

import github.mbarrr.mbarrrmanyitems.Items.Handheld.CrackShotItem;
import org.bukkit.potion.PotionEffectType;

public class ExecutionerSword extends CrackShotItem {
    /**
     * Generic constructor
     * Handheld items are all crackshot weapons
     *
     * @param customModelData       Custom model data, or 0 for none
     */
    public ExecutionerSword(int customModelData) {
        super("ExecutionerSword", customModelData, true);

        addEquipEffect(PotionEffectType.SLOW, 1);

    }


}
