package me.adversing.edenstaffappbot.bot.listeners.impl;

import me.adversing.edenstaffappbot.bot.EdenBot;
import me.adversing.edenstaffappbot.bot.listeners.BotListener;
import me.adversing.edenstaffappbot.bot.utils.chat.Emoji;
import me.adversing.edenstaffappbot.bot.utils.chat.InlineKeyboardBuilder;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class StaffManagementListener implements BotListener {
    @Override
    public void execute(Update update, AbsSender sender) {
        if (update.getMessage().hasText() && update.getMessage().isReply()) {
            String text = update.getMessage().getReplyToMessage().getText();
            String[] args = update.getMessage().getText().split(" ");
            String id = args[0];


            if (text.equals("Send after this message the the ID of the staff member you want to add, divided by a space.")) {

                if (args.length != 1) {
                    SendMessage sendMessage = InlineKeyboardBuilder.create(update.getMessage().getChatId())
                            .setText("You have to send only the ID of the user you want to set as staff.")
                            .row()
                            .button(Emoji.LEFT_ARROW + " GO BACK", "show_staff_management_panel", null)
                            .endRow()
                            .build();
                    try {
                        sender.executeAsync(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    return;
                }



                if (EdenBot.getInstance().getSqlConnection().isStaff(Long.parseLong(id))) {
                    SendMessage error = InlineKeyboardBuilder.create(update.getMessage().getChatId())
                            .setText("This user is already a staff member.")
                            .row()
                            .button(Emoji.LEFT_ARROW + " GO BACK", "show_staff_management_panel", null)
                            .endRow()
                            .build();
                    try {
                        sender.executeAsync(error);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    return;
                }

                EdenBot.getInstance().getSqlConnection().updateStaff(Long.parseLong(id), true);

                SendMessage sendMessage = InlineKeyboardBuilder.create(update.getMessage().getChatId())
                        .setText(id + " added successfully to the staff list.")
                        .row()
                        .button(Emoji.LEFT_ARROW + " GO BACK", "show_staff_management_panel", null)
                        .endRow()
                        .build();
                try {
                    sender.executeAsync(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }


            } else if (text.equals("Send after this message the ID of the staff member you want to remove, divided by a space.")) {

                if (args.length != 1) {
                    SendMessage sendMessage = InlineKeyboardBuilder.create(update.getMessage().getChatId())
                            .setText("You have to send only the ID of the user you want to remove from the staff-list.")
                            .row()
                            .button(Emoji.LEFT_ARROW + " GO BACK", "show_staff_management_panel", null)
                            .endRow()
                            .build();
                    try {
                        sender.executeAsync(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }




                if (!EdenBot.getInstance().getSqlConnection().isStaff(Long.parseLong(id))) {
                    SendMessage error = InlineKeyboardBuilder.create(update.getMessage().getChatId())
                            .setText("This user is not a staff member.")
                            .row()
                            .button(Emoji.LEFT_ARROW + " GO BACK", "show_staff_management_panel", null)
                            .endRow()
                            .build();
                    try {
                        sender.executeAsync(error);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    return;
                }

                EdenBot.getInstance().getSqlConnection().updateStaff(Long.parseLong(id), false);

                SendMessage sendMessage = InlineKeyboardBuilder.create(update.getMessage().getChatId())
                        .setText(id + " removed successfully from the staff list.")
                        .row()
                        .button(Emoji.LEFT_ARROW + " GO BACK", "show_staff_management_panel", null)
                        .endRow()
                        .build();
                try {
                    sender.executeAsync(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
