package com.charlieworld.bookbug;

import com.charlieworld.bookbug.util.ArrayMapper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayMapperTests {

    @Test
    public void arrayToStringTest() {
        String[] str = new String[] {"foo", "bar", "baz"};
        ArrayList<String> input = new ArrayList<String>(Arrays.asList(str));
        String expect = "foo,bar,baz";
        assert ArrayMapper.arrayToString(input).equals(expect);
    }

    @Test
    public void stringToArrayTest() {
        String input = "foo,bar,baz";
        String[] str = new String[] {"foo", "bar", "baz"};
        ArrayList<String> expect = new ArrayList<String>(Arrays.asList(str));
        assert ArrayMapper.stringToArray(input).equals(expect);
    }
}
