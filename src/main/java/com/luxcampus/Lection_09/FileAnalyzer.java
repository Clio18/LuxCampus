package com.luxcampus.Lection_09;

import com.luxcampus.Lection_07.ArrayList;
import com.luxcampus.Lection_07.List;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileAnalyzer {


    public static void main(String[] args) throws IOException {
        File path = new File(args[0]);
        String word = args[1];
        FileAnalyzer fileAnalyzer = new FileAnalyzer();

        fileAnalyzer.printGetCount(path, word);
        System.out.println();
        fileAnalyzer.printSentences(path, word);
    }

    //list of Objects....
    public List getListOfPatterns(File path) throws IOException {
        int[] delimiters = new int[]{'.', '!', '?'};
        InputStream inputStream = new FileInputStream(path);
        List listBytes = new ArrayList();
        List list = new ArrayList();
        int value;
        while (true) {
            value = inputStream.read();
            listBytes.add(value);
            if (value == delimiters[0] || value == delimiters[1] || value == delimiters[2]) {
                int[] array = new int[listBytes.size()];
                for (int i = 0; i < array.length; i++) {
                    array[i] = (int) listBytes.get(i);
                }
                list.add(new Pattern(array));
                listBytes = new ArrayList();
            }
            if (value == -1) {
                break;
            }
        }
        inputStream.close();
        return list;
    }

    //count of word appearance in one sentence
    public int getAppearance(Pattern pattern, String word) {
        byte[] bytes = pattern.getArray();
        byte[] wordBytes = word.getBytes(StandardCharsets.UTF_8);
        int counter = 0;
        int appearance = 0;
        int position = 0;
        for (int i = 0; i < bytes.length; i++) { //pattern
            if (wordBytes[position] == bytes[i]) {
                counter++;
                position++;
            }
            if (counter == wordBytes.length) {
                appearance++;
                counter = 0;
                position = 0;
            }
        }
        return appearance;
    }

    //count of word appearance in the text
    public int getCount(File path, String word) throws IOException {
        List list = getListOfPatterns(path);
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            Pattern pattern = (Pattern) list.get(i);
            count = count + getAppearance(pattern, word);
        }
        return count;
    }

    public void printGetCount(File path, String word) throws IOException {
        List list = getListOfPatterns(path);
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            Pattern pattern = (Pattern) list.get(i);
            count = count + getAppearance(pattern, word);
        }
        System.out.println(count);
    }

    //get list of sentences
    public List getSentences(File path, String word) throws IOException {
        List list = getListOfPatterns(path);
        List sentences = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            Pattern pattern = (Pattern) list.get(i);
            byte[] a = pattern.getArray();
            int count = getAppearance(pattern, word);
            if (count != 0) {
                sentences.add(new String(a));
            }
        }
        return sentences;
    }

    public void printSentences(File path, String word) throws IOException {
        List list = getListOfPatterns(path);
        for (int i = 0; i < list.size(); i++) {
            Pattern pattern = (Pattern) list.get(i);
            byte[] a = pattern.getArray();
            int count = getAppearance(pattern, word);
            if (count != 0) {
                System.out.println(new String(a));
            }
        }
    }
}

