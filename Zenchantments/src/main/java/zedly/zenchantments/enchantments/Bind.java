package zedly.zenchantments.enchantments;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import zedly.zenchantments.Config;
import zedly.zenchantments.CustomEnchantment;
import zedly.zenchantments.Storage;
import zedly.zenchantments.Tool;

import java.util.*;

import static zedly.zenchantments.Tool.ALL;

public class Bind extends CustomEnchantment {

    public Bind() {
        maxLevel = 1;
        loreName = "Bind";
        probability = 0;
        enchantable = new Tool[]{ALL};
        conflicting = new Class[]{};
        description = "Keeps items with this enchantment in your inventory after death";
        cooldown = 0;
        power = -1.0;
        handUse = 0;

    }

    public int getEnchantmentId() {
        return 4;
    }

    @Override
    public boolean onPlayerDeath(final PlayerDeathEvent evt, int level, boolean usedHand) {
        if(evt.getKeepInventory()) {
            return false;
        }
        final Player player = evt.getEntity();
        Config config = Config.get(player.getWorld());
        final ItemStack[] contents = player.getInventory().getContents().clone();
        final List<ItemStack> removed = new ArrayList<>();
        for(int i = 0; i < contents.length; i++) {
            if(!config.getEnchants(contents[i]).containsKey(this)) {
                contents[i] = null;
            } else {
                removed.add(contents[i]);
                evt.getDrops().remove(contents[i]);
            }
        }
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Storage.zenchantments, () -> {
            if(evt.getKeepInventory()) {
                evt.getDrops().addAll(removed);
            } else {
                player.getInventory().setContents(contents);
            }
        }, 1);
        return true;
    }
}