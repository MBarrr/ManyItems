package github.mbarrr.mbarrrmanyitems.Items.Handheld.Weapons.Heavy;

import github.mbarrr.mbarrrmanyitems.Items.Handheld.CrackShotItem;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Heavy weapon, high damage low attack speed
 * Special ability: Earthquake; player jumps and falls quickly, sends a shockwave infront of the user damaging entities that are on the ground
 * Slows the user significantly when equipped
 * Deals high knockback
 *
 */

public class EarthHammer extends CrackShotItem {
    /**
     * Generic constructor
     * Handheld items are all crackshot weapons
     *
     * @param itemTitle             Crackshot title of the item
     * @param customModelData       Custom model data, or 0 for none
     */
    public EarthHammer(String itemTitle, int customModelData) {
        super(itemTitle, customModelData, true);

        addEquipEffect(PotionEffectType.SLOW, 1);
        addUserEffect(PotionEffectType.SLOW, 1, 2);
    }
}
