package github.mbarrr.mbarrrmanyitems.Armour;

import github.mbarrr.mbarrrmanyitems.MbarrrManyItems;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class TestArmour extends CustomArmour{

    public TestArmour(ArmourSlot armourSlot, Material material, int tag, MbarrrManyItems instance) {
        super(armourSlot, material, tag, instance);

        addEffect(PotionEffectType.SPEED, 2);
        addEffect(PotionEffectType.FIRE_RESISTANCE, 1);

        addEnchantment(Enchantment.PROTECTION_FALL, 1);
        addEnchantment(Enchantment.DURABILITY, 2);

        List<String> lore = new ArrayList<>();
        lore.add("test");
        lore.add("armour");

        setDisplayAttributes("Test armour", lore);
    }
}
