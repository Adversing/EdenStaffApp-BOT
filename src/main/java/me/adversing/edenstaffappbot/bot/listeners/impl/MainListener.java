package me.adversing.edenstaffappbot.bot.listeners.impl;

import me.adversing.edenstaffappbot.bot.database.SqlConnection;
import me.adversing.edenstaffappbot.bot.listeners.BotListener;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

public class MainListener implements BotListener {

    SqlConnection data = new SqlConnection();

    @Override
    public void execute(Update update, AbsSender sender) {
        if (update.getMessage().getFrom().getIsBot()) return;
        if (update.getMessage() != null) {
            try {
                data.insertUser(update.getMessage().getFrom().getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
