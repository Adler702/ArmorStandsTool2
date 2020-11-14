package de.torbusentwicklus.armorstandstool2.listener;

import de.torbusentwicklus.armorstandstool2.api.provider.ArmorStandsToolProvider;
import lombok.AllArgsConstructor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
public class InventoryClickEventListener implements Listener {

    private ArmorStandsToolProvider armorStandsToolProvider;

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) throws NullPointerException {
        if (event.getInventory().getTitle().equals("§7ArmorStandEditor")) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            ArmorStand armorStand = armorStandsToolProvider.getEditors().get(player);
            ItemStack itemStack = event.getCurrentItem();
            if (itemStack.getItemMeta().getDisplayName().equals("§aEdit Items: §eLegs")) {
                /**
                 * Add Items
                 */
            } else if (itemStack.getItemMeta().getDisplayName().equals("§aEdit Items: §eArms")) {
                /**
                 * Add Items
                 */
            } else if (itemStack.getItemMeta().getDisplayName().equals("§aGravity")) {
                if (armorStand.hasGravity()) {
                    armorStand.setGravity(false);
                } else {
                    armorStand.setGravity(true);
                }
            } else if (itemStack.getItemMeta().getDisplayName().equals("§aBasePlate")) {
                if (armorStand.hasBasePlate()) {
                    armorStand.setBasePlate(false);
                } else {
                    armorStand.setBasePlate(true);
                }
            } else if (itemStack.getItemMeta().getDisplayName().equals("§aArms")) {
                if (armorStand.hasArms()) {
                    armorStand.setArms(false);
                } else {
                    armorStand.setArms(true);
                }
            } else if (itemStack.getItemMeta().getDisplayName().equals("§aVisibility")) {
                if (armorStand.isVisible()) {
                    armorStand.setVisible(false);
                } else {
                    armorStand.setVisible(true);
                }
            } else if (itemStack.getItemMeta().getDisplayName().equals("§aSmall")) {
                if (armorStand.isSmall()) {
                    armorStand.setSmall(false);
                } else {
                    armorStand.setSmall(true);
                }
            } else if (itemStack.getItemMeta().getDisplayName().equals("§aDisplayName Visible")) {
                if (armorStand.isCustomNameVisible()) {
                    armorStand.setCustomNameVisible(false);
                } else {
                    armorStand.setCustomNameVisible(true);
                }
            } else if (itemStack.getItemMeta().getDisplayName().equals("§aEdit Items: §eLocation")) {
                /**
                 * Add Items
                 */
            } else if (itemStack.getItemMeta().getDisplayName().equals("§aEdit Items: §eBody/Head")) {
                /**
                 * Add Items
                 */
            } else if (itemStack.getItemMeta().getDisplayName().equals("§cZerstören")) {
                armorStand.remove();
                player.closeInventory();
            }
        }
    }
}
