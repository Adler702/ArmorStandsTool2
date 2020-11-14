package de.torbusentwicklus.armorstandstool2.listener;

import de.torbusentwicklus.armorstandstool2.api.provider.ArmorStandsToolProvider;
import lombok.AllArgsConstructor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

@AllArgsConstructor
public class PlayerInteractAtEntityEventListener implements Listener {

    private ArmorStandsToolProvider armorStandsToolProvider;

    @EventHandler
    public void onPlayerInteract(PlayerInteractAtEntityEvent event) {
        if (!(event.getRightClicked() instanceof ArmorStand)) return;
        if (!armorStandsToolProvider.getEditors().containsKey(event.getPlayer())) return;
        armorStandsToolProvider.getEditors().remove(event.getPlayer());
        armorStandsToolProvider.getEditors().put(event.getPlayer(), (ArmorStand) event.getRightClicked());
        event.getPlayer().openInventory(armorStandsToolProvider.EditorGUI(armorStandsToolProvider.getEditors().get(event.getPlayer())));
    }
}
