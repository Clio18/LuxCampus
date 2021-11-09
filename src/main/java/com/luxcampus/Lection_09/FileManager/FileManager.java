package com.luxcampus.Lection_09.FileManager;

import java.io.*;

public class FileManager {
    //    public static void main(String[] args) throws IOException {
//
//        File file = new File("/Users/antonobolonik/Downloads/TEST3");
//        System.out.println(file.listFiles().length);
//        deleteDir(file);
//
//    }
    // public static int countFiles(String path) - принимает путь к папке,
    // возвращает количество файлов в папке и всех подпапках по пути
    //each time when Finder creates folder (or somebody enters into folder) the new file .DS_Store created too, so every folder contains at least 1 file
    public static int countFiles(String path) {
        int counter = 0;
        File directoryPath = new File(path);
        File[] files = directoryPath.listFiles();
        for (File file : files) {
            if (!file.isDirectory()) {
                counter++;
            } else {
                counter = counter + countFiles(file.getPath());
            }
        }
        return counter;
    }

    // public static int countDirs(String path) - принимает путь к папке,
    // возвращает количество папок в папке и всех подпапках по пути
    public static int countDirs(String path) {
        int counter = 0;
        File directoryPath = new File(path);
        File[] files = directoryPath.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                counter++;
                counter = counter + countDirs(file.getPath());
            }
        }
        return counter;
    }

    // - метод по копированию папок и файлов.
    //Параметр from - путь к файлу или папке, параметр to - путь к папке куда будет производиться копирование.
    public static void copy(String from, String to) throws IOException {
        File fromFile = new File(from);
        File toFile = new File(to);
        if (fromFile.isDirectory()) {
            copyDir(fromFile, toFile);
        } else copyFile(fromFile, toFile);
    }

    //- метод по перемещению папок и файлов.
    //Параметр from - путь к файлу или папке, параметр to - путь к папке куда будет производиться копирование
    public static void move(String from, String to) throws IOException {
        copy(from, to);
        File path = new File(from);
        deleteFromDir(path);
        deleteDir(path.getPath());
    }

    public static void moveFile(String from, String to) throws IOException {
        copy(from, to);
        File path = new File(from);
        deleteFromDir(path);
    }

    public static void deleteFromDir(File dir) {
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                deleteFromDir(file);
                file.delete();
            } else file.delete();
        }
    }

    public static void deleteDir(String path) {
        File dir = new File(path);
        if (dir.listFiles().length > 0) {
            deleteFromDir(dir);
        } else dir.delete();
    }

    public static void copyFile(File from, File to) throws IOException {
        if (!to.exists()) {
            if (to.isDirectory()) {
                to = new File(to.getPath() + "/" + from.getName());
            }
            to.createNewFile();
        } else {
            if (to.isDirectory()) {
                to = new File(to.getPath() + "/" + from.getName());
            }
        }
        //read from file
        InputStream inputStream = new FileInputStream(from);
        //get all bytes
        byte[] bytes = inputStream.readAllBytes();
        //write destination (where we will write)
        OutputStream outputStream = new FileOutputStream(to);
        //what we will write
        outputStream.write(bytes);
        inputStream.close();
        outputStream.close();
    }

    public static void copyDir(File from, File to) throws IOException {
        if (!to.exists()) {
            to.mkdir();
        }
        to = new File(to.getPath());
        for (File file : from.listFiles()) {
            if (file.isFile()) {
                copyFile(file, new File(to.getPath() + "/" +
                        file.getName()));
            } else if (file.isDirectory()) {
                copyDir(file, new File(to.getPath() + "/" +
                        file.getName()));
            }
        }
    }

}
