package com.luxcampus.Lection_09.FileManager;
import org.junit.jupiter.api.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

class FileManagerIntegrationTest {
    FileManager fileManager;

    String base = "/Users/antonobolonik/Downloads/TEST";
    String notExist = "/Users/antonobolonik/Downloads/TEST1";
    String empty = "/Users/antonobolonik/Downloads/TEST3";
    //path to file which we want to copy
    String pathToFileForCopy = "/Users/antonobolonik/Downloads/TEST/INNER_TEST/INNER_INNER/file6.txt";
    //path to dir which we want to copy
    String pathToDirForCopy = "/Users/antonobolonik/Downloads/TEST/INNER_TEST/INNER_INNER";


    @BeforeEach
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
        path9.delete();
    }

    @DisplayName("Test for count files")
    @Test
    void countFiles() {
        assertEquals(6, FileManager.countFiles(base));
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
        assertEquals(2, FileManager.countDirs(base));
    }

    @DisplayName("Test for count dirs on non existing folder")
    @Test
    void countDirsOnNonExisting() {
        assertThrows(NullPointerException.class, () -> {
            FileManager.countDirs(notExist);
        });
    }

    @DisplayName("Test copy from file to dir")
    @Test
    void copyFromFileToDir() throws IOException {
        File from = new File(pathToFileForCopy);
        //write the message to the file6.txt
        String line = "Message";
        Files.write(Paths.get(pathToFileForCopy), line.getBytes(StandardCharsets.UTF_8));
        //copy file to the empty folder
        FileManager.copy(pathToFileForCopy, empty);

        File copy = new File(empty + "/" + from.getName());
        //read message from the file which was copied
        InputStream inputStream = new FileInputStream(copy);
        byte[] copyBytes = inputStream.readAllBytes();
        String expected = new String(copyBytes, StandardCharsets.UTF_8);

        assertEquals(line, expected);

        //delete this file from empty dir
        FileManager.deleteFromDir(new File(empty));

        inputStream.close();
    }

    @DisplayName("Test for count files in dir after copy to")
    @Test
    void countFilesInEmptyDirAfterCopyTo() throws IOException {
        FileManager.copyFile(new File(pathToFileForCopy), new File(empty));
        assertEquals(1, FileManager.countFiles(empty));
        FileManager.deleteFromDir(new File(empty));
        assertEquals(0, FileManager.countFiles(empty));
    }

    @DisplayName("Test copy from dir to dir")
    @Test
    void copyFromDirToDir() throws IOException {
        FileManager.copy(pathToDirForCopy, empty);
        assertEquals(FileManager.countFiles(pathToDirForCopy),
                FileManager.countFiles(empty));
    }

    @DisplayName("Test for move dir with files (all structure)")
    @Test
    void moveFileToDir() throws IOException {
        File copy = new File(empty);
        //the folder empty is empty
        assertEquals(0, FileManager.countFiles(copy.getPath()));
        FileManager.moveFile(pathToFileForCopy, empty);
        //the folder empty is not empty
        assertEquals(1, FileManager.countFiles(copy.getPath()));
        //back to given structure
        //empty folder contains 1 file, so it will be 0 element in the list of files
        FileManager.moveFile(copy.listFiles()[0].getPath(), pathToDirForCopy);
    }

    @DisplayName("Test for move dir with files")
    @Test
    void moveDirToDir() throws IOException {
        File from = new File(pathToDirForCopy);
        File copy = new File(empty);

        //the folder empty is empty
        assertEquals(0, FileManager.countFiles(copy.getPath()));
        //the folder contains 1 element
        assertEquals(1, FileManager.countFiles(from.getPath()));

        FileManager.moveDir(pathToDirForCopy, empty);
        //the folder empty is not empty
        assertEquals(1, FileManager.countFiles(copy.getPath()));
        //back to structure
        FileManager.moveDir(empty, pathToDirForCopy);
    }

    @DisplayName("Test for move structure")
    @Test
    void move() throws IOException {
        File from = new File("/Users/antonobolonik/Downloads/TEST");
        File copy = new File(empty);
        int count = FileManager.countFiles(from.getPath());

        FileManager.move(from.getPath(), empty);
        //the folder empty is not empty
        assertEquals(count, FileManager.countFiles(copy.getPath()));

        //back to structure
        FileManager.move(empty, from.getPath());
    }

    @DisplayName("Test for delete from dir")
    @Test
    void deleteFromDir() {
        File from = new File(pathToDirForCopy);
        assertEquals(1, FileManager.countFiles(from.getPath()));
        FileManager.deleteFromDir(from);
        assertEquals(0, FileManager.countFiles(from.getPath()));
    }

    @DisplayName("Test for copy file")
    @Test
    void copyFile() throws IOException {
        File from = new File(pathToDirForCopy);
        File file = new File(pathToFileForCopy);
        File to = new File(empty);

        //the folder empty is empty
        assertEquals(0, FileManager.countFiles(to.getPath()));
        //the folder contains 1 element
        assertEquals(1, FileManager.countFiles(from.getPath()));

        FileManager.copyFile(file, to);
        //the folder empty is not empty
        assertEquals(1, FileManager.countFiles(to.getPath()));
        //the folder does not contain element
        assertEquals(1, FileManager.countFiles(from.getPath()));
        //back to structure
        FileManager.deleteFromDir(to);
    }

    @DisplayName("Test for copy dir")
    @Test
    void copyDir() throws IOException {
        File from = new File(base);

        File copy = new File(empty);
        int count = FileManager.countFiles(from.getPath());

        FileManager.copyDir(from, copy);
        //the folder empty is not empty
        assertEquals(count, FileManager.countFiles(copy.getPath()));
        //back to structure
        FileManager.deleteFromDir(copy);
    }
}