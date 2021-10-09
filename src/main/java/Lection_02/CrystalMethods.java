package Lection_02;

import java.util.Arrays;

public class CrystalMethods {
    public static void main(String[] args) {

        printArray(new char[]{'a', 'b', 'x'});
        System.out.println();

        printArray(fromIntToChar(new int[]{65, 66, 67}));
        System.out.println();

        System.out.println(biggerBetweenTwo(99, 99));

        System.out.println(biggerBetweenThree(9, 5, 9));

        System.out.println(biggerBetweenFive(9, 5, 9, 1, 11));

        System.out.println(stringMaker(new char[]{'a', 'b', 'x'}));

        System.out.println(findIndex(new int[]{65, 66, 67}, 67));

        System.out.println(findIndexFromEnd(new int[]{65, 66, 67, 68, 69, 70, 71}, 65));

        System.out.println(factorial(32));

        System.out.println(isHighYear(2100));

        findDivided(new int[]{65, 66, 67, 68, 69, 70, 71}, 2);
        System.out.println();

        sortAsc(new int[]{5, 66, 67, 68, 9, 702, 71});
        System.out.println();

        System.out.println(Arrays.toString(multiplication(new int[]{5, 6, 7}, new int[]{5, 6, 7})));

        System.out.println(Arrays.toString(notSame(new int[]{5, 3, 7, 2, 5, 11}, new int[]{5, 6, 7, 9, 6, 22})));

        System.out.println(Arrays.toString(reverse(new int[]{1, 2, 3, 4})));

        System.out.println(Arrays.toString(create(5, 1, 10)));

        System.out.println(ifContains(new char[]{'a', 'b', 'x', 'n', 'o'}, new char[]{'j', 'j', 'j'}));

        System.out.println(ifContains(new byte[]{1, 2, 3, 4, 4}));

        System.out.println(Arrays.toString(notSame(new int[]{5, 3, 7, 2, 11, 13, 13}, new int[]{5, 6, 7, 9, 11, 11, 12, 13})));

    }

    //1) принимает массив чаров, выводит его на экран
    static void printArray(char[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

    //2) принимает массив интов, возвращает массив чаров,
    // каждый символ в позиции массива соответствует коду символа передаваемого инта
    static char[] fromIntToChar(int[] array) {
        char[] arrayOfChar = new char[array.length];
        for (int i = 0; i < array.length; i++) {
            arrayOfChar[i] = (char) array[i];
        }
        return arrayOfChar;
    }

    //3) приминает 2 инта, а и б, возвращает большее их этих 2х чисел
    static int biggerBetweenTwo(int a, int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    //4) принимает 3 инта, возвращает большее из них
    static int biggerBetweenThree(int a, int b, int c) {
        int temp = biggerBetweenTwo(a, b);
        return biggerBetweenTwo(temp, c);
    }

    //5) приминает 5 интов, возвращает большее из них
    static int biggerBetweenFive(int a, int b, int c, int d, int e) {
        int first = biggerBetweenThree(a, b, c);
        int second = biggerBetweenTwo(d, e);
        return biggerBetweenTwo(first, second);
    }

    //6) принимает массив чаров, возвращает строку состоящую из символов массива
    static String stringMaker(char[] array) {
        String result = "";
        for (int i = 0; i < array.length; i++) {
            result = result + array[i];
        }
        return result;
    }

    //8) принимает массив интов, и значение типа инт,
    // возвращает индекс массива в котором значение совпадает с передаваемым,
    // начиная с начала массива. Если значения в массиве нет возвращает -1
    static int findIndex(int[] array, int value) {
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
    static int findIndexFromEnd(int[] array, int value) {
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
    static int[] notSame(int[] a, int[] b) {
        int count = findSize(a, b);
        int[] same = findSame(count, a, b);
        int[] concat = concatArray(a, b);
        return returnUnique(same, concat);
    }
    /* find quantity of same items */
    static int findSize(int[] a, int[] b) {
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                if (a[i] == b[j]) {
                    count++;
                }
            }
        }
        return count;
    }
    /* return the array of the same items */
    static int[] findSame(int count, int[] a, int[] b) {
        int[] same = new int[count];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                if (a[i] == b[j]) {
                    same[--count] = a[i];
                }
            }
        }
        return same;
    }
    /* concatenate two arrays */
    static int[] concatArray(int[] a, int[] b) {
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
    /* for each item in the concat array we check how many times it do not  equal to the items in
    * same array: if counter equals the length of the same array
    * it means that this item is unique
    * so we have another counter which is incremented in this case
    * ut shows how many unique items we have in concat array  */
    static int[] returnUnique(int[] same, int[] concat) {
        int a = 0;
        int b = 0;
        for (int i = 0; i < concat.length; i++) {
            for (int j = 0; j < same.length; j++) {
                if (concat[i] != same[j]) {
                    a++;
                }
            }
            if (a == same.length) {
                b++;
            }
            a = 0;
        }
        int[] result = new int[b];
        for (int i = 0; i < concat.length; i++) {
            for (int j = 0; j < same.length; j++) {
                if (concat[i] != same[j]) {
                    a++;
                }
            }
            if (a == same.length) {
                result[--b] = concat[i];
            }
            a = 0;
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
    static boolean ifContains(char[] a, char[] b) {
        boolean flag;
        if (a.length > b.length) {
            String first = stringMaker(a);
            CharSequence charSequence = new StringBuffer(stringMaker(b));
            flag = first.contains(charSequence);
        } else {
            String first = stringMaker(b);
            CharSequence charSequence = new StringBuffer(stringMaker(a));
            flag = first.contains(charSequence);
        }
        return flag;
    }


}
