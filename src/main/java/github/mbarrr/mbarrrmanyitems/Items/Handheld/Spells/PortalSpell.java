package github.mbarrr.mbarrrmanyitems.Items.Handheld.Spells;

import com.shampaggon.crackshot.events.WeaponShootEvent;
import github.mbarrr.mbarrrmanyitems.MbarrrManyItems;
import org.bukkit.entity.Player;

public class PortalSpell extends Spell{


    private static final int CAST_TIME = 5; //Cast time in seconds
    private static final int  PORTAL_UP_TIME = 10;


    /**
     * Generic constructor
     * Handheld items are all crackshot weapons
     *
     * @param customModelData       Custom model data, or 0 for none
     */
    public PortalSpell(MbarrrManyItems instance, int customModelData) {
        super(instance, "PortalSpell", customModelData, false, CAST_TIME);
    }

    @Override
    protected void onCastSuccess(WeaponShootEvent e) {
        super.onCastSuccess(e);
        Player player = e.getPlayer();

        Portal portal = new Portal(player.getLocation(), player.getTargetBlock(null, 50).getLocation(), PORTAL_UP_TIME);

    }
}
