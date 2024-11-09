package com.mantra;

import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;
import com.mantra.config.MantraConfig;

@Mod(modid = Mantra.MODID, name = Mantra.NAME, version = Mantra.VERSION, clientSideOnly = true)
public class Mantra {
    public static final String MODID = "mantra";
    public static final String NAME = "Mantra";
    public static final String VERSION = "1.0";

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        MinecraftForge.EVENT_BUS.register(this);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        logger.info("Mantra mod initialized");
    }

    @SubscribeEvent
    public void onChat(ClientChatEvent event) {
        String message = event.getMessage();
        
        // 不修改命令
        if (message.startsWith("/")) {
            return;
        }

        // 执行文字替换
        if (MantraConfig.enableReplace) {
            for (String rule : MantraConfig.replaceRules) {
                String[] parts = rule.split("=", 2);
                if (parts.length == 2) {
                    String from = parts[0];
                    String to = parts[1];
                    message = message.replace(from, to);
                }
            }
        }

        // 添加前缀
        if (MantraConfig.enablePrefix && !MantraConfig.prefix.isEmpty()) {
            message = MantraConfig.prefix + message;
        }

        // 添加后缀
        if (MantraConfig.enableSuffix && !MantraConfig.suffix.isEmpty()) {
            message = message + MantraConfig.suffix;
        }

        // 设置修改后的消息
        if (!message.equals(event.getMessage())) {
            event.setMessage(message);
        }
    }
} 