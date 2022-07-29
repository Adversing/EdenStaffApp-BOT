package me.adversing.edenstaffappbot.bot.utils.chat;

import me.adversing.edenstaffappbot.bot.EdenBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class ChatUtils {

    public static void sendStaffMessage(String message, boolean htmlFormatting, boolean markdownFormatting) {
        for (Long aLong : EdenBot.getInstance().getSqlConnection().selectStaff()) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setText(message);
            sendMessage.enableHtml(htmlFormatting);
            sendMessage.enableMarkdown(markdownFormatting);
            sendMessage.setChatId(String.valueOf(aLong));
            try {
                EdenBot.getInstance().executeAsync(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        for (Long aLong : EdenBot.getInstance().getSqlConnection().selectAdmins()) {
            SendMessage sendMessage0 = new SendMessage();
            sendMessage0.setText(message);
            sendMessage0.enableHtml(htmlFormatting);
            sendMessage0.enableMarkdown(markdownFormatting);
            sendMessage0.setChatId(String.valueOf(aLong));
            try {
                EdenBot.getInstance().executeAsync(sendMessage0);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }



}
