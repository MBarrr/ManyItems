package github.mbarrr.mbarrrmanyitems.Items.Armour.Helmets.Unimplemented;

import github.mbarrr.mbarrrmanyitems.Items.Armour.Helmets.Helmet;
import github.mbarrr.mbarrrmanyitems.MbarrrManyItems;
import org.bukkit.enchantments.Enchantment;

/**
 * Valyrian Steel Helmet
 * High damage resistance,
 * No abilities
 */
public class ValyrianHelmet extends Helmet {
    public ValyrianHelmet(int tag, MbarrrManyItems instance) {
        super(8, instance, "Valyrian Helmet");

        addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
    }
}
