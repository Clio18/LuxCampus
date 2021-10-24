package Lection_03;

public class UTF8Encoder {
    public static void main(String[] args) {
        String actual = encode(66376); //for Hwair character
        String expected = "11110000 10010000 10001101 10001000";
        assertEquals(actual, expected);

        String actual2 = encode('€');
        String expected2 = "11100010 10000010 10101100";
        assertEquals(actual2, expected2);


        System.out.println(encodeString("¢Hello€"));


    }

    static void assertEquals(String actual, String expected) {
        if (expected.equals(actual)) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED! Expected: " +
                    expected + " Actual: " + actual);
        }
    }

    public static String encodeString (String line){
        char [] array = line.toCharArray();
        String result = "";
        for (int i = 0; i < array.length; i++) {
            int value = array[i];
            result = result+encode(value) + " :: ";
        }
        return result;
    }

    public static String encode(int value) {
        String res = toBinary(value);
        String newRes = "";
        String resultUTF8 = "";
        if (value >= 0 && value <= 127) {
            if (res.length() < 7) {
                String zero = "";
                int zeros = 7 - res.length();
                for (int i = 0; i < zeros; i++) {
                    zero = zero + 0;
                }
                newRes = zero + res;
                resultUTF8 = encodeOne(newRes);
            } else {
                resultUTF8 = encodeOne(res);
            }
        } else if (value >= 128 && value <= 2047) {
            if (res.length() < 11) {
                String zero = "";
                int zeros = 11 - res.length();
                for (int i = 0; i < zeros; i++) {
                    zero = zero + 0;
                }
                newRes = zero + res;
                resultUTF8 = encodeTwo(newRes);
            } else {
                resultUTF8 = encodeTwo(res);
            }
        } else if (value >= 2048 && value <= 65535) {
            if (res.length() < 16) {
                String zero = "";
                int zeros = 16 - res.length();
                for (int i = 0; i < zeros; i++) {
                    zero = zero + 0;
                }
                newRes = zero + res;
                resultUTF8 = encodeThree(newRes);
            } else {
                resultUTF8 = encodeThree(res);
            }
        } else if (value >= 65536 && value <= 1114111) {
            if (res.length() < 21) {
                String zero = "";
                int zeros = 21 - res.length();
                for (int i = 0; i < zeros; i++) {
                    zero = zero + 0;
                }
                newRes = zero + res;
                resultUTF8 = encodeFour(newRes);
            } else {
                resultUTF8 = encodeFour(res);
            }
        }
        return resultUTF8;
    }

    public static String toBinary(int valueOfChar) {
        String s = "";
        while (valueOfChar > 0) {
            s = s + valueOfChar % 2;
            valueOfChar = valueOfChar / 2;
        }
        return new StringBuilder(s).reverse().toString();
    }

    public static String encodeFour(String value) {
        String firstBit = "11110" + value.substring(0, 3);
        String secondBit = "10" + value.substring(3, 9);
        String thirdBit = "10" + value.substring(9, 15);
        String fourthBit = "10" + value.substring(15, 21);
        return firstBit + " " + secondBit + " " + thirdBit + " " + fourthBit;
    }

    public static String encodeThree(String value) {
        String firstBit = "1110" + value.substring(0, 4);
        String secondBit = "10" + value.substring(4, 10);
        String thirdBit = "10" + value.substring(10, 16);
        return firstBit + " " + secondBit + " " + thirdBit;
    }

    public static String encodeTwo(String value) {
        String firstBit = "110" + value.substring(0, 5);
        String secondBit = "10" + value.substring(5, 11);
        return firstBit + " " + secondBit;
    }

    public static String encodeOne(String value) {
        String firstBit = "0" + value;
        return firstBit;
    }


}
