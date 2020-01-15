package org.magmafoundation.magma.api.core.entity;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SSpawnPositionPacket;
import net.minecraft.server.management.WhitelistEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import org.bukkit.*;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.block.data.BlockData;
import org.bukkit.configuration.serialization.DelegateDeserialization;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.map.MapView;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Scoreboard;
import org.magmafoundation.magma.api.bridge.entity.player.IBridgeServerPlayerEntity;
import org.magmafoundation.magma.api.core.MagmaOfflinePlayer;
import org.magmafoundation.magma.api.core.MagmaServer;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.Set;

/**
 * MagmaPlayer
 *
 * @author Hexeption admin@hexeption.co.uk
 * @since 24/11/2019 - 08:11 pm
 */
@DelegateDeserialization(MagmaOfflinePlayer.class)
public class MagmaPlayer extends MagmaHumanEntity implements Player {

    private ITextComponent tabListHeader;
    private ITextComponent tabListFooter;

    private Location compassTarget;

    public MagmaPlayer(MagmaServer server, ServerPlayerEntity entity) {
        super(server, entity);
    }

    @Override
    public String getDisplayName() {
        return ((IBridgeServerPlayerEntity) getHandle()).getDisplayName().getFormattedText();
    }

    @Override
    public void setDisplayName(String name) {
        ((IBridgeServerPlayerEntity) getHandle()).setDisplayName(new StringTextComponent((name)));
    }

    @Override
    public String getPlayerListName() {
        return ((IBridgeServerPlayerEntity) getHandle()).getTabListDisplayName().getFormattedText();
    }

    @Override
    public void setPlayerListName(String name) {
        ((IBridgeServerPlayerEntity) getHandle()).setTabListDisplayName(new StringTextComponent(name));
    }

    @Override
    public String getPlayerListHeader() {
        return tabListHeader == null ? null : tabListHeader.getFormattedText();
    }

    @Override
    public String getPlayerListFooter() {
        return tabListFooter == null ? null : tabListFooter.getFormattedText();
    }

    @Override
    public void setPlayerListHeader(String header) {
        this.tabListHeader = new StringTextComponent(header);
    }

    @Override
    public void setPlayerListFooter(String footer) {
        this.tabListFooter = new StringTextComponent(footer);
    }

    @Override
    public void setPlayerListHeaderFooter(String header, String footer) {
        this.tabListHeader = new StringTextComponent(header);
        this.tabListFooter = new StringTextComponent(footer);
    }

    @Override
    public void setCompassTarget(Location compassTarget) {
        this.compassTarget = compassTarget;

        getHandle().connection.sendPacket(new SSpawnPositionPacket(new BlockPos(compassTarget.getBlockX(), compassTarget.getBlockY(), compassTarget.getBlockZ())));
    }

    @Override
    public Location getCompassTarget() {
        return compassTarget == null ? getWorld().getSpawnLocation() : compassTarget;
    }

    @Override
    public InetSocketAddress getAddress() {
        if (getHandle().connection == null)
            return null;

        if (getHandle().connection.netManager.getRemoteAddress() instanceof InetSocketAddress)
            return (InetSocketAddress) getHandle().connection.netManager.getRemoteAddress();
        return null;
    }

    @Override
    public boolean isConversing() {
        return false;
    }

    @Override
    public void acceptConversationInput(String input) {

    }

    @Override
    public boolean beginConversation(Conversation conversation) {
        return false;
    }

    @Override
    public void abandonConversation(Conversation conversation) {

    }

    @Override
    public void abandonConversation(Conversation conversation, ConversationAbandonedEvent details) {

    }

    @Override
    public void sendRawMessage(String message) {

    }

    @Override
    public void kickPlayer(String message) {

    }

    @Override
    public void chat(String msg) {

    }

    @Override
    public boolean performCommand(String command) {
        return false;
    }

    @Override
    public boolean isSneaking() {
        return getHandle().isSneaking();
    }

    @Override
    public void setSneaking(boolean sneak) {
        getHandle().setSneaking(sneak);
    }

    @Override
    public boolean isSprinting() {
        return getHandle().isSprinting();
    }

    @Override
    public void setSprinting(boolean sprinting) {
        getHandle().setSprinting(sprinting);
    }

    @Override
    public void saveData() {

    }

    @Override
    public void loadData() {

    }

    @Override
    public void setSleepingIgnored(boolean isSleeping) {

    }

    @Override
    public boolean isSleepingIgnored() {
        return false;
    }

    @Override
    public void playNote(Location loc, byte instrument, byte note) {

    }

    @Override
    public void playNote(Location loc, Instrument instrument, Note note) {

    }

    @Override
    public void playSound(Location location, Sound sound, float volume, float pitch) {

    }

    @Override
    public void playSound(Location location, String sound, float volume, float pitch) {

    }

    @Override
    public void playSound(Location location, Sound sound, SoundCategory category, float volume,
        float pitch) {

    }

    @Override
    public void playSound(Location location, String sound, SoundCategory category, float volume,
        float pitch) {

    }

    @Override
    public void stopSound(Sound sound) {

    }

    @Override
    public void stopSound(String sound) {

    }

    @Override
    public void stopSound(Sound sound, SoundCategory category) {

    }

    @Override
    public void stopSound(String sound, SoundCategory category) {

    }

    @Override
    public void playEffect(Location loc, Effect effect, int data) {

    }

    @Override
    public <T> void playEffect(Location loc, Effect effect, T data) {

    }

    @Override
    public void sendBlockChange(Location loc, Material material, byte data) {

    }

    @Override
    public void sendBlockChange(Location loc, BlockData block) {

    }

    @Override
    public boolean sendChunkChange(Location loc, int sx, int sy, int sz, byte[] data) {
        return false;
    }

    @Override
    public void sendSignChange(Location loc, String[] lines) throws IllegalArgumentException {

    }

    @Override
    public void sendSignChange(Location loc, String[] lines, DyeColor dyeColor)
        throws IllegalArgumentException {

    }

    @Override
    public void sendMap(MapView map) {

    }

    @Override
    public void updateInventory() {

    }

    @Override
    public void awardAchievement(Achievement achievement) {

    }

    @Override
    public void removeAchievement(Achievement achievement) {

    }

    @Override
    public boolean hasAchievement(Achievement achievement) {
        return false;
    }

    @Override
    public void incrementStatistic(Statistic statistic) throws IllegalArgumentException {

    }

    @Override
    public void decrementStatistic(Statistic statistic) throws IllegalArgumentException {

    }

    @Override
    public void incrementStatistic(Statistic statistic, int amount)
        throws IllegalArgumentException {

    }

    @Override
    public void decrementStatistic(Statistic statistic, int amount)
        throws IllegalArgumentException {

    }

    @Override
    public void setStatistic(Statistic statistic, int newValue) throws IllegalArgumentException {

    }

    @Override
    public int getStatistic(Statistic statistic) throws IllegalArgumentException {
        return 0;
    }

    @Override
    public void incrementStatistic(Statistic statistic, Material material)
        throws IllegalArgumentException {

    }

    @Override
    public void decrementStatistic(Statistic statistic, Material material)
        throws IllegalArgumentException {

    }

    @Override
    public int getStatistic(Statistic statistic, Material material)
        throws IllegalArgumentException {
        return 0;
    }

    @Override
    public void incrementStatistic(Statistic statistic, Material material, int amount)
        throws IllegalArgumentException {

    }

    @Override
    public void decrementStatistic(Statistic statistic, Material material, int amount)
        throws IllegalArgumentException {

    }

    @Override
    public void setStatistic(Statistic statistic, Material material, int newValue)
        throws IllegalArgumentException {

    }

    @Override
    public void incrementStatistic(Statistic statistic, EntityType entityType)
        throws IllegalArgumentException {

    }

    @Override
    public void decrementStatistic(Statistic statistic, EntityType entityType)
        throws IllegalArgumentException {

    }

    @Override
    public int getStatistic(Statistic statistic, EntityType entityType)
        throws IllegalArgumentException {
        return 0;
    }

    @Override
    public void incrementStatistic(Statistic statistic, EntityType entityType, int amount)
        throws IllegalArgumentException {

    }

    @Override
    public void decrementStatistic(Statistic statistic, EntityType entityType, int amount) {

    }

    @Override
    public void setStatistic(Statistic statistic, EntityType entityType, int newValue) {

    }

    @Override
    public void setPlayerTime(long time, boolean relative) {

    }

    @Override
    public long getPlayerTime() {
        return 0;
    }

    @Override
    public long getPlayerTimeOffset() {
        return 0;
    }

    @Override
    public boolean isPlayerTimeRelative() {
        return false;
    }

    @Override
    public void resetPlayerTime() {

    }

    @Override
    public void setPlayerWeather(WeatherType type) {

    }

    @Override
    public WeatherType getPlayerWeather() {
        return null;
    }

    @Override
    public void resetPlayerWeather() {

    }

    @Override
    public void giveExp(int amount) {

    }

    @Override
    public void giveExpLevels(int amount) {

    }

    @Override
    public float getExp() {
        return 0;
    }

    @Override
    public void setExp(float exp) {

    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public void setLevel(int level) {

    }

    @Override
    public int getTotalExperience() {
        return 0;
    }

    @Override
    public void setTotalExperience(int exp) {

    }

    @Override
    public float getExhaustion() {
        return 0;
    }

    @Override
    public void setExhaustion(float value) {

    }

    @Override
    public float getSaturation() {
        return 0;
    }

    @Override
    public void setSaturation(float value) {

    }

    @Override
    public int getFoodLevel() {
        return 0;
    }

    @Override
    public void setFoodLevel(int value) {

    }

    @Override
    public boolean getAllowFlight() {
        return false;
    }

    @Override
    public void setAllowFlight(boolean flight) {

    }

    @Override
    public void hidePlayer(Player player) {

    }

    @Override
    public void hidePlayer(Plugin plugin, Player player) {

    }

    @Override
    public void showPlayer(Player player) {

    }

    @Override
    public void showPlayer(Plugin plugin, Player player) {

    }

    @Override
    public boolean canSee(Player player) {
        return false;
    }

    @Override
    public boolean isFlying() {
        return false;
    }

    @Override
    public void setFlying(boolean value) {

    }

    @Override
    public void setFlySpeed(float value) throws IllegalArgumentException {

    }

    @Override
    public void setWalkSpeed(float value) throws IllegalArgumentException {

    }

    @Override
    public float getFlySpeed() {
        return 0;
    }

    @Override
    public float getWalkSpeed() {
        return 0;
    }

    @Override
    public void setTexturePack(String url) {

    }

    @Override
    public void setResourcePack(String url) {

    }

    @Override
    public void setResourcePack(String url, byte[] hash) {

    }

    @Override
    public Scoreboard getScoreboard() {
        return null;
    }

    @Override
    public void setScoreboard(Scoreboard scoreboard)
        throws IllegalArgumentException, IllegalStateException {

    }

    @Override
    public boolean isHealthScaled() {
        return false;
    }

    @Override
    public void setHealthScaled(boolean scale) {

    }

    @Override
    public void setHealthScale(double scale) throws IllegalArgumentException {

    }

    @Override
    public double getHealthScale() {
        return 0;
    }

    @Override
    public Entity getSpectatorTarget() {
        return null;
    }

    @Override
    public void setSpectatorTarget(Entity entity) {

    }

    @Override
    public void sendTitle(String title, String subtitle) {

    }

    @Override
    public void sendTitle(String title, String subtitle, int fadeIn, int stay, int fadeOut) {

    }

    @Override
    public void resetTitle() {

    }

    @Override
    public void spawnParticle(Particle particle, Location location, int count) {

    }

    @Override
    public void spawnParticle(Particle particle, double x, double y, double z, int count) {

    }

    @Override
    public <T> void spawnParticle(Particle particle, Location location, int count, T data) {

    }

    @Override
    public <T> void spawnParticle(Particle particle, double x, double y, double z, int count,
        T data) {

    }

    @Override
    public void spawnParticle(Particle particle, Location location, int count, double offsetX,
        double offsetY, double offsetZ) {

    }

    @Override
    public void spawnParticle(Particle particle, double x, double y, double z, int count,
        double offsetX, double offsetY, double offsetZ) {

    }

    @Override
    public <T> void spawnParticle(Particle particle, Location location, int count, double offsetX,
        double offsetY, double offsetZ, T data) {

    }

    @Override
    public <T> void spawnParticle(Particle particle, double x, double y, double z, int count,
        double offsetX, double offsetY, double offsetZ, T data) {

    }

    @Override
    public void spawnParticle(Particle particle, Location location, int count, double offsetX,
        double offsetY, double offsetZ, double extra) {

    }

    @Override
    public void spawnParticle(Particle particle, double x, double y, double z, int count,
        double offsetX, double offsetY, double offsetZ, double extra) {

    }

    @Override
    public <T> void spawnParticle(Particle particle, Location location, int count, double offsetX,
        double offsetY, double offsetZ, double extra, T data) {

    }

    @Override
    public <T> void spawnParticle(Particle particle, double x, double y, double z, int count,
        double offsetX, double offsetY, double offsetZ, double extra, T data) {

    }

    @Override
    public AdvancementProgress getAdvancementProgress(Advancement advancement) {
        return null;
    }

    @Override
    public int getClientViewDistance() {
        return 0;
    }

    @Override
    public String getLocale() {
        return null;
    }

    @Override
    public void updateCommands() {

    }

    @Override
    public void openBook(ItemStack book) {

    }

    @Override
    public boolean isOnline() {
        return false;
    }

    @Override
    public boolean isBanned() {
        return this.server.getBanList(BanList.Type.NAME).isBanned(getName());
    }

    @Override
    public boolean isWhitelisted() {
        return this.server.getDedicatedPlayerList().getWhitelistedPlayers().isWhitelisted(getHandle().getGameProfile());
    }

    @Override
    public void setWhitelisted(boolean value) {
        this.server.getDedicatedPlayerList().getWhitelistedPlayers().addEntry(new WhitelistEntry(getHandle().getGameProfile()));
    }

    @Override
    public Player getPlayer() {
        return this;
    }

    @Override
    public long getFirstPlayed() {
        return 0;
    }

    @Override
    public long getLastPlayed() {
        return 0;
    }

    @Override
    public boolean hasPlayedBefore() {
        return false;
    }

    @Override
    public Map<String, Object> serialize() {
        return null;
    }

    @Override
    public void sendPluginMessage(Plugin source, String channel, byte[] message) {

    }

    @Override
    public Set<String> getListeningPluginChannels() {
        return null;
    }

    @Override
    public ServerPlayerEntity getHandle() {
        return (ServerPlayerEntity) entity;
    }
}
