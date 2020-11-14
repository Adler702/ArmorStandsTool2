package de.torbusentwicklus.armorstandstool2.api.spigot;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class SkullBuilder extends ItemBuilder {
    private SkullMeta sm;

    public SkullBuilder(ItemStack itemStack) {
        super(itemStack);
    }

    public SkullBuilder(int amount) {
        super(Material.SKULL_ITEM, amount, 3);
    }

    public SkullBuilder setOwner(String owner) {
        this.sm = ((SkullMeta) this.is.getItemMeta());
        this.sm.setOwner(owner);
        this.is.setItemMeta(this.sm);
        return this;
    }
}
