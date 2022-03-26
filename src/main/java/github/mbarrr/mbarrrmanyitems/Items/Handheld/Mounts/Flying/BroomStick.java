package github.mbarrr.mbarrrmanyitems.Items.Handheld.Mounts.Flying;

import com.shampaggon.crackshot.events.WeaponShootEvent;
import github.mbarrr.mbarrrmanyitems.Items.Handheld.CrackShotItem;
import net.minecraft.server.v1_16_R3.EntityPlayer;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.util.Vector;

public class BroomStick extends CrackShotItem {

    Villager target;
    Phantom carrier;
    Player owner;

    public BroomStick() {
        super("Broomstick", 0);
    }

    @Override
    protected void onClick(WeaponShootEvent e) {
        owner = e.getPlayer();

        Location spawnLoc = e.getPlayer().getLocation();
        Vector dir = spawnLoc.getDirection();



        spawnLoc.add(dir.multiply(25));

        Villager armorStand = (Villager) spawnLoc.getWorld().spawnEntity(spawnLoc, EntityType.VILLAGER);
        armorStand.setAI(false);
        armorStand.setInvulnerable(true);
        target = armorStand;


        Phantom shulkerBullet = (Phantom) e.getPlayer().getWorld().spawnEntity(e.getPlayer().getLocation(), EntityType.PHANTOM);
        shulkerBullet.setSize(10);
        shulkerBullet.setTarget(target);
        shulkerBullet.setInvulnerable(true);
        shulkerBullet.addPassenger(owner);
        carrier = shulkerBullet;


        EntityPlayer asd;
        

    }
}
