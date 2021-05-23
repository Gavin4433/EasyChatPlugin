package net.liangcha.EasyChat.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import java.util.Collection;
import java.util.List;
public class mute implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(sender.isOp()&&args.length==1)
        {
            Plugin plugin = Bukkit.getPluginManager().getPlugin("EasyChat");
            List<String> mute_players = plugin.getConfig().getStringList("mute_players");
            if(mute_players.contains(args[0]))
            {
                sender.sendMessage("该玩家已被禁言，所以操作无效!");
            }
            else {
                mute_players.add(args[0]);
                plugin.getConfig().set("mute_players", mute_players);
                sender.sendMessage(ChatColor.GOLD + "已禁言" + args[0] + "!");
                plugin.saveConfig();
                Collection<Player> players = (Collection<Player>) Bukkit.getOnlinePlayers();
                players.forEach(player -> {
                    player.sendMessage(ChatColor.RED + "[全服公告]" + ChatColor.GOLD + "管理员已禁言" + args[0] + "!");
                });
            }
        }
        else {
            sender.sendMessage(ChatColor.RED+"权限不足或指令使用有误!");
        }
        return false;
    }
}
