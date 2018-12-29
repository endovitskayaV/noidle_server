package ru.vsu.noidle_server.utils;

import ru.vsu.noidle_server.Constants;

public class TimeUtils {

    private static int SECOND = 1000;
    private static int MINUTE = 60 * SECOND;
    private static int HOUR = 60 * MINUTE;

    public static String toPretty(Long millis) {
        if (millis == null) {
            return null;
        }
        StringBuilder text = new StringBuilder();
        if (millis > HOUR) {
            text.append(millis / HOUR).append(" " + Constants.H + " ");
            millis %= HOUR;
        }
        if (millis > MINUTE) {
            text.append(millis / MINUTE).append(" " + Constants.MIN + " ");
            millis %= MINUTE;
        }
//        if (millis > SECOND) {
//            text.append(millis / SECOND).append(" sec ")
//            millis %= SECOND
//        }
        if (text.toString().isEmpty()) {
            text.append(Constants.LESS_MIN);
        }
        return text.toString();
    }
}
