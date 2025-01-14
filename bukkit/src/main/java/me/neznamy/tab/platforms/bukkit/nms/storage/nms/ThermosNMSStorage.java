package me.neznamy.tab.platforms.bukkit.nms.storage.nms;

import me.neznamy.tab.platforms.bukkit.nms.datawatcher.DataWatcher;
import me.neznamy.tab.platforms.bukkit.scoreboard.PacketScoreboard;
import org.jetbrains.annotations.NotNull;

/**
 * NMS loader for Thermos 1.7.10.
 */
public class ThermosNMSStorage extends BukkitLegacyNMSStorage {

    @Override
    public Class<?> getLegacyClass(@NotNull String name) throws ClassNotFoundException {
        try {
            return getClass().getClassLoader().loadClass("net.minecraft.server." + serverPackage + "." + name);
        } catch (NullPointerException e) {
            // nested class not found
            throw new ClassNotFoundException(name);
        }
    }

    @Override
    public void loadNamedFieldsAndMethods() throws ReflectiveOperationException {
        PacketScoreboard.ScoreboardScore_setScore = PacketScoreboard.ScoreboardScoreClass.getMethod("func_96647_c", int.class);
        PacketScoreboard.ScoreboardTeam_setAllowFriendlyFire = PacketScoreboard.ScoreboardTeam.getMethod("func_96660_a", boolean.class);
        PacketScoreboard.ScoreboardTeam_setCanSeeFriendlyInvisibles = PacketScoreboard.ScoreboardTeam.getMethod("func_98300_b", boolean.class);
        DataWatcher.REGISTER = DataWatcher.CLASS.getMethod("func_75682_a", int.class, Object.class);
        PacketScoreboard.ScoreboardTeam_setPrefix = PacketScoreboard.ScoreboardTeam.getMethod("func_96666_b", String.class);
        PacketScoreboard.ScoreboardTeam_setSuffix = PacketScoreboard.ScoreboardTeam.getMethod("func_96662_c", String.class);
    }
}
