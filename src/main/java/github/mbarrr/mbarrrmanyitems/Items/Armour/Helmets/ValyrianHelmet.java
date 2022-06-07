package github.mbarrr.mbarrrmanyitems.Items.Armour.Helmets;

import github.mbarrr.mbarrrmanyitems.MbarrrManyItems;
import org.bukkit.enchantments.Enchantment;

/**
 * Valyrian Steel Helmet
 * High damage resistance,
 * No abilities
 */
public class ValyrianHelmet extends Helmet{
    public ValyrianHelmet(int tag, MbarrrManyItems instance) {
        super(tag, instance, "ValyrianHelmet");

        addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
    }
}
