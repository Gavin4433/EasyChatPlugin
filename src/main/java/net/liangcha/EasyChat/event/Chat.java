package net.liangcha.EasyChat.event;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
public class Chat implements Listener {
    private Map<Player, Long> last = new HashMap<>();

    @EventHandler
    public void Checker(AsyncPlayerChatEvent e) {
        //检测玩家是否被禁言
            if (!check(e.getPlayer())) {
                String msg = e.getMessage();
                Plugin plugin = Bukkit.getPluginManager().getPlugin("EasyChat");
                List<String> fucks = plugin.getConfig().getStringList("fucks");
                int chat_cd = plugin.getConfig().getInt("chat_cd");
                fucks.forEach(str -> {
                    if (msg.toLowerCase().replace(" ", "").contains(str)) {
                        e.setMessage(msg.toLowerCase().replace(" ", "").replace(str, String.format("%" + str.length() + "s", "").replace(' ', '*')));
                        Player player = e.getPlayer();

                    }
                });
            //判定玩家说话是否过快
            if (last.getOrDefault(e.getPlayer(), -1L) + chat_cd * 1000L > System.currentTimeMillis()) {
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(ChatColor.RED + "你说话太快了!");

                } else {
                    last.put(e.getPlayer(), System.currentTimeMillis());

                }
            }
            else{
                e.setCancelled(true);
                e.getPlayer().sendMessage(ChatColor.RED+"你被禁言了!");
            }


    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        last.remove(event.getPlayer());
    }

    public boolean check(Player playerIn) {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("EasyChat");
        List<String> mute_players = plugin.getConfig().getStringList("mute_players");
        if (mute_players.contains(playerIn.getName())) {
            System.out.print("该玩家在禁言列表中");

            return true;
        }
        else{
            return false;
        }
    }
}

