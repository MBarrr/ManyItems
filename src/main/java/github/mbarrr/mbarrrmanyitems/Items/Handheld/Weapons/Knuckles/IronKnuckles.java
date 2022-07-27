package github.mbarrr.mbarrrmanyitems.Items.Handheld.Weapons.Knuckles;

import com.shampaggon.crackshot.events.WeaponDamageEntityEvent;
import github.mbarrr.mbarrrmanyitems.Items.Handheld.CrackShotItem;
import org.bukkit.potion.PotionEffectType;

/**
 * Craftable weapon
 * Deals very low damage at high fire rate
 */

public class IronKnuckles extends CrackShotItem {
    /**
     * Generic constructor
     * Handheld items are all crackshot weapons
     *
     * @param customModelData       Custom model data, or 0 for none
     */
    public IronKnuckles(int customModelData) {
        super("Iron Knuckles", customModelData, true);

        addEquipEffect(PotionEffectType.SPEED, 1);
    }
}
