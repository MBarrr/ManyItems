package github.mbarrr.mbarrrmanyitems.Items.Handheld.Mounts;

import net.minecraft.server.v1_16_R3.*;


public class Mount extends EntityInsentient {

    private EntityPlayer owner;
    private float speed;

    public Mount(EntityTypes<? extends EntityInsentient> entitytypes, World world, EntityPlayer player, float speed) {
        super(entitytypes, world);
        this.owner = player;
        this.speed = speed;
    }

    protected EntityPlayer getOwner(){
        return owner;
    }

    protected float getSpeed(){
        return speed;
    }

    @Override
    public Entity getRidingPassenger() {
        return this.getPassengers().isEmpty() ? null : (Entity)this.getPassengers().get(0);
    }
}
