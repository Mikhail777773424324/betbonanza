package by.valuesoft;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.DeleteMessage;
import com.pengrad.telegrambot.request.EditMessageText;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
//import io.stalker.Consts;
//import io.stalker.Stalker;

import java.util.concurrent.TimeUnit;

public class Telegram {

    private static int configMessageId = 0;
    private static int statsMessageId  = 0;
    private static TelegramBot bot;
    private static final long chatId = Long.parseLong(System.getenv("TELEGRAM_CHAT_ID"));

//    private final Coin444 coin;
    public Telegram() {
        start();
    }

    public void start() {
        bot = new TelegramBot(System.getenv("TELEGRAM_TOKEN"));
        restart();
        final int confirmedUpdatesAll = UpdatesListener.CONFIRMED_UPDATES_ALL;
        bot.setUpdatesListener(updates -> {
            for (Update update : updates) {
                if (update.message().from().id() == Long.parseLong(System.getenv("TELEGRAM_CHAT_ID"))) {
                    try {
                        String text = update.message().text().toLowerCase();
                        long chatId = update.message().chat().id();
                        String[] commandArgs = text.split("\\s+");
                        String command = commandArgs[0];
                        String commandData = commandArgs.length > 1 ? commandArgs[1] : "";

                        switch (command) {

                            // *******
//                            case "c":
//                                Consts.CANCEL = true;
//                                break;
//
//                            case "s":
//                                Consts.SELL = true;
//                                break;
//
//                            case "q":
//                                Consts.QUIT = true;
//                                break;
//
//                            // *******
//                            case "sq":
//                                Consts.START_QTY = new BigDecimal(commandData);
//                                break;
//
//                            case "ss":
//                                Consts.START_STEP = new BigDecimal(commandData);
//                                break;
//
//                            // *******
//                            case "dt":
//                                Consts.DELTA_TIME = Long.parseLong(commandData) * 1000L;
//                                break;
//
//                            case "dm":
//                                Consts.DELTA_MIN = new BigDecimal(commandData);
//                                break;
//
//                            // *******
//                            case "oc":
//                                Consts.ORDERS_COUNT = Integer.parseInt(commandData);
//                                break;
//
//                            case "ot":
//                                Consts.ORDERS_TIME = Long.parseLong(commandData) * 1000L;
//                                break;
//
//                            case "we":
//                                Consts.WAIT_EXECUTED = Boolean.parseBoolean(commandData);
//                                break;
//
//                            // *******
//                            case "tp":
//                                Consts.TRADE_PART = new BigDecimal(commandData);
//                                break;
                        }

                        deleteMessage(update);
                        updateMessage(chatId, configMessageId, getConfigText());
                    } catch (Exception e) {
                        e.printStackTrace();
                        DeleteMessage deleteMessage = new DeleteMessage(chatId, update.message().messageId());
                        bot.execute(deleteMessage);
                    }
                }
            }
            return confirmedUpdatesAll;
        }, Throwable::printStackTrace);
    }


    private void deleteMessage(Update update) {
        DeleteMessage deleteMessage = new DeleteMessage(chatId, update.message().messageId());
        bot.execute(deleteMessage);
    }

    private void restart() {
        if (configMessageId != 0) {
            updateMessage(chatId, configMessageId, getConfigText());
        } else {
            configMessageId = sendMessage(chatId, getConfigText());
            statsMessageId = sendMessage(chatId, getStatsText());

            startStatsUpdater();
        }
    }

    private void startStatsUpdater() {
        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                updateMessage(chatId, statsMessageId, getStatsText());
            }
        }).start();
    }

    private String getConfigText() {
        String sb = "";
        return sb;
//                "\t" + Consts.SYMBOL;
//                "`tm    |   test mode     ` " + Consts.TEST + "\n\n" +
//
//                "`sq    |   start qty     ` " + Consts.START_QTY + "\n" +
//                "`ss    |   start step    ` " + Consts.START_STEP + "\n\n" +
//
//                "`dt    |   delta time    ` " + (Consts.DELTA_TIME / 1000L) + "\n" +
//                "`dm    |   delta min     ` " + Consts.DELTA_MIN + "\n\n" +
//
//                "`oc    |   orders count  ` " + Consts.ORDERS_COUNT + "\n" +
//                "`ot    |   orders time   ` " + (Consts.ORDERS_TIME / 1000L) + "\n" +
//                "`we    |   wait executed ` " + Consts.WAIT_EXECUTED + "\n\n" +
//
////                "`pb    |   points min bid` " + Consts.POINTS_MIN_BID + "\n\n" +
//
//                "`tp    |   trade part    ` " + Consts.TRADE_PART + "\n";

//        return sb.replaceAll("\\.", "\\\\.").replaceAll("-", "\\\\-");
    }

    private int sendMessage(Object chatId, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message)
                .parseMode(ParseMode.MarkdownV2)
                .disableWebPagePreview(true);

        SendResponse response = bot.execute(sendMessage);

        return response.message().messageId();
    }

    private void updateMessage(Object chatId, int messageId, String text) {
        bot.execute(
                new EditMessageText(chatId, messageId, text)
                        .parseMode(ParseMode.MarkdownV2)
                        .disableWebPagePreview(true)
        );
    }

    private String getStatsText() {
        return "123";
    }

}