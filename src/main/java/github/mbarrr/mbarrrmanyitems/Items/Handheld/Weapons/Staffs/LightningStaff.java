package github.mbarrr.mbarrrmanyitems.Items.Handheld.Weapons.Staffs;


import com.shampaggon.crackshot.events.WeaponShootEvent;
import github.mbarrr.mbarrrmanyitems.Items.Handheld.CrackShotItem;
import github.mbarrr.mbarrrmanyitems.MbarrrManyItems;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;

/**
 * Summons lightning in a line
 * in the direction the user
 * is facing
 */
public class LightningStaff extends CrackShotItem {


    private static final int LIGHTNING_VARIATION = 1; //Number of blocks the lightning can stray from side to side
    private static final double DISTANCE_BETWEEN_LIGHTNING = 1; //Number of blocks between each strike of lightning
    private static final double NUM_LIGHTNING = 4; //Number of lightning strikes
    private static final int TIME_BETWEEN_STRIKES = 10; //Number of ticks between strikes
    private final Random random = new Random();

    /**
     * Generic constructor
     * Handheld items are all crackshot weapons
     *
     * @param itemTitle             Crackshot title of the item
     * @param customModelData       Custom model data, or 0 for none
     */
    public LightningStaff(String itemTitle, int customModelData) {
        super(itemTitle, customModelData, true);

        addEquipEffect(PotionEffectType.SLOW, 1);
    }

    /**
     * Called when the fired weapon is this item
     *
     * @param e Event
     */
    @Override
    protected void onShoot(WeaponShootEvent e) {
        super.onShoot(e);

        final int[] randOffset = new int[1];
        Location loc = e.getPlayer().getLocation();
        Vector dir = loc.getDirection();
        World world = loc.getWorld();

        final int[] i = {0};

        BukkitRunnable lightningRunnable = new BukkitRunnable() {
            @Override
            public void run() {
                if(i[0] >= NUM_LIGHTNING){
                    cancel();
                    return;
                }
                //Add distance between lightning in direction player is facing
                loc.add(dir.multiply(DISTANCE_BETWEEN_LIGHTNING));

                //Generate random offset that lightning can stray by to either side
                randOffset[0] = random.nextInt(LIGHTNING_VARIATION*3)-LIGHTNING_VARIATION;

                //Strike lightning on location with offset applied
                world.strikeLightning(loc.clone().add(dir.multiply(-randOffset[0])));

                i[0]++;
            }
        };
        lightningRunnable.runTaskTimer(MbarrrManyItems.getInstance(), 0, TIME_BETWEEN_STRIKES);
    }
}
