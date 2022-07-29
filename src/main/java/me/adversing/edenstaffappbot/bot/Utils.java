package me.adversing.edenstaffappbot.bot;

import me.adversing.edenstaffappbot.bot.utils.chat.Emoji;
import me.adversing.edenstaffappbot.bot.utils.chat.InlineKeyboardBuilder;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;

public class Utils {

    public static SendMessage sendHomeMenu(User user, long chatId) {
        SendMessage sendMessage = InlineKeyboardBuilder.create(chatId)
                .setText("Hello " + user.getFirstName() + ", here you can start your Staff Application " +
                        "process to become a Staff Member of <b>EdenMine Network</b>" +
                        ".\n\nClick the buttons below to see the requirements you need or to send your staff application.")
                .row()
                .button("\uD83D\uDDC3 REQUIREMENTS", "send_requirements", null)
                .button(Emoji.BLACK_QUESTION_MARK_ORNAMENT + " BOT INFO", "send_bot_info", null)
                .endRow()
                .row()
                .button("\uD83D\uDCDD SEND STAFF APPLICATION", "start_staff_app_process", null)
                .endRow()
                .row()
                .button(Emoji.NO_ENTRY_SIGN + " MANAGEMENT PANEL", "show_management_panel", null)
                .endRow()
                .build();
        return sendMessage;
    }

    public static SendMessage sendRequirements(long chatId) {
        String text = "<b>EdenMine Staff Application</b>\n" +
                "\n" +
                "<i>Requirements</i>:\n" +
                "- You must be 14 years or older\n" +
                "- You must have a working microphone.\n" +
                "- Have a Discord & Telegram account\n" +
                "- You must be able to speak/understand English properly\n" +
                "- Being subscribed to our Telegram channel @edenmine\n" +
                "\n\n" +
                "<b>RULES</b>" +
                "\n" +
                "Hey there panda! I see you are interested into join our amazing staff as supporter so let me tell you some little guidelines to write your application.\n" +
                "In EdenMine we like people who can express themselves so try to provide us as much information as you can about you, remember we are not inside your head! Also, we are looking for originality in your application so try to not give us generic answers or you gonna lose our interest in you.";

        SendMessage sendMessage = InlineKeyboardBuilder.create(chatId)
                .setText(text)
                .row()
                .button(Emoji.LEFT_ARROW + " HOME", "display_homepage", null)
                .endRow()
                .build();
        return sendMessage;
    }

    public static SendMessage sendBotInfo(long chatId) {
        String text = "This bot was coded by @changingthepast using magic :)";

        SendMessage sendMessage = InlineKeyboardBuilder.create(chatId)
                .setText(text)
                .row()
                .button(Emoji.LEFT_ARROW + " HOME", "display_homepage", null)
                .endRow()
                .build();
        return sendMessage;
    }

    public static SendMessage sendStaffAppProcessMenu(long chatId) {
        String text = "Ok, now fill this form using <b>telegra.ph</b>, replacing the '[]' (brackets) symbols with your answers to the questions.\n" +
                "You can copy & paste the form below into your telegra.ph.\n" +
                "\n" +
                "Once you finished copy the link of your telegra.ph containing your Staff Application, hit the \"\u270D PROCEED\" button and then paste the link of your telegra.ph in this telegram chat.\n" +
                "As soon as you sent the message, your Staff Application telegra.ph will be reviewed by EdenMine Staff Team.\n" +
                "\n" +
                "If you changed your mind, don't worry, just hit the button \"\u2B05 HOME\" to return to the main menu.\n" +
                "\n" +
                "<b>FORM</b>\n" +
                "\n" +
                "<code>What is your current Minecraft in-game name?\n" +
                "[]\n" +
                "How old are you?\n" +
                "[]\n" +
                "What country are you from?\n" +
                "[]\n" +
                "What language/s are you capable to speak/write? (You must speak English fluently.)\n" +
                "[]\n" +
                "How many hours are you able to stay online moderating our server per day?\n" +
                "[]\n" +
                "Any previous staff experience? If the answer is yes, tell us the server name and your role there\n" +
                "[]\n" +
                "Any previous punishments in EdenMine?\n" +
                "[]\n" +
                "What is your Telegram?\n" +
                "[]\n" +
                "This is how you will be contacted if your application is accepted. If you do not have telegram, please download it here: https://telegram.org/. Make sure you have an \"@\" and provide it below\n" +
                "[]\n" +
                "What is your Discord #ID?\n" +
                "[]\n" +
                "We use discord for all of our staff chats and communication. If you do not have discord, you can get it here: https://discordapp.com/\n" +
                "[]\n" +
                "How would you describe your personality?\n" +
                "[]\n" +
                "What would you say your strengths are? What about your weaknesses?\n" +
                "[]\n" +
                "What do you like the most about our server community? What about the less?\n" +
                "[]\n" +
                "Why are you applying for a staff vacancy in Edenmine?\n" +
                "[]\n" +
                "Do you have any suggestion or idea to improve our server? If the answer is yes, tell us what it's about and how you would carry it out\n" +
                "[]\n" +
                "In this part of your application you gonna moderate some daily situations that you can experience being staffer in Edenmine. Tell us what would you do in every situation.\n" +
                "\n" +
                "Two players start to argue in public chat, insulting each other and flooding the chat with insults and blasphemies.\n" +
                "[]\n" +
                "A player reports a bug in a game-mode.\n" +
                "[]\n" +
                "A player reports another player who was cheating previously but it's not online anymore.\n" +
                "[]\n" +
                "You see a player using cheats in a game-mode.\n" +
                "[]\n" +
                "A player asks for support about something non-related with your competencies.\n" +
                "[]\n" +
                "A player tries to provoke you knowing you cannot get violent or aggressive as a staffer.\n" +
                "[]</code>\n";

        SendMessage sendMessage = InlineKeyboardBuilder.create(chatId)
                .setText(text)
                .row()
                .button(Emoji.LEFT_ARROW + " HOME", "display_homepage", null)
                .button("\u270D PROCEED", "send_staff_application_final", null)
                .endRow()
                .build();
        return sendMessage;
    }

    public static SendMessage sendStaffAppFinalStage(long chatId) {
        String text = "Send after this message your <b>telegra.ph</b> Staff Application link or type <code>cancel</code> to go back to the main menu.";
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableHtml(true);
        sendMessage.setText(text);
        sendMessage.setChatId(String.valueOf(chatId));

        ForceReplyKeyboard kb = ForceReplyKeyboard
                .builder()
                .inputFieldPlaceholder("Send a telegra.ph link or type \"cancel\"...")
                .forceReply(true)
                .selective(true)
                .build();
        sendMessage.setReplyMarkup(kb);


        return sendMessage;
    }

    public static SendMessage sendErrorMessage(long chatId) {
        String text = "You don't have enough permissions to see this section.";
        SendMessage sendMessage = InlineKeyboardBuilder.create(chatId)
                .setText(text)
                .row()
                .button(Emoji.LEFT_ARROW + " HOME", "display_homepage", null)
                .endRow()
                .build();
        return sendMessage;
    }

    public static SendMessage sendStaffManagementPanel(long chatId) {
        String text = "Welcome to the staff management panel, here you can view the list of who has access to the staff applications.\n\nYou edit can the list of the staff members by using the buttons below.";

        SendMessage sendMessage = InlineKeyboardBuilder.create(chatId)
                .setText(text)
                .row()
                .button("\uD83D\uDCC4 VIEW STAFF LIST", "send_staff_list", null)
                .endRow()
                .row()
                .button("\u2795 ADD STAFF MEMBER", "add_staff_member", null)
                .button("\u2716 REMOVE STAFF MEMBER", "remove_staff member", null)
                .endRow()
                .row()
                .button(Emoji.LEFT_ARROW + " HOME", "display_homepage", null)
                .endRow()
                .build();
        return sendMessage;
    }

    public static SendMessage sendStaffList(long chatId) {
        StringBuilder sb = new StringBuilder();

        for (long id : EdenBot.getInstance().getSqlConnection().selectStaff()) {
            sb.append("- ").append(id).append("\n");
        }

        String text = "<b>Current Staff List</b> \n\n" + sb;

        SendMessage sendMessage = InlineKeyboardBuilder.create(chatId)
                .setText(text)
                .row()
                .button(Emoji.LEFT_ARROW + " HOME", "display_homepage", null)
                .endRow()
                .build();
        return sendMessage;
    }

    public static SendMessage sendAddStaffMember(long chatId) {
        String text = "Send after this message the the <b>ID</b> of the staff member you want to add, divided by a space.";
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableHtml(true);
        sendMessage.setText(text);
        sendMessage.setChatId(String.valueOf(chatId));

        ForceReplyKeyboard kb = ForceReplyKeyboard
                .builder()
                .inputFieldPlaceholder("Send the ID of the user you want to add...")
                .forceReply(true)
                .selective(true)
                .build();
        sendMessage.setReplyMarkup(kb);

        return sendMessage;
    }

    public static SendMessage sendRemoveStaffMember(long chatId) {
        String text = "Send after this message the <b>ID</b> of the staff member you want to remove, divided by a space.";
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableHtml(true);
        sendMessage.setText(text);
        sendMessage.setChatId(String.valueOf(chatId));

        ForceReplyKeyboard kb = ForceReplyKeyboard
                .builder()
                .inputFieldPlaceholder("Send the ID of the user you want to remove...")
                .forceReply(true)
                .selective(true)
                .build();
        sendMessage.setReplyMarkup(kb);

        return sendMessage;
    }

    public static SendMessage sendManagementPanel(long chatId) {
        String text = "Welcome to the management panel, here you can view the list of who has access to the staff applications and the admins.\n\nUse the buttons below to navigate.";

        SendMessage sendMessage = InlineKeyboardBuilder.create(chatId)
                .setText(text)
                .row()
                .button("\uD83D\uDC68\u200D\uD83D\uDCBC STAFF MANAGEMENT PANEL", "show_staff_management_panel", null)
                .button("\uD83E\uDDD1\u200D\uD83D\uDCBB ADMIN MANAGEMENT PANEL", "show_admin_management_panel", null)
                .endRow()
                .row()
                .button(Emoji.LEFT_ARROW + " HOME", "display_homepage", null)
                .endRow()
                .build();
        return sendMessage;
    }

    public static SendMessage sendAdminManagementPanel(long chatId) {
        String text = "Welcome to the admin management panel, here you can view the list of who has access to the staff management panel.\n\nYou edit can the list of the admins by using the buttons below.";

        SendMessage sendMessage = InlineKeyboardBuilder.create(chatId)
                .setText(text)
                .row()
                .button("\uD83D\uDCC4 VIEW ADMIN LIST", "send_admin_list", null)
                .endRow()
                .row()
                .button("\u2795 ADD ADMIN", "add_admin", null)
                .button("\u2716 REMOVE ADMIN", "remove_admin", null)
                .endRow()
                .row()
                .button(Emoji.LEFT_ARROW + " HOME", "display_homepage", null)
                .endRow()
                .build();
        return sendMessage;

    }

    public static SendMessage sendAdminList(long chatId) {
        StringBuilder sb = new StringBuilder();

        for (long id : EdenBot.getInstance().getSqlConnection().selectAdmins()) {
            sb.append("- ").append(id).append("\n");
        }

        String text = "<b>Current Admin List</b> \n\n" + sb;

        SendMessage sendMessage = InlineKeyboardBuilder.create(chatId)
                .setText(text)
                .row()
                .button(Emoji.LEFT_ARROW + " HOME", "display_homepage", null)
                .endRow()
                .build();
        return sendMessage;
    }

    public static SendMessage sendAddAdmin(long chatId) {
        String text = "Send after this message the the <b>ID</b> of the admin you want to add, divided by a space.";
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableHtml(true);
        sendMessage.setText(text);
        sendMessage.setChatId(String.valueOf(chatId));

        ForceReplyKeyboard kb = ForceReplyKeyboard
                .builder()
                .inputFieldPlaceholder("Send the ID of the user you want to add...")
                .forceReply(true)
                .selective(true)
                .build();
        sendMessage.setReplyMarkup(kb);

        return sendMessage;
    }

    public static SendMessage sendRemoveAdmin(long chatId) {
        String text = "Send after this message the <b>ID</b> of the admin you want to remove, divided by a space.";
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableHtml(true);
        sendMessage.setText(text);
        sendMessage.setChatId(String.valueOf(chatId));

        ForceReplyKeyboard kb = ForceReplyKeyboard
                .builder()
                .inputFieldPlaceholder("Send the ID of the user you want to remove...")
                .forceReply(true)
                .selective(true)
                .build();
        sendMessage.setReplyMarkup(kb);

        return sendMessage;
    }
}
