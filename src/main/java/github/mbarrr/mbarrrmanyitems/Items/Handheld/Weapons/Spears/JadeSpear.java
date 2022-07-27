package github.mbarrr.mbarrrmanyitems.Items.Handheld.Weapons.Spears;

import github.mbarrr.mbarrrmanyitems.Items.Handheld.CrackShotItem;
import org.bukkit.potion.PotionEffectType;

/**
 * Throwable and usable as melee
 * High damaage, low attack speed,
 * Very low fire rate
 * Craftable item
 */

public class JadeSpear extends CrackShotItem {
    /**
     * Generic constructor
     * Handheld items are all crackshot weapons
     *
     */
    public JadeSpear() {
        super("Jade Spear", 4, true);

        addEquipEffect(PotionEffectType.SPEED, 1);
    }
}
