package org.magmafoundation.magma.mixin.core.mojang.authlib;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import com.google.common.base.Charsets;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerProfileCache;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.magmafoundation.magma.bridge.minecraft.server.management.BridgePlayerProfileCache;
import org.spongepowered.asm.mixin.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * MixinGameProfile
 *
 * @author Redned
 * @since 30/01/2020 - 07:38 pm
 */
@Mixin(GameProfile.class)
@Implements(@Interface(iface = PlayerProfile.class, prefix = "profile$"))
public abstract class MixinGameProfile implements PlayerProfile {

    @Shadow @Mutable @Final private String name;
    @Shadow @Mutable @Final private UUID id;
    @Shadow @Mutable @Final private PropertyMap properties;
    @Shadow private boolean legacy;

    @Shadow public abstract String shadow$getName();
    @Shadow public abstract UUID shadow$getId();
    @Shadow public abstract boolean shadow$isComplete();

    @Nullable
    @Intrinsic
    public String profile$getName() {
        return shadow$getName();
    }

    @NotNull
    @Override
    public String setName(@Nullable String name) {
        String prevName = this.name;
        this.name = name;
        return prevName;
    }

    @Nullable
    @Intrinsic
    public UUID profile$getId() {
        return shadow$getId();
    }

    @Nullable
    @Override
    public UUID setId(@Nullable UUID id) {
        UUID prevId = this.id;
        this.id = id;
        return prevId;
    }

    @NotNull
    @Override
    public Set<ProfileProperty> getProperties() {
        Set<ProfileProperty> profileProperties = new HashSet<>();
        for (Property property : properties.values()) {
            profileProperties.add(new ProfileProperty(property.getName(), property.getValue(), property.getSignature()));
        }
        return profileProperties;
    }

    @Override
    public boolean hasProperty(@Nullable String property) {
        return properties.containsKey(property);
    }

    @Override
    public void setProperty(@NotNull ProfileProperty property) {
        String name = property.getName();
        properties.removeAll(name);
        properties.put(name, new Property(name, property.getValue(), property.getSignature()));
    }

    @Override
    public void setProperties(@NotNull Collection<ProfileProperty> properties) {
        properties.forEach(this::setProperty);
    }

    @Override
    public boolean removeProperty(@Nullable String property) {
        return !properties.removeAll(name).isEmpty();
    }

    @Override
    public void clearProperties() {
        properties.clear();
    }

    @Intrinsic
    public boolean property$isComplete() {
        return shadow$isComplete();
    }

    @Override
    public boolean completeFromCache() {
        return completeFromCache(false);
    }

    public boolean completeFromCache(boolean lookupName) {
        if (this.isComplete()) {
            return true;
        }
        String name = this.getName();
        PlayerProfileCache profileCache = ((MinecraftServer) Bukkit.getServer()).getPlayerProfileCache();
        if (this.getId() == null) {
            final GameProfile profile;
            boolean isOnlineMode = Bukkit.getOnlineMode(); // TODO bungee online mode
            if (isOnlineMode) {
                profile = lookupName ? profileCache.getGameProfileForUsername(name) : ((BridgePlayerProfileCache) profileCache).bridge$getProfileIfCached(name);
            } else {
                // Make an OfflinePlayer using an offline mode UUID since the name has no profile
                profile = new GameProfile(UUID.nameUUIDFromBytes(("OfflinePlayer:" + name).getBytes(Charsets.UTF_8)), name);
            }
            if (profile != null) {
                this.id = profile.getId();
                this.name = profile.getName();
                this.properties = profile.getProperties();
                this.legacy = profile.isLegacy();
            }
        }
        if (this.getName() == null) {
            // If we need textures, skip this check, as we will get it below anyways.
            GameProfile profile = profileCache.getProfileByUUID(this.getId());
            if (profile != null) {
                this.id = profile.getId();
                this.name = profile.getName();
                this.properties = profile.getProperties();
                this.legacy = profile.isLegacy();
            }
        }
        return this.isComplete();
    }

    @Override
    public boolean complete(boolean textures) {
        boolean isOnlineMode = Bukkit.getOnlineMode(); // TODO bungee online mode
        boolean isCompleteFromCache = this.completeFromCache(true);
        if (isOnlineMode && (!isCompleteFromCache || textures && !hasTextures())) {
            GameProfile profile = ((MinecraftServer) Bukkit.getServer()).getMinecraftSessionService().fillProfileProperties((GameProfile) (Object) this, true);
            if (profile == null)
                return false;

            this.id = profile.getId();
            this.name = profile.getName();
            this.properties = profile.getProperties();
            this.legacy = profile.isLegacy();
        }
        return this.isComplete() && (!isOnlineMode || !textures || hasTextures());
    }
}
