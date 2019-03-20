package ru.vsu.noidle_server.utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vsu.noidle_server.Constants;

//import javax.validation.constraints.Null;
import java.time.OffsetDateTime;

public class TimeUtils {

    private static int SECOND = 1000;
    private static int MINUTE = 60 * SECOND;
    private static int HOUR = 60 * MINUTE;
    private static long DAY = 24 * HOUR;
    private static long MONTH = 31 * DAY;
    private static long YEAR = 12 * MONTH;

    @Contract("null -> null")
    public static String toPretty(@Nullable Long millis) {
        if (millis == null) {
            return null;
        }
        StringBuilder text = new StringBuilder();

        if (millis >=  YEAR) {
            text.append(millis / YEAR).append(" " + Constants.Y + " ");
            millis %= YEAR;
        }

        if (millis >=  MONTH) {
            text.append(millis / MONTH).append(" " + Constants.MON + " ");
            millis %= MONTH;
        }

        if (millis >=  DAY) {
            text.append(millis / DAY).append(" " + Constants.D + " ");
            millis %= DAY;
        }

        if (millis >= HOUR) {
            text.append(millis / HOUR).append(" " + Constants.H + " ");
            millis %= HOUR;
        }

        if (millis >= MINUTE) {
            text.append(millis / MINUTE).append(" " + Constants.MIN + " ");
            millis %= MINUTE;
        }

        if (text.toString().isEmpty()) {
            text.append(Constants.LESS_MIN);
        }
        return text.toString();
    }

    @Contract("null -> null")
    public static OffsetDateTime toOffsetDateTime(@Nullable String date) {
        if (date == null) {
            return null;
        }
        return doToOffsetDateTime(date);
    }

    @Nullable
    private static OffsetDateTime doToOffsetDateTime(@NotNull String date) {
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
        } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
            return null;
        }
    }

    @Contract("null -> true")
    public static boolean canParseToOffsetDateTimeOrNull(@Nullable String date) {
        return date == null || doToOffsetDateTime(date) != null;
    }

    @NotNull
    public static String toDMYYYFormat(@Nullable OffsetDateTime date) {
        if (date == null) {
            return "";
        }
        return date.getDayOfMonth() + "." + date.getMonth().getValue() + "." + date.getYear();
    }

    public static boolean differentDay(@NotNull OffsetDateTime firstDate, @NotNull OffsetDateTime secondDate) {
        return firstDate.getYear() != secondDate.getYear() &&
                firstDate.getDayOfYear() != secondDate.getDayOfYear();
    }
}
