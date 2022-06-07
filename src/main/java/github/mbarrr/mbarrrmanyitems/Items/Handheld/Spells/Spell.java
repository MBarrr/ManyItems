package github.mbarrr.mbarrrmanyitems.Items.Handheld.Spells;

import com.shampaggon.crackshot.events.WeaponPreShootEvent;
import com.shampaggon.crackshot.events.WeaponShootEvent;
import github.mbarrr.mbarrrmanyitems.Items.Armour.Helmets.CrystalCrown;
import github.mbarrr.mbarrrmanyitems.Items.Handheld.CrackShotItem;
import github.mbarrr.mbarrrmanyitems.MbarrrManyItems;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Hashtable;

public class Spell extends CrackShotItem {


    private static final long TICK_TIME = 1;
    private int castTime;
    private CrystalCrown crystalCrown;
    private final HashMap<Player, Long> players = new HashMap<>();

    /**
     * Generic constructor
     * Handheld items are all crackshot weapons
     *
     * @param itemTitle             Crackshot title of the item
     * @param customModelData       Custom model data, or 0 for none
     * @param requiresEquipListener
     */
    public Spell(MbarrrManyItems instance, String itemTitle, int customModelData, boolean requiresEquipListener, int castTime) {
        super(itemTitle, customModelData, requiresEquipListener);
        this.castTime = castTime * 20;
        this.crystalCrown = (CrystalCrown) instance.getArmour("CrystalCrown");
    }

    /**
     * Called when the fired weapon is this item
     *
     * @param e Event
     */
    @Override
    protected void onShoot(WeaponShootEvent e) {
        super.onShoot(e);

        if (castTime == 0){
            onCastSuccess(e);
            return;
    }

        Player player = e.getPlayer();
        final double[] i = {castTime};

        if(crystalCrown.isPlayerWearingArmour(player)) i[0] = i[0]/2;

        BukkitRunnable castCountdown = new BukkitRunnable() {
            @Override
            public void run() {
                if(i[0] == 0){
                    player.sendTitle("", "Success", 0, 2*20, 0);
                    onCastSuccess(e);
                    this.cancel();
                    return;
                }

                if(!player.getEquipment().getItemInMainHand().equals(getItem())){
                    player.sendTitle("", "Casting Cancelled", 0, 2*20, 0);
                    this.cancel();
                    return;
                }

                player.sendTitle("", "Casting in "+i[0], 0, 1, 0);

                i[0] -= 0.05;
            }
        };

        castCountdown.runTaskTimer(MbarrrManyItems.getInstance(), 0, TICK_TIME);
    }

    protected void onCastSuccess(WeaponShootEvent e){

    }
}
