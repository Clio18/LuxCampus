package com.luxcampus.Lection_02;

import java.util.Arrays;

public class CrystalMethods {
    public static void main(String[] args) {

        System.out.println("#1: ");
        print(new char[]{'a', 'b'});
        System.out.println();


        char[] expectedCharArray = new char[]{'A', 'B', 'C'};
        char[] actualCharArray = toChars(new int[]{65, 66, 67});
        assertEqualsArray("#2", actualCharArray, expectedCharArray);

        int expectedMax = 99;
        int actualMax = max(9, 99);
        assertEquals("#3", actualMax, expectedMax);

        int expectedMaxOfThree = 1000;
        int actualMaxOfThree = max(9, 99, 1000);
        assertEquals("#4", actualMax, expectedMax);

        int expectedMaxOfFive = 1000;
        int actualMaxOfFive = max(9, 99, 1000, 1, 99);
        assertEquals("#5", actualMax, expectedMax);

        String expectedString = "ABX";
        String actualString = toString(new char[]{'A', 'B', 'X'});
        assertEquals("#6", actualString, expectedString);

        int expectedIndex = 1;
        int actualIndex = indexOf(new int[]{1, 2, 3, 4, 2, 6}, 2);
        assertEquals("#8", actualMax, expectedMax);

        int expectedIndexFromEnd = 5;
        int actualIndexFromEnd = indexFromEnd(new int[]{1, 2, 3, 4, 2, 6}, 6);
        assertEquals("#9", actualMax, expectedMax);


        long expectedFactorial = 6;
        long actualFactorial = factorial(3);
        assertEquals("#10", actualMax, expectedMax);

        boolean expectedAnswer = true;
        boolean actualAnswer = isHighYear(2100);
        assertEquals("#11", actualMax, expectedMax);

        System.out.println("#12: ");
        findDivided(new int[]{65, 66, 67, 68, 68, 69, 70, 71}, 2);
        System.out.println();
        int[] actualDivided = findDividedTest(new int[]{65, 66, 67, 68, 68, 69, 70, 71}, 2);
        int[] expectedDivided = new int[]{66, 68, 68, 70};
        assertEqualsArray("#12 ----------Test method: ", actualDivided, expectedDivided);


        System.out.println("#13: ");
        sortAsc(new int[]{5, 66, 67, 68, 9, 702, 71});
        System.out.println();
        int[] actualSorted = sortAscTest(new int[]{5, 66, 67, 68, 9, 702, 71});
        int[] expectedSorted = new int[]{5, 9, 66, 67, 68, 71, 702};
        assertEqualsArray("#13 ---------Test method: ", actualSorted, expectedSorted);

        boolean actualResult = ifContains(new byte[]{1, 2, 3, 4, 4});
        boolean expectedResult = true;
        assertEquals("#14", actualResult, expectedResult);

        int[] actualMultiArray = multiplication(new int[]{5, 6, 7}, new int[]{5, 6, 7});
        int[] expectedMultiArray = new int[]{25, 36, 49};
        assertEqualsArray("#15", actualMultiArray, expectedMultiArray);


        int[] concatenatedArray = concatenation(new int[]{5, 3, 7, 2, 5, 11}, new int[]{5, 6, 7, 9, 6, 22});
        int[] actualIntArray = unique(concatenatedArray);
        int[] expectedIntArray = new int[]{2, 3, 9, 11, 22};
        int[] actualAsc = sortAscTest(actualIntArray);
        assertEqualsArray("#16", actualAsc, expectedIntArray);

        int[] actualReversed = reverse(new int[]{1, 2, 3, 4});
        int[] expectedReversed = new int[]{4, 3, 2, 1};
        assertEqualsArray("#17", actualReversed, expectedReversed);

        int actualSize = create(5, 1, 10).length;
        int expectedSize = 5;
        assertEquals("----------#18 Length of array : ", actualSize, expectedIndexFromEnd);
        int actualMin = Arrays.stream(create(5, 1, 10)).min().getAsInt();
        int expectedMin = 0;
        assertEquals("----------#18 Minimum value in array : ", expectedMin < actualMin, true);
        int actualMaximum = Arrays.stream(create(5, 1, 10)).max().getAsInt();
        int expectedMaximum = 11;
        assertEquals("----------#18 Maximum value in array : ", expectedMaximum > actualMaximum, true);


        boolean actualIfContain = ifContains(new char[]{'a', 'b', 'x', 'n', 'o'}, new char[]{'j', 'j', 'j'});
        boolean expectedIfContain = false;
        assertEquals("#19", actualIfContain, expectedIfContain);


    }

    static void assertEquals(String testId, boolean actual, boolean expected) {
        if (expected == actual) {
            System.out.println("TEST " + testId + " PASSED");
        } else {
            System.out.println("TEST " + testId + " FAILED! Expected: " +
                    expected + " Actual: " + actual);
        }
    }

    static void assertEquals(String testId, int actual, int expected) {
        if (expected == actual) {
            System.out.println("TEST " + testId + " PASSED");
        } else {
            System.out.println("TEST " + testId + " FAILED! Expected: " +
                    expected + " Actual: " + actual);
        }
    }

    static void assertEquals(String testId, long actual, long expected) {
        if (expected == actual) {
            System.out.println("TEST " + testId + " PASSED");
        } else {
            System.out.println("TEST " + testId + " FAILED! Expected: " +
                    expected + " Actual: " + actual);
        }
    }

    static void assertEquals(String testId, String actual, String expected) {
        if (expected.equals(actual)) {
            System.out.println("TEST " + testId + " PASSED");
        } else {
            System.out.println("TEST " + testId + " FAILED! Expected: " +
                    expected + " Actual: " + actual);
        }
    }

    static void assertEqualsArray(String testId, long[] actual, long[] expected) {
        int count = 0;
        if (actual.length == expected.length) {
            for (int i = 0; i < actual.length; i++) {
                if (actual[i] == expected[i]) {
                    count++;
                }
            }
            if (count == actual.length) {
                System.out.println("TEST " + testId + " PASSED");
            } else {
                System.out.println("TEST " + testId + " FAILED!");
                System.out.print("Expected: ");
                System.out.println(Arrays.toString(expected));
                System.out.print("Actual: ");
                System.out.println(Arrays.toString(actual));
            }
        } else {
            System.out.println("TEST " + testId + " FAILED!");
            System.out.print("Expected: ");
            System.out.println(Arrays.toString(expected));
            System.out.print("Actual: ");
            System.out.println(Arrays.toString(actual));
        }
    }

    static void assertEqualsArray(String testId, char[] actual, char[] expected) {
        int count = 0;
        if (actual.length == expected.length) {
            for (int i = 0; i < actual.length; i++) {
                if (actual[i] == expected[i]) {
                    count++;
                }
            }
            if (count == actual.length) {
                System.out.println("TEST " + testId + " PASSED");
            } else {
                System.out.println("TEST " + testId + " FAILED!");
                System.out.print("Expected: ");
                System.out.println(Arrays.toString(expected));
                System.out.print("Actual: ");
                System.out.println(Arrays.toString(actual));
            }
        } else {
            System.out.println("TEST " + testId + " FAILED!");
            System.out.print("Expected: ");
            System.out.println(Arrays.toString(expected));
            System.out.print("Actual: ");
            System.out.println(Arrays.toString(actual));
        }
    }

    static void assertEqualsArray(String testId, int[] actual, int[] expected) {
        int count = 0;
        if (actual.length == expected.length) {
            for (int i = 0; i < actual.length; i++) {
                if (actual[i] == expected[i]) {
                    count++;
                }
            }
            if (count == actual.length) {
                System.out.println("TEST " + testId + " PASSED");
            } else {
                System.out.println("TEST " + testId + " FAILED!");
                System.out.print("Expected: ");
                System.out.println(Arrays.toString(expected));
                System.out.print("Actual: ");
                System.out.println(Arrays.toString(actual));
            }
        } else {
            System.out.println("TEST " + testId + " FAILED!");
            System.out.print("Expected: ");
            System.out.println(Arrays.toString(expected));
            System.out.print("Actual: ");
            System.out.println(Arrays.toString(actual));
        }
    }

    //1) принимает массив чаров, выводит его на экран
    static void print(char[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

    //2) принимает массив интов, возвращает массив чаров,
    // каждый символ в позиции массива соответствует коду символа передаваемого инта
    static char[] toChars(int[] array) {
        char[] arrayOfChar = new char[array.length];
        for (int i = 0; i < array.length; i++) {
            arrayOfChar[i] = (char) array[i];
        }
        return arrayOfChar;
    }

    //3) приминает 2 инта, а и б, возвращает большее их этих 2х чисел
    static int max(int a, int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    //4) принимает 3 инта, возвращает большее из них
    static int max(int a, int b, int c) {
        int temp = max(a, b);
        return max(temp, c);
    }

    //5) приминает 5 интов, возвращает большее из них
    static int max(int a, int b, int c, int d, int e) {
        int first = max(a, b, c);
        int second = max(d, e);
        return max(first, second);
    }

    //6) принимает массив чаров, возвращает строку состоящую из символов массива
    static String toString(char[] array) {
        String result = "";
        for (int i = 0; i < array.length; i++) {
            result = result + array[i];
        }
        return result;
    }

    //8) принимает массив интов, и значение типа инт,
    // возвращает индекс массива в котором значение совпадает с передаваемым,
    // начиная с начала массива. Если значения в массиве нет возвращает -1
    static int indexOf(int[] array, int value) {
        int result = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                result = i;
            }
        }
        return result;
    }

    //9) принимает массив интов, и значение типа инт,
    //* возвращает индекс массива в котором значение совпадает с передаваемым,
    //* начиная с конца массива. Если значения в массиве нет возвращает -1
    static int indexFromEnd(int[] array, int value) {
        int result = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                result = array.length - i - 1;
            }
        }
        return result;
    }

    //10) метод принимает инт, и возвращает факториал от заданого инта
    static long factorial(int n) {
        if (n <= 1) {
            return 1;
        } else
            return n * factorial(n - 1);
    }

    //11) принимает инт год, и возвращает тру если год высокосный
    static boolean isHighYear(int year) {
        boolean flag = false;
        if (year % 400 == 0) {
            flag = true;
            return flag;
        } else if (year % 100 == 0) {
            return flag;
        } else if (year % 4 == 0) {
            flag = true;
            return flag;
        } else {
            return flag;
        }
    }

    //12) приминает массив интов и число,
    // выводит на екран только елементы массива которые кратны этому числу
    static void findDivided(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] % value == 0) {
                System.out.print(array[i] + " ");
            }
        }
    }

    static int[] findDividedTest(int[] array, int value) {
        int counter = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % value == 0) {
                counter++;
            }
        }
        int[] result = new int[counter];
        int index = counter - 1;
        for (int i = array.length - 1; i > 0; i--) {
            if (array[i] % value == 0) {
                result[index] = array[i];
                index--;
            }
        }
        return result;
    }

    //13) метод принимает массив интов сортирует его по возрастанию
    static void sortAsc(int[] array) {
        // [xxxx.]
        for (int i = 0; i < array.length - 1; i++) {
            //[.xxxx]
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    //replace
                    int x = array[i];
                    array[i] = array[j];
                    array[j] = x;
                }
            }

        }
        for (int a = 0; a < array.length; a++) {
            System.out.print(array[a] + " ");
        }
    }

    static int[] sortAscTest(int[] array) {
        // [xxxx.]
        for (int i = 0; i < array.length - 1; i++) {
            //[.xxxx]
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    //replace
                    int x = array[i];
                    array[i] = array[j];
                    array[j] = x;
                }
            }
        }
        return array;
    }

    //14) принимает массив байт, если в массиве есть повторяющиеся елементы, возвращает тру
    static boolean ifContains(byte[] array) {
        boolean flag = false;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (i == j) {
                    continue;
                } else if (array[i] == array[j]) {
                    flag = true;
                }
            }
        }
        return flag;
    }

    //15) принимает два массива интов одинаковых по длинне,
    // возращает массив интов который состоит из перемноженных елементов входящих массивов
    static int[] multiplication(int[] a, int[] b) {
        int[] result = new int[a.length];
        if (a.length == b.length) {
            for (int i = 0; i < a.length; i++) {
                result[i] = a[i] * b[i];
            }
            return result;
        } else {
            return result;
        }
    }

    //16) принимает два массива интов, возвращает массив из елементов которые не совпадают в массивах
    /* concatenate two arrays */
    static int[] concatenation(int[] a, int[] b) {
        int length = a.length + b.length;
        int c[] = new int[length];
        int size = b.length - 1;
        for (int i = 0; i < length; i++) {
            if (i < a.length) {
                c[i] = a[i];
            } else {
                c[i] = b[size];
                size--;
            }
        }
        return c;

    }

    static int[] unique(int[] array) {
        //find the size of the resulting array
        int size = 0;
        int elementCounter = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i] == array[j]) {
                    elementCounter++;
                }
            }
            if (elementCounter == 1) {
                size++;
            }
            elementCounter = 0;
        }

        //find the array of unique elements
        int[] result = new int[size];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i] == array[j]) {
                    elementCounter++;
                }
            }
            if (elementCounter == 1) {
                result[--size] = array[i];
            }
            elementCounter = 0;
        }
        return result;
    }

    //17) принимает масив интов, возвращает его же но в реверсном порядке
    static int[] reverse(int[] a) {
        int[] result = new int[a.length];
        int index = 3;
        for (int i = a.length - 1; i >= 0; i--) {
            int in = index - i; // 3-3 3-2 3-1 3-0
            result[in] = a[i];
        }
        return result;
    }

    //18) принимает 3 инта:
    //- размер выходного массива
    //- нижняя граница
    //- верхняя граница
    //возвращает массив интов заданой длинный,
    //* который содержит случайные числа от нижней границы до верхней границы"
    static int[] create(int size, int bottom, int up) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = bottom + (int) (Math.random() * ((up - bottom) + 1));
        }
        return array;
    }

    //19) принимает 2 массива чаров,
    //* проверяет есть ли в 1 массиве,
    //* такая же последовательность символов которую представляет собой второй массив.
    //* Возвращает булеан
    static boolean ifContains(char[] subChars, char[] chars) {
        boolean flag;
        if (subChars.length > chars.length) {
            String first = toString(subChars);
            CharSequence charSequence = new StringBuffer(toString(chars));
            flag = first.contains(charSequence);
        } else {
            String first = toString(chars);
            CharSequence charSequence = new StringBuffer(toString(subChars));
            flag = first.contains(charSequence);
        }
        return flag;
    }


}
