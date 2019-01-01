package ru.vsu.noidle_server.utils;

import ru.vsu.noidle_server.Constants;

import java.time.OffsetDateTime;

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

    public static OffsetDateTime toOffsetDateTime(String date) {
        if (date == null) {
            return null;
        }
        try {
            int index = date.indexOf('.');
            int day = Integer.parseInt(date.substring(0, index));
            date = date.substring(index + 1);

            index = date.indexOf('.');
            int month = Integer.parseInt(date.substring(0, index));
            date = date.substring(index + 1);

            int year = Integer.parseInt(date);
            return OffsetDateTime.of(
                    year, month, day,
                    0, 0, 0, 0, OffsetDateTime.now().getOffset()
            );
        }catch (StringIndexOutOfBoundsException | NumberFormatException e){
            return null;
        }
    }

    public static String toDMYYYFormat(OffsetDateTime date) {
        if (date==null){
            return "";
        }
        return date.getDayOfMonth() + "." + date.getMonth().getValue() + "." + date.getYear();
    }

    public static boolean differentDay(OffsetDateTime firstDate, OffsetDateTime secondDate){
        return  firstDate.getYear()!=secondDate.getYear() &&
                firstDate.getDayOfYear()!=secondDate.getDayOfYear();
    }
}
