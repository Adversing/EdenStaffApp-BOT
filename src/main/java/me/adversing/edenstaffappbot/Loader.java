package me.adversing.edenstaffappbot;

import com.google.common.io.Files;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import me.adversing.edenstaffappbot.bot.EdenBot;
import me.adversing.edenstaffappbot.bot.utils.vars.BotVars;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.security.auth.login.Configuration;
import java.io.*;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Loader {

    private static Properties props;
    
    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger("EM");
        logger.info("------------------------------------------");
        logger.info("EdenMine Software Loader");
        logger.info(" ");
        logger.info(" Loading .");
        logger.info(" Loading ..");
        logger.info(" Loading ...");
        logger.info("System started.");
        logger.info(" ");
        logger.info("------------------------------------------");
        BotVars botVars = new BotVars("", ""); /*TODO Insert here your bot's token. */

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("main-thread").build();
        ExecutorService executor = Executors.newSingleThreadExecutor(namedThreadFactory);

        File file = new File("cache");
        if (!file.exists() && file.mkdirs())
            logger.info("Created cache directory, situated in " + file.getAbsolutePath());

        executor.execute(() -> {
            try {
                TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
                telegramBotsApi.registerBot(new EdenBot(botVars));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        });
    }

    public static Properties loadProperties() {
        Properties props = new Properties();
        File propsFile = new File("config.properties");
        if (!propsFile.exists()) {
            try (InputStream is = Loader.class.getClassLoader().getResourceAsStream("config.properties")) {
                props.load(is);
                try (OutputStream output = new FileOutputStream("config.properties")) {
                    props.store(output, null);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try (InputStream is = new FileInputStream("config.properties")) {
                props.load(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return props;
    }

    
    public static Properties getProperties() {
        return props;
    }

}


