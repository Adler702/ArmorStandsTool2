package de.torbusentwicklus.armorstandstool2.api.spigot;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ItemBuilder
{
    protected ItemStack is;
    protected ItemMeta im;

    public ItemBuilder() {}

    @Deprecated
    public ItemBuilder(String from)
    {
        int id = 0;
        int subid = 0;
        int amount = 1;
        if (from.contains(":"))
        {
            String[] array = from.split(":");
            id = Integer.valueOf(array[0]).intValue();
            String a = array[1];
            if (array[1].startsWith("1")) {
                a = a.substring(0, 2);
            } else {
                a = a.substring(0, 1);
            }
            subid = Integer.valueOf(a).intValue();
        }
        if (from.contains(", "))
        {
            String[] array = from.split(", ");
            amount = Integer.valueOf(array[1]).intValue();
        }
        this.is = new ItemStack(id, amount, (short)subid);
    }

    public ItemBuilder(ItemStack itemStack)
    {
        this.is = new ItemStack(itemStack);
    }

    public ItemBuilder(Material material)
    {
        this.is = new ItemStack(material);
    }

    public ItemBuilder(Material material, int amount)
    {
        this.is = new ItemStack(material, amount);
    }

    public ItemBuilder(Material material, int amount, int subid)
    {
        this.is = new ItemStack(material, amount, (short)subid);
    }

    @Deprecated
    public ItemBuilder(int id)
    {
        this.is = new ItemStack(id);
    }
    @Deprecated
    public ItemBuilder(int id, int amount)
    {
        this.is = new ItemStack(id, amount);
    }
    @Deprecated
    public ItemBuilder(int id, int amount, int subid)
    {
        this.is = new ItemStack(id, amount, (short)subid);
    }

    public ItemBuilder setDurability(int durability)
    {
        this.is.setDurability((short)durability);
        return this;
    }

    public ItemBuilder setDisplayName(String name)
    {
        this.im = this.is.getItemMeta();
        this.im.setDisplayName(name);
        this.is.setItemMeta(this.im);
        return this;
    }

    public ItemBuilder addEnchant(Enchantment enchantment, int level)
    {
        this.im = this.is.getItemMeta();
        this.im.addEnchant(enchantment, level, true);
        this.is.setItemMeta(this.im);
        return this;
    }

    public ItemBuilder addEnchants(Map<Enchantment, Integer> enchantments)
    {
        this.im = this.is.getItemMeta();
        if (!enchantments.isEmpty()) {
            for (Enchantment ench : enchantments.keySet()) {
                this.im.addEnchant(ench, enchantments.get(ench), true);
            }
        }
        this.is.setItemMeta(this.im);
        return this;
    }

    public ItemBuilder addItemFlag(ItemFlag itemflag)
    {
        this.im = this.is.getItemMeta();
        this.im.addItemFlags(new ItemFlag[] { itemflag });
        this.is.setItemMeta(this.im);
        return this;
    }

    public ItemBuilder setLore(List<String> lore)
    {
        this.im = this.is.getItemMeta();
        this.im.setLore(lore);
        this.is.setItemMeta(this.im);
        return this;
    }

    public String toBase64()
    {
        try
        {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

            dataOutput.writeObject(this.is);
            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        }
        catch (Exception e)
        {
            throw new IllegalStateException("Unable to save item stack", e);
        }
    }

    public ItemBuilder fromBase64(String from)
    {
        try
        {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(from));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            this.is = ((ItemStack)dataInput.readObject());
            dataInput.close();
        }
        catch (IOException |ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return this;
    }

    public ItemStack build()
    {
        return this.is;
    }
}

