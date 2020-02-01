package org.magmafoundation.magma.mixin.bridge.minecraft.server.management;

import com.mojang.authlib.GameProfile;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * MixinPlayerProfileCache
 *
 * @author Redned
 * @since 30/01/2020 - 06:17 pm
 */
@Mixin(targets = "net.minecraft.server.management.PlayerProfileCache$ProfileEntry")
public interface AccessorPlayerProfileCache$ProfileEntry {

    @Accessor("gameProfile") GameProfile accessor$getGameProfile();
}
