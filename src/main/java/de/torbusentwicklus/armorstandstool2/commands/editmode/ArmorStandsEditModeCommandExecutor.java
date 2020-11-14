package de.torbusentwicklus.armorstandstool2.commands.editmode;

import lombok.AllArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@AllArgsConstructor
public class ArmorStandsEditModeCommandExecutor implements CommandExecutor {

    private IArmorStandsEditModeCommand iArmorStandsEditModeCommand;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            return false;
        }

        Player player = (Player) commandSender;

        if (!player.hasPermission("armorstandstool.use")) {
            return false;
        }

        if (iArmorStandsEditModeCommand.toggleMode(player)) {
            player.sendMessage("§aArmorStandEditMode aktiviert.");
        } else {
            player.sendMessage("§aArmorStandEditMode deaktiviert.");
        }

        return false;
    }
}
