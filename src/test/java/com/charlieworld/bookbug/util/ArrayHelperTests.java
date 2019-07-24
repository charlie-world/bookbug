package com.charlieworld.bookbug.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ArrayHelperTests {

    @Test
    public void arrayToStringTest() {
        String[] str = new String[]{"foo", "bar", "baz"};
        ArrayList<String> input = new ArrayList<>(Arrays.asList(str));
        String expect = "foo,bar,baz";
        assertEquals(ArrayHelper.arrayToString(input), expect);
    }

    @Test
    public void stringToArrayTest() {
        String input = "foo,bar,baz";
        String[] str = new String[]{"foo", "bar", "baz"};
        ArrayList<String> expect = new ArrayList<>(Arrays.asList(str));
        assertEquals(ArrayHelper.stringToArray(input), expect);
    }
}
