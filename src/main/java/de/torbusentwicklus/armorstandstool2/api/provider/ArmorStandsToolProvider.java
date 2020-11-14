package de.torbusentwicklus.armorstandstool2.api.provider;

import de.torbusentwicklus.armorstandstool2.api.mysql.MySQLConnection;
import de.torbusentwicklus.armorstandstool2.api.spigot.ItemBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import java.util.Arrays;
import java.util.HashMap;

@Getter
@AllArgsConstructor
public class ArmorStandsToolProvider {

    private MySQLConnection mySQLConnection;
    private HashMap<Player, ArmorStand> editors;

    public Inventory EditorGUI(ArmorStand armorStand) {
        Inventory inventory = Bukkit.createInventory(null, 27, "§7ArmorStandEditor");
        ItemStack glass = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 14).setDisplayName(" ").build();
        for (int i = 0; i < 27; i++) {
            inventory.setItem(i, glass);
        }
        inventory.setItem(3, new ItemBuilder(Material.GOLD_LEGGINGS).setDisplayName("§aEdit Items: §eLegs").build());
        inventory.setItem(5, new ItemBuilder(Material.IRON_AXE).setDisplayName("§aEdit Items: §eArms").build());
        inventory.setItem(10, new ItemBuilder(Material.FEATHER).setDisplayName("§aGravity").setLore(Arrays.asList("", "§7Value: §c" + armorStand.hasGravity())).build());
        inventory.setItem(11, new ItemBuilder(Material.STONE_PLATE).setDisplayName("§aBasePlate").setLore(Arrays.asList("", "§7Value: §c" + armorStand.hasBasePlate())).build());
        inventory.setItem(12, new ItemBuilder(Material.STICK).setDisplayName("§aArms").setLore(Arrays.asList("", "§7Value: §c" + armorStand.hasArms())).build());
        inventory.setItem(13, new ItemBuilder(Material.BOOK).setDisplayName("§aDisplayName").setLore(Arrays.asList("", "§7Änder den DisplayNamen mit", "§a/setname [<Name>]")).build());
        inventory.setItem(14, new ItemBuilder(Material.POTION, 1, 8270).setDisplayName("§aVisibility").setLore(Arrays.asList("", "§7Value: §c" + armorStand.isVisible())).build());
        inventory.setItem(15, new ItemBuilder(Material.EYE_OF_ENDER).setDisplayName("§aSmall").setLore(Arrays.asList("", "§7Value: §c" + armorStand.isSmall())).build());
        inventory.setItem(16, new ItemBuilder(Material.POTION).setDisplayName("§aDisplayName Visible").setLore(Arrays.asList("", "§7Value: §c" + armorStand.isCustomNameVisible())).build());
        inventory.setItem(21, new ItemBuilder(Material.STONE).setDisplayName("§aEdit Items: §eLocation").build());
        inventory.setItem(23, new ItemBuilder(Material.CHAINMAIL_CHESTPLATE).setDisplayName("§aEdit Items: §eBody/Head").build());
        inventory.setItem(26, new ItemBuilder(Material.BARRIER).setDisplayName("§cZerstören").build());
        return inventory;
    }
}
