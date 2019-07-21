package com.charlieworld.bookbug.util;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayMapper {

    private static String SEPERATOR = ",";

    public static String arrayToString(ArrayList<String> arr) {
        return arr.toString().replace("[", "").replace("]", "").replaceAll(" ", "");
    }

    public static ArrayList<String> stringToArray(String str) {
        String[] splitedArr = str.split(SEPERATOR);
        return new ArrayList<String>(Arrays.asList(splitedArr));
    }
}
