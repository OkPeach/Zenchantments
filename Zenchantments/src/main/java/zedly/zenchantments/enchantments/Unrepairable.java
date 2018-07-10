package zedly.zenchantments.enchantments;

import zedly.zenchantments.CustomEnchantment;
import zedly.zenchantments.Tool;

import static zedly.zenchantments.Tool.ALL;

public class Unrepairable extends CustomEnchantment {

    public Unrepairable() {
        maxLevel = 1;
        loreName = "Unrepairable";
        probability = 0;
        enchantable = new Tool[]{ALL};
        conflicting = new Class[]{};
        description = "Prevents an item from being repaired";
        cooldown = 0;
        power = -1.0;
        handUse = 0;
    }

    public int getEnchantmentId() {
        return 73;
    }
}