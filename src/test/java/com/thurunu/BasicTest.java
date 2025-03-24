package com.thurunu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BasicTest {

    Basics basicTest = new Basics();

    @Test
    @DisplayName("First number is less than the second")
    public void compare() {
        int value = basicTest.compare(2, 3);
        Assertions.assertEquals(-1, value);
    }

    @Test
    @DisplayName("First Number is equal to the second")
    public void compareEqual() {
        int value = basicTest.compare(2, 2);
        Assertions.assertEquals(0, value);
    }

    @Test
    @DisplayName("Array sorted")
    public void sortArray() {
        Basics basicTests = new Basics();
        int[] array = {5, 8, 3, 9, 1, 6};
        basicTests.sortArray(array);
        Assertions.assertArrayEquals(new int[]{1, 3, 5, 6, 8, 9}, array);
    }

    @Test
    @DisplayName("Reverse String")
    public void testReverseString() {
        String str = "Hello World";
        String reversed = "dlroW olleH";
        String result = basicTest.reverseString(str);
        Assertions.assertEquals(reversed, result);
    }

}
