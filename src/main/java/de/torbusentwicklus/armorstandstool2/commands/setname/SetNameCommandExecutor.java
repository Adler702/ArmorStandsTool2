package de.torbusentwicklus.armorstandstool2.commands.setname;

import lombok.AllArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@AllArgsConstructor
public class SetNameCommandExecutor implements CommandExecutor {

    private ISetNameCommand iSetNameCommand;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            return false;
        }

        Player player = (Player) commandSender;

        if (!player.hasPermission("armorstandstool.use")) {
            return false;
        }

        iSetNameCommand.setName(player, strings);

        return false;
    }
}
