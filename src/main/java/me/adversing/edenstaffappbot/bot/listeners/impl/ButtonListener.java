package me.adversing.edenstaffappbot.bot.listeners.impl;

import me.adversing.edenstaffappbot.bot.EdenBot;
import me.adversing.edenstaffappbot.bot.Utils;
import me.adversing.edenstaffappbot.bot.listeners.BotListener;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

public class ButtonListener implements BotListener {
    @Override
    public void execute(Update update, AbsSender sender) {
        if (update.hasCallbackQuery() && update.getCallbackQuery().getData() != null) {
            DeleteMessage delete = new DeleteMessage();
            delete.setChatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()));
            delete.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
            try {
                sender.execute(delete);
            } catch (Exception e) {
                e.printStackTrace();
            }

            switch (update.getCallbackQuery().getData()) {
                case "start_staff_app_process":
                    try {
                        sender.executeAsync(Utils.sendStaffAppProcessMenu(update.getCallbackQuery().getMessage().getChatId()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "send_staff_application_final":
                    try {
                        sender.executeAsync(Utils.sendStaffAppFinalStage(update.getCallbackQuery().getMessage().getChatId()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "send_bot_info":
                    try {
                        sender.executeAsync(Utils.sendBotInfo(update.getCallbackQuery().getMessage().getChatId()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "send_requirements":
                    try {
                        sender.executeAsync(Utils.sendRequirements(update.getCallbackQuery().getMessage().getChatId()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "display_homepage":
                    try {
                        sender.executeAsync(Utils.sendHomeMenu(update.getCallbackQuery().getFrom(), update.getCallbackQuery().getMessage().getChatId()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "show_staff_management_panel":
                    boolean admin = EdenBot.getInstance().getSqlConnection().isAdmin(update.getCallbackQuery().getFrom().getId());
                    if (!admin) {
                        try {
                            sender.executeAsync(Utils.sendErrorMessage(update.getCallbackQuery().getMessage().getChatId()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    }

                    try {
                        sender.executeAsync(Utils.sendStaffManagementPanel(update.getCallbackQuery().getMessage().getChatId()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "send_staff_list":
                    try {
                        sender.executeAsync(Utils.sendStaffList(update.getCallbackQuery().getMessage().getChatId()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "add_staff_member":
                    try {
                        sender.executeAsync(Utils.sendAddStaffMember(update.getCallbackQuery().getMessage().getChatId()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "remove_staff member":
                    try {
                        sender.executeAsync(Utils.sendRemoveStaffMember(update.getCallbackQuery().getMessage().getChatId()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "show_management_panel":

                    boolean isAdmin = EdenBot.getInstance().getSqlConnection().isAdmin(update.getCallbackQuery().getFrom().getId());
                    if (!isAdmin) {
                        try {
                            sender.executeAsync(Utils.sendErrorMessage(update.getCallbackQuery().getMessage().getChatId()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    }

                    try {
                        sender.executeAsync(Utils.sendManagementPanel(update.getCallbackQuery().getMessage().getChatId()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "show_admin_management_panel":
                    if (update.getCallbackQuery().getFrom().getId() != 420209531 && update.getCallbackQuery().getFrom().getId() != 1259348919) {
                        try {
                            sender.executeAsync(Utils.sendErrorMessage(update.getCallbackQuery().getMessage().getChatId()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    }

                    try {
                        sender.executeAsync(Utils.sendAdminManagementPanel(update.getCallbackQuery().getMessage().getChatId()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "send_admin_list":
                    try {
                        sender.executeAsync(Utils.sendAdminList(update.getCallbackQuery().getMessage().getChatId()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "add_admin":
                    try {
                        sender.executeAsync(Utils.sendAddAdmin(update.getCallbackQuery().getMessage().getChatId()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "remove_admin":
                    try {
                        sender.executeAsync(Utils.sendRemoveAdmin(update.getCallbackQuery().getMessage().getChatId()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }
}