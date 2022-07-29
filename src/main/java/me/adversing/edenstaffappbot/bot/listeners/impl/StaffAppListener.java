package me.adversing.edenstaffappbot.bot.listeners.impl;

import me.adversing.edenstaffappbot.bot.EdenBot;
import me.adversing.edenstaffappbot.bot.Utils;
import me.adversing.edenstaffappbot.bot.listeners.BotListener;
import me.adversing.edenstaffappbot.bot.utils.chat.ChatUtils;
import me.adversing.edenstaffappbot.bot.utils.chat.Emoji;
import me.adversing.edenstaffappbot.bot.utils.chat.InlineKeyboardBuilder;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class StaffAppListener implements BotListener {

    @Override
    public void execute(Update update, AbsSender sender) {
        if (update.getMessage().hasText() && update.getMessage().isReply()) {
            String text = update.getMessage().getReplyToMessage().getText();

            if (text.equals("Send after this message your telegra.ph Staff Application link or type cancel to go back to the main menu.")) {


                if (update.getMessage().getText().contains("telegra.ph")) {

                    SendMessage sendMessage = InlineKeyboardBuilder.create(update.getMessage().getChatId())
                            .setText("Thanks for submitting your staff application." +
                                    " If you don't receive a message from the Staff within 2 weeks consider yourself denied.")
                            .row()
                            .button(Emoji.LEFT_ARROW + " HOME", "display_homepage", null)
                            .endRow()
                            .build();
                    try {
                        sender.executeAsync(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }


                    DeleteMessage deleteMessage = new DeleteMessage();
                    deleteMessage.setMessageId(update.getMessage().getReplyToMessage().getMessageId());
                    deleteMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
                    try {
                        sender.executeAsync(deleteMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }

                    String text0 = "\u2757 NUOVA STAFF APPLICATION RICEVUTA\n" +
                            "\n" +
                            "Autore: @" + update.getMessage().getFrom().getUserName() + " | \uD83C\uDD94: " + update.getMessage().getFrom().getId() +
                            "\nLink: " + update.getMessage().getText();

                    if (EdenBot.getInstance().getSqlConnection().selectStaff().isEmpty()) {
                        SendMessage sendMessage1 = new SendMessage();
                        sendMessage1.setText("There are no Staff Members registered in this bot's database.");
                        sendMessage1.setChatId(String.valueOf(update.getMessage().getChatId()));
                        try {
                            sender.executeAsync(sendMessage1);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        return;
                    }
                    ChatUtils.sendStaffMessage(text0, true, false);

                } else if (update.getMessage().getText().equalsIgnoreCase("cancel")){
                    DeleteMessage deleteMessage = new DeleteMessage();
                    deleteMessage.setMessageId(update.getMessage().getReplyToMessage().getMessageId());
                    deleteMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
                    try {
                        sender.executeAsync(deleteMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }

                    try {
                        sender.executeAsync(Utils.sendHomeMenu(update.getMessage().getFrom(), update.getMessage().getChatId()));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }

                    DeleteMessage deleteMessage1 = new DeleteMessage();
                    deleteMessage1.setMessageId(update.getMessage().getMessageId());
                    deleteMessage1.setChatId(String.valueOf(update.getMessage().getChatId()));
                    try {
                        sender.executeAsync(deleteMessage1);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }

                } else {
                    SendMessage sendMessage1 = InlineKeyboardBuilder.create(update.getMessage().getChatId())
                            .setText("Your message doesn't contain a telegra.ph link with your staff application, please retry.")
                            .row()
                            .button(Emoji.LEFT_ARROW + " HOME", "display_homepage", null)
                            .endRow()
                            .build();
                    try {
                        sender.executeAsync(sendMessage1);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }

                    DeleteMessage deleteMessage = new DeleteMessage();
                    deleteMessage.setMessageId(update.getMessage().getReplyToMessage().getMessageId());
                    deleteMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
                    try {
                        sender.executeAsync(deleteMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
