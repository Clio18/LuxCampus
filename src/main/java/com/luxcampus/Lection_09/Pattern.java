package com.luxcampus.Lection_09;

public class Pattern {
   private int [] array;

    public Pattern(int[] array) {
        this.array = array;
    }

    public byte [] getArray() {
        byte [] newArr = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            newArr[i] = (byte) array[i];
        }
        return newArr;
    }

    public void setArray(int[] array) {
        this.array = array;
    }
}
