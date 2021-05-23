package net.liangcha.EasyChat;
import net.liangcha.EasyChat.commands.mute;
import net.liangcha.EasyChat.commands.unmute;
import net.liangcha.EasyChat.event.Chat;
import org.bukkit.plugin.java.JavaPlugin;
public class Main extends JavaPlugin{
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new Chat(),this);
		getCommand("mute").setExecutor(new mute());
		getCommand("unmute").setExecutor(new unmute());
		saveDefaultConfig();
		reloadConfig();
	}
	@Override
	public void onDisable() {
		System.out.print("插件已卸载\n");
	}
	@Override
	public void onLoad() {
		System.out.print("插件已加载\n");
    }

		//new code here

}
