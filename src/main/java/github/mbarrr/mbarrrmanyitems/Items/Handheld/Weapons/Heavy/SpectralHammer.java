package github.mbarrr.mbarrrmanyitems.Items.Handheld.Weapons.Heavy;

import com.shampaggon.crackshot.events.WeaponShootEvent;
import github.mbarrr.mbarrrmanyitems.Items.Handheld.CrackShotItem;
import github.mbarrr.mbarrrmanyitems.MbarrrManyItems;
import org.bukkit.Location;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;


/**
 * Heavy weapon
 * Special Ability: Summon lightning
 */
public class SpectralHammer extends CrackShotItem {

    private final Random random = new Random();
    private static final int NUM_LIGHTNING = 8;
    private static final int RADIUS = 8;
    private static final int DELAY = 10; // Half a Second

    /**
     * Generic constructor
     * Handheld items are all crackshot weapons
     *
     * @param customModelData       Custom model data, or 0 for none
     */
    public SpectralHammer(int customModelData) {
        super("SpectralHammer", customModelData, true);

        addEquipEffect(PotionEffectType.SLOW, 1);
    }

    /**
     * Called when the fired weapon is this item
     * Summons lightning at the location where the item was fired
     * @param e Event
     */
    @Override
    protected void onShoot(WeaponShootEvent e) {
        super.onShoot(e);
        final int[] i = {0};
        final int[] randx = new int[1];
        final int[] randz = new int[1];
        final Location loc = e.getPlayer().getLocation();

        BukkitRunnable lightningRunnable = new BukkitRunnable() {
            @Override
            public void run() {
                if(i[0] == NUM_LIGHTNING){
                    cancel();
                    return;
                }

                randx[0] = random.nextInt(RADIUS*2) - RADIUS;
                randz[0] = random.nextInt(RADIUS*2) - RADIUS;

                loc.getWorld().strikeLightning(loc.clone().add(randx[0], 0, randz[0]));

                i[0]++;
            }
        };
        lightningRunnable.runTaskTimer(MbarrrManyItems.getInstance(), 0, DELAY);
    }
}
