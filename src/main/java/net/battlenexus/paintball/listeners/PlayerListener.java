package net.battlenexus.paintball.listeners;

import net.battlenexus.paintball.entities.PBPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class PlayerListener implements Listener {

    protected HashMap<String, String> deathMessages = new HashMap<>();

    public PlayerListener(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        PBPlayer.newPlayer(event.getPlayer());
    }

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent event) {
        if(!(event.getDamager() instanceof Projectile) || !(event.getEntity() instanceof Player) || !(((Projectile) event.getDamager()).getShooter() instanceof Player)) {
            return;
        }

        PBPlayer shooter = PBPlayer.getPlayer((Player) ((Projectile) event.getDamager()).getShooter());
        PBPlayer target  = PBPlayer.getPlayer((Player) event.getEntity());

        deathMessages.put(target.getBukkitPlayer().getName(), shooter.getBukkitPlayer().getDisplayName() + " has killed " + target.getBukkitPlayer().getDisplayName() + ". :(");
        target.kill(shooter);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        String playerName = event.getEntity().getName();

        if (deathMessages.containsKey(playerName)) {
            event.setDeathMessage(deathMessages.get(playerName));
            deathMessages.remove(playerName);
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        PBPlayer player;
        if ((player = PBPlayer.getPlayer(event.getPlayer())) != null) {
            if (player.isFrozen())
                player.handleFrozen();
        }
    }

    @EventHandler
    public void onPlayerProjectileHit(ProjectileHitEvent event) {
        if(!(event.getEntity().getShooter() instanceof Player)) {
            return;
        }
        //TODO
    }

}