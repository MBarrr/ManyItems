package github.mbarrr.mbarrrmanyitems.Items.Handheld.Mounts.Ground;

import github.mbarrr.mbarrrmanyitems.Items.Handheld.Mounts.Mount;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.event.entity.EntityTargetEvent;

public class GroundMount extends Mount {

    public GroundMount(EntityTypes<? extends EntityInsentient> entitytypes, World world, EntityPlayer player, float speed) {
        super(entitytypes, world, player, speed);
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
                if(t) move(EnumMoveType.SELF, new Vec3D(entityliving.getLookDirection().x*getSpeed()/2.5, entityliving.getLookDirection().y*getSpeed()/2.5, entityliving.getLookDirection().z*getSpeed()/2.5));
            } else {
                //Shulker  has no passenger
                this.aE = 0.02F;
                this.setGoalTarget(getOwner(), EntityTargetEvent.TargetReason.CUSTOM, true);
                super.g(vec3d);
            }
        }
    }
}
