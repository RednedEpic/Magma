package org.bukkit.craftbukkit.v1_12_R1.entity;

import net.minecraft.entity.item.EntityFallingBlock;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.util.CraftMagicNumbers;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;

public class CraftFallingBlock extends CraftEntity implements FallingBlock {

    public CraftFallingBlock(CraftServer server, EntityFallingBlock entity) {
        super(server, entity);
    }

    @Override
    public EntityFallingBlock getHandle() {
        return (EntityFallingBlock) entity;
    }

    @Override
    public String toString() {
        return "CraftFallingBlock";
    }

    public EntityType getType() {
        return EntityType.FALLING_BLOCK;
    }

    public Material getMaterial() {
        return Material.getMaterial(getBlockId());
    }

    public int getBlockId() {
        return CraftMagicNumbers.getId(getHandle().getBlock().getBlock());
    }

    public byte getBlockData() {
        return (byte) getHandle().getBlock().getBlock().getMetaFromState(getHandle().getBlock());
    }

    public boolean getDropItem() {
        return getHandle().shouldDropItem;
    }

    public void setDropItem(boolean drop) {
        getHandle().shouldDropItem = drop;
    }

    @Override
    public boolean canHurtEntities() {
        return getHandle().hurtEntities;
    }

    @Override
    public void setHurtEntities(boolean hurtEntities) {
        getHandle().hurtEntities = hurtEntities;
    }

    @Override
    public void setTicksLived(int value) {
        super.setTicksLived(value);

        // Second field for EntityFallingBlock
        getHandle().ticksExisted = value;
    }
}
