package pl.szop.andrzejshop.utils;

import java.text.DecimalFormat;

public class NumberUtils {

    public static String priceFormat(Double value){
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        return decimalFormat.format(value);
    }
}
