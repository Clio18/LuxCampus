package com.luxcampus.Lection_09.FileManager;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.*;

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


    @BeforeEach
    @Test
    void init() throws IOException {
        //files - 6, folders - 3
        File path = new File("/Users/antonobolonik/Downloads/TEST");
        path.mkdir();
        File path1 = new File("/Users/antonobolonik/Downloads/TEST/file1.txt");
        path1.createNewFile();
        File path2 = new File("/Users/antonobolonik/Downloads/TEST/file2.txt");
        path2.createNewFile();
        File path3 = new File("/Users/antonobolonik/Downloads/TEST/file3.txt");
        path3.createNewFile();
        File path4 = new File("/Users/antonobolonik/Downloads/TEST/INNER_TEST");
        path4.mkdir();
        File path5 = new File("/Users/antonobolonik/Downloads/TEST//INNER_TEST/file4.txt");
        path5.createNewFile();
        File path6 = new File("/Users/antonobolonik/Downloads/TEST//INNER_TEST/file5.txt");
        path6.createNewFile();
        File path7 = new File("/Users/antonobolonik/Downloads/TEST/INNER_TEST/INNER_INNER");
        path7.mkdir();
        File path8 = new File("/Users/antonobolonik/Downloads/TEST/INNER_TEST/INNER_INNER/file6.txt");
        path8.createNewFile();

        //empty dir
        File path9 = new File("/Users/antonobolonik/Downloads/TEST3");
        path9.mkdir();
    }

    @AfterEach
    @Test
    void destroy() throws IOException {

        File path8 = new File("/Users/antonobolonik/Downloads/TEST/INNER_TEST/INNER_INNER/file6.txt");
        path8.delete();
        File path7 = new File("/Users/antonobolonik/Downloads/TEST/INNER_TEST/INNER_INNER");
        path7.delete();

        File path5 = new File("/Users/antonobolonik/Downloads/TEST//INNER_TEST/file4.txt");
        path5.delete();
        File path6 = new File("/Users/antonobolonik/Downloads/TEST//INNER_TEST/file5.txt");
        path6.delete();
        File path4 = new File("/Users/antonobolonik/Downloads/TEST/INNER_TEST");
        path4.delete();

        File path1 = new File("/Users/antonobolonik/Downloads/TEST/file1.txt");
        path1.delete();
        File path2 = new File("/Users/antonobolonik/Downloads/TEST/file2.txt");
        path2.delete();
        File path3 = new File("/Users/antonobolonik/Downloads/TEST/file3.txt");
        path3.delete();
        File path = new File("/Users/antonobolonik/Downloads/TEST");
        path.delete();

        //empty dir
        File path9 = new File("/Users/antonobolonik/Downloads/TEST3");
        if (path9.list().length>0){
            File path10 = new File("/Users/antonobolonik/Downloads/TEST3/file6.txt");
            path10.delete();
            path9.delete();
        } else path9.delete();
    }

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

    @DisplayName("Test for count files in empty dir")
    @Test
    void countFilesInEmptyDir() {
        assertEquals(0, fileManager.countFiles(empty));
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
        //copy.delete();
        inputStream.close();
        inputStream2.close();
    }

    @DisplayName("Test for count files in dir after copy to")
    @Test
    void countFilesInEmptyDirAfterCopyTo() throws IOException {
        FileManager.copyFile(new File("/Users/antonobolonik/Downloads/TEST/INNER_TEST/INNER_INNER/file6.txt"), new File(empty));
        assertEquals(1, FileManager.countFiles(empty));
        FileManager.deleteFromDir(new File(empty));
        assertEquals(0, FileManager.countFiles(empty));

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
    void moveFile() throws IOException {
        File from = new File("/Users/antonobolonik/Downloads/TEST/INNER_TEST/INNER_INNER");
        File copy = new File(empty);

        //the folder empty is empty
        assertEquals(0, FileManager.countFiles(copy.getPath()));
        //the folder contains 1 element
        assertEquals(1, FileManager.countFiles(from.getPath()));

        FileManager.moveFile("/Users/antonobolonik/Downloads/TEST/INNER_TEST/INNER_INNER", empty);
        //the folder empty is not empty
        assertEquals(1, FileManager.countFiles(copy.getPath()));
        //the folder does not contain element
        assertEquals(0, FileManager.countFiles(from.getPath()));
    }

    @Test
    void moveDir() throws IOException {
        File from = new File("/Users/antonobolonik/Downloads/TEST/INNER_TEST/INNER_INNER");
        File copy = new File(empty);

        //the folder empty is empty
        assertEquals(0, FileManager.countFiles(copy.getPath()));
        //the folder contains 1 element
        assertEquals(1, FileManager.countFiles(from.getPath()));

        FileManager.moveFile("/Users/antonobolonik/Downloads/TEST/INNER_TEST/INNER_INNER", empty);
        //the folder empty is not empty
        assertEquals(1, FileManager.countFiles(copy.getPath()));
        //the folder does not contain element
        assertEquals(0, FileManager.countFiles(from.getPath()));
    }

    @Test
    void move() throws IOException {
        File from = new File("/Users/antonobolonik/Downloads/TEST");
        File copy = new File(empty);
        int count = FileManager.countFiles(from.getPath());

        FileManager.move(from.getPath(), empty);
        //the folder empty is not empty
        assertEquals(count, FileManager.countFiles(copy.getPath()));
    }

    @Test
    void deleteFromDir() {
        File from = new File("/Users/antonobolonik/Downloads/TEST/INNER_TEST/INNER_INNER");
        assertEquals(1, FileManager.countFiles(from.getPath()));
        FileManager.deleteFromDir(from);
        assertEquals(0, FileManager.countFiles(from.getPath()));

    }

    @Test
    void copyFile() throws IOException {
        File from = new File("/Users/antonobolonik/Downloads/TEST/INNER_TEST/INNER_INNER");
        File file = new File("/Users/antonobolonik/Downloads/TEST/INNER_TEST/INNER_INNER/file6.txt");
        File copy = new File(empty);

        //the folder empty is empty
        assertEquals(0, FileManager.countFiles(copy.getPath()));
        //the folder contains 1 element
        assertEquals(1, FileManager.countFiles(from.getPath()));

        FileManager.copyFile(file, copy);
        //the folder empty is not empty
        assertEquals(1, FileManager.countFiles(copy.getPath()));
        //the folder does not contain element
        assertEquals(1, FileManager.countFiles(from.getPath()));
    }

    @Test
    void copyDir() throws IOException {
        File from = new File("/Users/antonobolonik/Downloads/TEST");
        File copy = new File(empty);
        int count = FileManager.countFiles(from.getPath());

        FileManager.copyDir(from, copy);
        //the folder empty is not empty
        assertEquals(count, FileManager.countFiles(copy.getPath()));
    }
}