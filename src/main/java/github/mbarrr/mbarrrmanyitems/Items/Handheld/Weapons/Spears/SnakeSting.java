package github.mbarrr.mbarrrmanyitems.Items.Handheld.Weapons.Spears;

import github.mbarrr.mbarrrmanyitems.Items.Handheld.CrackShotItem;
import org.bukkit.potion.PotionEffectType;

public class SnakeSting extends CrackShotItem {
    /**
     * Generic constructor
     * Handheld items are all crackshot weapons
     *
     */
    public SnakeSting() {
        super("Snake Sting", 6, false);

        addEquipEffect(PotionEffectType.SPEED, 1);

        addTargetEffect(PotionEffectType.POISON, 6, 2);

        addUserEffect(PotionEffectType.SPEED, 2, 2);
    }
}
