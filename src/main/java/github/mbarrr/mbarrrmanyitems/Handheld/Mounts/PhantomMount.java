package github.mbarrr.mbarrrmanyitems.Handheld.Mounts;

import net.minecraft.server.v1_16_R3.*;
import org.bukkit.event.entity.EntityTargetEvent;


public class PhantomMount extends EntityPhantom {

    EntityPlayer owner;
    float speed;

    public PhantomMount(EntityTypes<? extends EntityPhantom> entitytypes, World world, EntityPlayer player, float speed, int size) {
        super(entitytypes, world);

        this.owner = player;
        this.setSize(size);
        this.updateSize();
        this.speed = speed;

        player.startRiding(this);
    }

    @Override
    public void g(Vec3D vec3d) {
        if (this.isAlive()) {
            if (this.isVehicle()) {
                //Shulker has passenger
                EntityLiving entityliving = (EntityLiving) this.getRidingPassenger();
                this.yaw = entityliving.yaw;
                this.lastYaw = this.yaw;
                this.pitch = entityliving.pitch * 0.5F * -1;
                this.setYawPitch(this.yaw, this.pitch);
                this.aA = this.yaw;
                this.aC = this.aA;
                float f = entityliving.aR;
                float f1 = entityliving.aT;

                boolean t = true;

                //Slow down shulker if player not moving
                if (f1 <= 0.0F) {
                    f1 *= 0.1F;
                    t = false;
                }

                //God knows
                this.q((float) this.b(GenericAttributes.MOVEMENT_SPEED));
                super.g(new Vec3D((double) f, 0, (double) f1));
                this.a(this, false);

                //Move shulker in player direction if player is moving
                if(t) move(EnumMoveType.SELF, new Vec3D(entityliving.getLookDirection().x*speed/2.5, entityliving.getLookDirection().y*speed/2.5, entityliving.getLookDirection().z*speed/2.5));
            } else {
                //Shulker  has no passenger
                this.aE = 0.02F;
                this.setGoalTarget(owner, EntityTargetEvent.TargetReason.CUSTOM, true);
                super.g(vec3d);
            }
        }
    }



    @Override
    public Entity getRidingPassenger() {
        return this.getPassengers().isEmpty() ? null : (Entity)this.getPassengers().get(0);
    }
}
