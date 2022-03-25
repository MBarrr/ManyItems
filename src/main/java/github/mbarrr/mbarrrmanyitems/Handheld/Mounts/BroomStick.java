package github.mbarrr.mbarrrmanyitems.Handheld.Mounts;

import com.shampaggon.crackshot.events.WeaponShootEvent;
import github.mbarrr.mbarrrmanyitems.Handheld.HandheldItem;
import net.minecraft.server.v1_16_R3.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class BroomStick extends HandheldItem {

    Villager target;
    Phantom carrier;
    Player owner;

    public BroomStick() {
        super("Broomstick");
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
