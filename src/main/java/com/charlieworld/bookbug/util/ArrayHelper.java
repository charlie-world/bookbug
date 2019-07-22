package com.charlieworld.bookbug.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayHelper {

    private static String SEPERATOR = ",";

    public static String arrayToString(List<String> arr) {
        return arr.toString().replace("[", "").replace("]", "").replaceAll(" ", "");
    }

    public static List<String> stringToArray(String str) {
        String[] splitedArr = str.split(SEPERATOR);
        return new ArrayList<>(Arrays.asList(splitedArr));
    }
}
