package org.magmafoundation.magma.bridge.minecraft.server.management;

import com.mojang.authlib.GameProfile;

import org.jetbrains.annotations.Nullable;

/**
 * BridgePlayerProfileCache
 *
 * @author Redned
 * @since 30/01/2020 - 06:18 pm
 */
public interface BridgePlayerProfileCache {

    @Nullable
    GameProfile bridge$getProfileIfCached(String name);
}