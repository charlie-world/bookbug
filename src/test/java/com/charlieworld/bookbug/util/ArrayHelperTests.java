package com.charlieworld.bookbug.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayHelperTests {

    @Test
    public void arrayToStringTest() {
        String[] str = new String[]{"foo", "bar", "baz"};
        ArrayList<String> input = new ArrayList<String>(Arrays.asList(str));
        String expect = "foo,bar,baz";
        assert ArrayHelper.arrayToString(input).equals(expect);
    }

    @Test
    public void stringToArrayTest() {
        String input = "foo,bar,baz";
        String[] str = new String[]{"foo", "bar", "baz"};
        ArrayList<String> expect = new ArrayList<String>(Arrays.asList(str));
        assert ArrayHelper.stringToArray(input).equals(expect);
    }
}
