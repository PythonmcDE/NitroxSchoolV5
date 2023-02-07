package xyz.daarkii.school.common.utils;

import lombok.experimental.UtilityClass;

import java.util.Locale;

@UtilityClass
public class StringUtils {

    public String formatGems(double gems) {

        if(gems >= 1000000)
            return String.format(Locale.GERMANY, "%,.2f Mio", gems / 1000000);

        return format(gems);
    }

    public String format(double value) {
        return String.format(Locale.GERMANY, "%,.0f", value);
    }

}
