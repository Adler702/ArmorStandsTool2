package de.torbusentwicklus.armorstandstool2.commands.setname;

import de.torbusentwicklus.armorstandstool2.api.provider.ArmorStandsToolProvider;
import lombok.AllArgsConstructor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

@AllArgsConstructor
public class SetNameCommandImpl implements ISetNameCommand {

    private ArmorStandsToolProvider armorStandsToolProvider;

    @Override
    public void setName(Player player, String[] strings) {
        ArmorStand armorStand = armorStandsToolProvider.getEditors().get(player);
        String name = "";
        for (int i = 0; i < strings.length; i++) {
            name += " " + strings[i];
        }
        armorStand.setCustomName(name.replace("&", "§").substring(1));
    }
}
