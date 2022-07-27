package github.mbarrr.mbarrrmanyitems.Items.Handheld.Weapons.Heavy;

import com.shampaggon.crackshot.events.WeaponShootEvent;
import github.mbarrr.mbarrrmanyitems.Items.Handheld.CrackShotItem;
import github.mbarrr.mbarrrmanyitems.MbarrrManyItems;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.weather.LightningStrikeEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


/**
 * Heavy weapon
 * Special Ability: Summon lightning
 */
public class SpectralHammer extends CrackShotItem {

    private final MbarrrManyItems instance;
    private final HashMap<Integer, Player> lightningStrikeList = new HashMap<>();
    private final Random random = new Random();
    private static final int NUM_LIGHTNING = 10;
    private static final int RADIUS = 2;
    private static final int DELAY = 5; // Quarter of a Second
    private static final double LIGHTNING_DAMAGE = 5;

    /**
     * Generic constructor
     * Handheld items are all crackshot weapons
     *
     */
    public SpectralHammer() {
        super("Spectral Hammer", 11, true);

        instance = MbarrrManyItems.getInstance();
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

                LightningStrike lightningStrike = loc.getWorld().strikeLightningEffect(loc.clone().add(randx[0], 0, randz[0]));



                lightningStrikeList.put(lightningStrike.getEntityId(), e.getPlayer());

                Bukkit.broadcastMessage(lightningStrikeList.toString());

                i[0]++;
            }
        };
        lightningRunnable.runTaskTimer(MbarrrManyItems.getInstance(), 0, DELAY);
    }

    @EventHandler
    public void lightning(LightningStrikeEvent e){
        Bukkit.broadcastMessage("LightningStrikeEvent called");
        Bukkit.broadcastMessage("Lightning ID is: "+e.getLightning().getEntityId());


        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                if(!lightningStrikeList.containsKey(e.getLightning().getEntityId())) return;

                LightningStrike lightningStrike = e.getLightning();
                Player owner = lightningStrikeList.get(lightningStrike);

                Bukkit.broadcastMessage("Lightning belongs to object");
                List<Entity> nearby = lightningStrike.getNearbyEntities(1, 1, 1);
                nearby.remove(owner);

                for(Entity entity:nearby){
                    Bukkit.broadcastMessage("Checking Entity");
                    if(entity instanceof LivingEntity){
                        Bukkit.broadcastMessage("Hurting Entity");
                        ((LivingEntity) entity).damage(LIGHTNING_DAMAGE, lightningStrikeList.get(lightningStrike.getEntityId()));
                    }
                }

                lightningStrikeList.remove(lightningStrike.getEntityId());
                Bukkit.broadcastMessage(lightningStrikeList.toString());
            }
        };

        runnable.runTaskLater(instance, 1);
    }
}
