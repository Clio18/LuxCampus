package com.luxcampus.Lection_09.FileManager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class FileManagerTest {
    FileManager fileManager;
    String from = "/Users/antonobolonik/Downloads/TEST";
    String notExist = "/Users/antonobolonik/Downloads/TEST1";
    String empty = "/Users/antonobolonik/Downloads/TEST3";

//    public static void main(String[] args) throws IOException {
//        //files - 6, folders - 3
//        File path = new File("/Users/antonobolonik/Downloads/TEST");
//        path.mkdir();
//        File path1 = new File("/Users/antonobolonik/Downloads/TEST/file1.txt");
//        path1.createNewFile();
//        File path2 = new File("/Users/antonobolonik/Downloads/TEST/file2.txt");
//        path2.createNewFile();
//        File path3 = new File("/Users/antonobolonik/Downloads/TEST/file3.txt");
//        path3.createNewFile();
//        File path4 = new File("/Users/antonobolonik/Downloads/TEST/INNER_TEST");
//        path4.mkdir();
//        File path5 = new File("/Users/antonobolonik/Downloads/TEST//INNER_TEST/file4.txt");
//        path5.createNewFile();
//        File path6 = new File("/Users/antonobolonik/Downloads/TEST//INNER_TEST/file5.txt");
//        path6.createNewFile();
//        File path7 = new File("/Users/antonobolonik/Downloads/TEST/INNER_TEST/INNER_INNER");
//        path7.mkdir();
//        File path8 = new File("/Users/antonobolonik/Downloads/TEST/INNER_TEST/INNER_INNER/file6.txt");
//        path8.createNewFile();
//
//        //empty dir
//        File path9 = new File("/Users/antonobolonik/Downloads/TEST3");
//        path9.mkdir();
//        System.out.println("DATA STRUCTURE IS READY FOR TEST");
//    }

    @DisplayName("Test for count files")
    @Test
    void countFiles() {
        assertEquals(6, FileManager.countFiles(from));
    }

    @DisplayName("Test for count files on non existing folder")
    @Test
    void countFilesOnNonExisting() {
        assertThrows(NullPointerException.class, () -> {
            FileManager.countFiles(notExist);
        });
    }

    //should it be 0...
    @DisplayName("Test for count files in empty dir")
    @Test
    void countFilesInEmptyDir() {
        assertThrows(NullPointerException.class, () -> {
            FileManager.countFiles(empty);
        });
    }

    @DisplayName("Test for count dirs")
    @Test
    void countDirs() {
        assertEquals(2, FileManager.countDirs(from));
    }

    @DisplayName("Test for count dirss on non existing folder")
    @Test
    void countDirsOnNonExisting() {
        assertThrows(NullPointerException.class, () -> {
            FileManager.countDirs(notExist);
        });
    }

    @DisplayName("Test copy from file to dir")
    @Test
    void copyFromFileToDir() throws IOException {
        FileManager.copy("/Users/antonobolonik/Downloads/TEST/INNER_TEST/INNER_INNER/file6.txt", empty);
        File from = new File("/Users/antonobolonik/Downloads/TEST/INNER_TEST/INNER_INNER/file6.txt");
        File copy = new File(empty + "/" + from.getName());
        InputStream inputStream = new FileInputStream(from);
        byte[] fromBytes = inputStream.readAllBytes();
        InputStream inputStream2 = new FileInputStream(copy);
        byte[] copyBytes = inputStream2.readAllBytes();
        assertEquals(fromBytes.length, copyBytes.length);
        inputStream.close();
        inputStream2.close();
    }
    @DisplayName("Test copy from dir to dir")
    @Test
    void copyFromDirToDir() throws IOException {
        FileManager.copy("/Users/antonobolonik/Downloads/TEST/INNER_TEST/INNER_INNER", empty);
        assertEquals(FileManager.countFiles("/Users/antonobolonik/Downloads/TEST/INNER_TEST/INNER_INNER"),
                FileManager.countFiles(empty));
    }

    //hard to test...
    @Test
    void move() throws IOException {
//        File from = new File("/Users/antonobolonik/Downloads/TEST/INNER_TEST/INNER_INNER");
//        File copy = new File(empty);
//        assertEquals(1, from.listFiles().length);
//       // assertEquals(0, copy.listFiles().length);
//
//        FileManager.move("/Users/antonobolonik/Downloads/TEST/INNER_TEST/INNER_INNER", empty);
//        assertEquals(0, from.listFiles().length);
//        assertEquals(1, copy.listFiles().length);

    }

    @Test
    void deleteFromDir() {
    }

    @Test
    void copyFile() {
    }

    @Test
    void copyDir() {
    }
}