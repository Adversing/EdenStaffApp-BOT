package me.adversing.edenstaffappbot.bot.commands.impl;

import me.adversing.edenstaffappbot.bot.Utils;
import me.adversing.edenstaffappbot.bot.commands.MessagedBotCommand;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class StartCommand extends MessagedBotCommand {

    public StartCommand() {
        super("start", "Start");
    }

    @Override
    public void execute(AbsSender absSender, User user, Message message, Chat chat, String[] strings) {
        if (!chat.isUserChat()) return;
        try {
            absSender.executeAsync(Utils.sendHomeMenu(user, chat.getId()));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}