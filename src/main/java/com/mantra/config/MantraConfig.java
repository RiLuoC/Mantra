package com.mantra.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.mantra.Mantra;

@Config(modid = Mantra.MODID, name = "口头禅")
public class MantraConfig {
    
    @Config.LangKey("mantra.config.prefix")
    @Config.Comment("在消息开头添加的文字")
    public static String prefix = "【示例】";

    @Config.LangKey("mantra.config.suffix")
    @Config.Comment("在消息末尾添加的文字")
    public static String suffix = "~";

    @Config.LangKey("mantra.config.enablePrefix")
    @Config.Comment("是否在消息开头添加前缀文字")
    public static boolean enablePrefix = false;

    @Config.LangKey("mantra.config.enableSuffix")
    @Config.Comment("是否在消息末尾添加后缀文字")
    public static boolean enableSuffix = false;

    @Config.LangKey("mantra.config.enableReplace")
    @Config.Comment("是否启用文字替换功能")
    public static boolean enableReplace = false;

    @Config.LangKey("mantra.config.replaceRules")
    @Config.Comment("替换规则列表（格式：要替换的文字=替换后的文字）")
    public static String[] replaceRules = {
        "你好=您好",
        "再见=再会"
    };

    @Mod.EventBusSubscriber(modid = Mantra.MODID)
    private static class EventHandler {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(Mantra.MODID)) {
                ConfigManager.sync(Mantra.MODID, Config.Type.INSTANCE);
            }
        }
    }
} 