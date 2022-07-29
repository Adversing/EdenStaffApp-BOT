package me.adversing.edenstaffappbot.bot;

import lombok.Getter;
import me.adversing.edenstaffappbot.Loader;
import me.adversing.edenstaffappbot.bot.commands.impl.StartCommand;
import me.adversing.edenstaffappbot.bot.database.SqlConnection;
import me.adversing.edenstaffappbot.bot.listeners.ListenerManager;
import me.adversing.edenstaffappbot.bot.listeners.impl.*;
import me.adversing.edenstaffappbot.bot.utils.exceptions.SystemException;
import me.adversing.edenstaffappbot.bot.utils.vars.BotVars;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.*;
import java.util.*;

@Getter
public class EdenBot extends TelegramLongPollingCommandBot {


    public static HashMap<String, Long> staffMembers;
    private static EdenBot instance;

    public static String host, username, password, database;


    private final BotVars botVars;
    private final SqlConnection sqlConnection;


    private final ListenerManager listenerManager = new ListenerManager();
    private final Logger logger = LoggerFactory.getLogger("EdenBot");


    public EdenBot(BotVars botVars) {
        instance = this;
        this.botVars = botVars;

        staffMembers = new HashMap<>();

        register(new StartCommand());

        this.listenerManager.addListener(new ButtonListener());
        this.listenerManager.addListener(new StaffAppListener());
        this.listenerManager.addListener(new MainListener());
        this.listenerManager.addListener(new StaffManagementListener());
        this.listenerManager.addListener(new AdminManagementListener());

        sqlConnection = new SqlConnection();
        logger.info("Bot Loaded - Coded by Adversing.");


    }

    @Override
    public String getBotUsername() {
        return botVars.getUsername();
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        listenerManager.executeUpdate(update, this);
    }

    @Override
    public String getBotToken() {
        return botVars.getToken();
    }


    public void generateErrorMessage(Chat chat, SystemException throwable) {
        String errorCode = generateErrorCode();
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableHtml(true);
        sendMessage.setChatId(String.valueOf(chat.getId()));
        sendMessage.setText(String.format("Error: %s %s", errorCode, throwable.getMessage()));
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        if (throwable.getCause() == null) logger.error(getStackTrace(throwable));
        else logger.error(getStackTrace(throwable.getCause()));
    }

    private String getStackTrace(Throwable throwable) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString().replace("at ", "-> ");
    }

    public static String generateErrorCode() {
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder((100000 + rnd.nextInt(900000)) + "-");

        for (int i = 0; i < 5; i++)
            sb.append(chars[rnd.nextInt(chars.length)]);

        return "#" + sb;
    }

    public static EdenBot getInstance() {
        return instance;
    }
}
