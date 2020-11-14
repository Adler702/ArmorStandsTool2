package de.torbusentwicklus.armorstandstool2.commands.editmode;

import de.torbusentwicklus.armorstandstool2.api.provider.ArmorStandsToolProvider;
import de.torbusentwicklus.armorstandstool2.commands.editmode.IArmorStandsEditModeCommand;
import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;

@AllArgsConstructor
public class ArmorStandsEditModeCommandImpl implements IArmorStandsEditModeCommand {

    private final ArmorStandsToolProvider armorStandsToolProvider;

    @Override
    public boolean toggleMode(Player player) {
        if (!armorStandsToolProvider.getEditors().containsKey(player)) {
            armorStandsToolProvider.getEditors().put(player, null);
            return true;
        } else {
            armorStandsToolProvider.getEditors().remove(player);
            return false;
        }
    }
}
