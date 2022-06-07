package github.mbarrr.mbarrrmanyitems.Items.Handheld.Spells;

import github.mbarrr.mbarrrmanyitems.MbarrrManyItems;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class Portal {

    BukkitRunnable mainRunnable;
    private EnderPearl firstPortal;
    private EnderPearl secondPortal;
    private double portalUpTime;

    /**
     *
     * @param firstLocation Location of the first portal
     * @param secondLocation Location of the second portal
     * @param portalUpTime Time in seconds before portal is despawned
     */
    public Portal(Location firstLocation, Location secondLocation, double portalUpTime){
        firstPortal = (EnderPearl) firstLocation.getWorld().spawnEntity(firstLocation, EntityType.ENDER_PEARL);
        secondPortal = (EnderPearl) secondLocation.getWorld().spawnEntity(secondLocation, EntityType.ENDER_PEARL);

        firstPortal.setGravity(false);
        firstPortal.setInvulnerable(true);

        secondPortal.setGravity(false);
        secondPortal.setInvulnerable(true);

        this.portalUpTime = portalUpTime*20;

        mainRunnable = new BukkitRunnable() {
            @Override
            public void run() {
                mainLoop();
            }
        };

        mainRunnable.runTaskTimer(MbarrrManyItems.getInstance(), 0, 1L);
    }

    private void mainLoop(){

        if(portalUpTime <= 0){
            firstPortal.remove();
            secondPortal.remove();

            mainRunnable.cancel();
            return;
        }

        //spawn particles
        firstPortal.getLocation().getWorld().spawnParticle(Particle.CLOUD,firstPortal.getLocation(), 10);
        secondPortal.getLocation().getWorld().spawnParticle(Particle.CLOUD,secondPortal.getLocation(), 10);


        //get nearby players and teleport them
        List<Entity> entities = firstPortal.getNearbyEntities(1, 2, 1);

        for(Entity entity:entities){
            entity.teleport(secondPortal.getLocation());
        }

        portalUpTime=-0.05;

    }



}
