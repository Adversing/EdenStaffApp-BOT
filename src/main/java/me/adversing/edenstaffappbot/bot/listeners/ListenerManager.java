package me.adversing.edenstaffappbot.bot.listeners;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class ListenerManager {

    private final List<BotListener> listeners = new ArrayList<>();

    public void addListener(BotListener listener) {
        listeners.add(listener);
    }

    public void executeUpdate(Update event, AbsSender sender) {
        listeners.forEach(listener -> {
            listener.execute(event, sender);
        });
    }

}
