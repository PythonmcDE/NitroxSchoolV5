package xyz.daarkii.school.common.utils;

import lombok.experimental.UtilityClass;

import java.util.Locale;
import java.util.Random;

@UtilityClass
public class StringUtils {

    private final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVW";
    private final String LOWER = "abcdefghijklmnopqrstuvw";
    private final String NUMBERS = "1234567890";

    public String generate(int length) {

        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < length; i++) {

            String category = getCategoryForId(new Random().nextInt(3));
            char categoryChar = category.charAt(new Random().nextInt(category.length()));

            builder.append(categoryChar);
        }

        return builder.toString();
    }

    public String formatGems(double gems) {

        if(gems >= 1000000)
            return String.format(Locale.GERMANY, "%,.2f Mio", gems / 1000000);

        return format(gems);
    }

    public String format(double value) {
        return String.format(Locale.GERMANY, "%,.0f", value);
    }

    private String getCategoryForId(int id) {
        return switch (id) {
            case 0 -> UPPER;
            case 1 -> LOWER;
            default -> NUMBERS;
        };
    }

}
