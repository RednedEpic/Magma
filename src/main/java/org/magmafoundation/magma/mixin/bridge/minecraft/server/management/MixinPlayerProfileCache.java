package org.magmafoundation.magma.mixin.bridge.minecraft.server.management;

import com.mojang.authlib.GameProfile;

import net.minecraft.server.management.PlayerProfileCache;

import org.jetbrains.annotations.Nullable;
import org.magmafoundation.magma.bridge.minecraft.server.management.BridgePlayerProfileCache;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Locale;
import java.util.Map;

/**
 * MixinPlayerProfileCache
 *
 * @author Redned
 * @since 30/01/2020 - 06:17 pm
 */
@Mixin(PlayerProfileCache.class)
public class MixinPlayerProfileCache implements BridgePlayerProfileCache {

    @Shadow @Final private Map<String, AccessorPlayerProfileCache$ProfileEntry> usernameToProfileEntryMap;

    @Nullable
    @Override
    public GameProfile bridge$getProfileIfCached(String name) {
        AccessorPlayerProfileCache$ProfileEntry entry = usernameToProfileEntryMap.get(name.toLowerCase(Locale.ROOT));
        return entry == null ? null : entry.accessor$getGameProfile();
    }
}
