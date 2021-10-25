package Lection_03;

import java.util.Arrays;

public class UTF8Encoder {
    public static void main(String[] args) {
        String actual = encode(66376); //for Hwair character
        String expected = "11110000 10010000 10001101 10001000";
        assertEquals(actual, expected);

        String actual2 = encode('€');
        String expected2 = "11100010 10000010 10101100";
        assertEquals(actual2, expected2);

        System.out.println(encodeString("¢Hello€"));

        byte[][] a = encodeStringToByte("¢Hello€");
        for (byte[] c : a) {
            System.out.print(Arrays.toString(c));
        }
    }

    static void assertEquals(String actual, String expected) {
        if (expected.equals(actual)) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED! Expected: " +
                    expected + " Actual: " + actual);
        }
    }

    public static String toBinary(int valueOfChar) {
        String s = "";
        while (valueOfChar > 0) {
            s = s + valueOfChar % 2;
            valueOfChar = valueOfChar / 2;
        }
        return new StringBuilder(s).reverse().toString();
    }

    //encoding to bit mask
    public static String encodeString (String line){
        char [] array = line.toCharArray();
        String result = "";
        for (int i = 0; i < array.length; i++) {
            int value = array[i];
            result = result+encode(value) + " ";
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

    // encoding to byte
    public static byte[][] encodeStringToByte(String line) {
        char[] array = line.toCharArray();
        byte[][] result = new byte[array.length][4];
        for (int i = 0; i < array.length; i++) {
            int value = array[i];
            result[i] = encodeByte(value);
        }
        return result;
    }

    public static byte[] encodeByte(int value) {
        String res = toBinary(value);
        String newRes = "";
        byte[] resultByte4 = new byte[4];
        if (value >= 0 && value <= 127) {
            if (res.length() < 7) {
                String zero = "";
                int zeros = 7 - res.length();
                for (int i = 0; i < zeros; i++) {
                    zero = zero + 0;
                }
                newRes = zero + res;
                resultByte4 = encodeOneByte(newRes);
            } else {
                resultByte4 = encodeOneByte(res);
            }
        } else if (value >= 128 && value <= 2047) {
            if (res.length() < 11) {
                String zero = "";
                int zeros = 11 - res.length();
                for (int i = 0; i < zeros; i++) {
                    zero = zero + 0;
                }
                newRes = zero + res;
                resultByte4 = encodeTwoByte(newRes);
            } else {
                resultByte4 = encodeTwoByte(res);
            }
        } else if (value >= 2048 && value <= 65535) {
            if (res.length() < 16) {
                String zero = "";
                int zeros = 16 - res.length();
                for (int i = 0; i < zeros; i++) {
                    zero = zero + 0;
                }
                newRes = zero + res;
                resultByte4 = encodeThreeByte(newRes);
            } else {
                resultByte4 = encodeThreeByte(res);
            }
        } else if (value >= 65536 && value <= 1114111) {
            if (res.length() < 21) {
                String zero = "";
                int zeros = 21 - res.length();
                for (int i = 0; i < zeros; i++) {
                    zero = zero + 0;
                }
                newRes = zero + res;
                resultByte4 = encodeFourByte(newRes);
            } else {
                resultByte4 = encodeFourByte(res);
            }
        }
        return resultByte4;
    }

    public static byte[] encodeThreeByte(String value) {
        byte[] array = new byte[3];
        String firstBit = "1110" + value.substring(0, 4);
        String secondBit = "10" + value.substring(4, 10);
        String thirdBit = "10" + value.substring(10, 16);
        byte first = (byte) Integer.parseInt(firstBit, 2);
        byte second = (byte) Integer.parseInt(secondBit, 2);
        byte third = (byte) Integer.parseInt(thirdBit, 2);
        array[0] = first;
        array[1] = second;
        array[2] = third;
        return array;
    }

    public static byte[] encodeTwoByte(String value) {
        byte[] array = new byte[2];
        String firstBit = "110" + value.substring(0, 5);
        String secondBit = "10" + value.substring(5, 11);
        //return firstBit + " " + secondBit;
        byte first = (byte) Integer.parseInt(firstBit, 2);
        byte second = (byte) Integer.parseInt(secondBit, 2);
        array[0] = first;
        array[1] = second;
        return array;
    }

    public static byte[] encodeOneByte(String value) {
        byte[] array = new byte[1];
        String firstBit = "0" + value;
        byte first = (byte) Integer.parseInt(firstBit, 2);
        array[0] = first;
        return array;

        //return firstBit;
    }

    public static byte[] encodeFourByte(String value) {
        byte[] array = new byte[4];
        String firstBit = "11110" + value.substring(0, 3);
        String secondBit = "10" + value.substring(3, 9);
        String thirdBit = "10" + value.substring(9, 15);
        String fourthBit = "10" + value.substring(15, 21);
        //return firstBit + " " + secondBit + " " + thirdBit + " " + fourthBit;
        byte first = (byte) Integer.parseInt(firstBit, 2);
        byte second = (byte) Integer.parseInt(secondBit, 2);
        byte third = (byte) Integer.parseInt(thirdBit, 2);
        byte four = (byte) Integer.parseInt(fourthBit, 2);
        array[0] = first;
        array[1] = second;
        array[2] = third;
        array[3] = four;
        return array;
    }


}
