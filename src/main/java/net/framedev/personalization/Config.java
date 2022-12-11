package net.framedev.personalization;

public class Config {
    Personalization plugin = Personalization.getInstance();

    public void defaultData() {
        plugin.getConfig().set("personalization.death_msg", "был вызван Сатаной-подскользнулся и упал-отправился в мир иной");
        plugin.getConfig().set("personalization.hit_effects", "ENDERDRAGON_SHOOT-STEP_SOUND-ZOMBIE_INFECT");
        plugin.getConfig().set("personalization.kill_effects", "BLAZE_SHOOT-CHORUS_FLOWER_DEATH-IRON_DOOR_CLOSE");
        plugin.getConfig().set("personalization.arrow_trail", "BARRIER-DRIP_LAVA-EXPLOSION_HUGE-PORTAL-WATER_SPLASH");

        plugin.getConfig().set("inventories.main.size", 9);
        plugin.getConfig().set("inventories.main.title", "Используемые");

        plugin.getConfig().set("inventories.effects.size", 36);
        plugin.getConfig().set("inventories.effects.title", "Выбор");
    }
}
