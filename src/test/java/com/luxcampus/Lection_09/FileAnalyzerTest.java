package com.luxcampus.Lection_09;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileAnalyzerTest {
    FileAnalyzer analyzer = new FileAnalyzer();
    File path = new File("test.txt");
    String word = "Java";

    @Test
    @DisplayName("Test finds quantity of word appearance in the file")
    void testCheckAppearance() throws IOException {
        assertEquals(11, analyzer.getCount(path, word));
    }
    @Test
    @DisplayName("Test finds all sentences with word")
    void testFindsSentences() throws IOException {
        assertEquals(6, analyzer.getSentences(path, word).size());
    }
    @Test
    @DisplayName("Test get all sentences")
    void testFindsAllSentences() throws IOException {
        assertEquals(14, analyzer.getListOfPatterns(path).size());
    }
    @Test
    @DisplayName("Test get count of word in one sentence")
    void testFindsAInOneSentence() throws IOException {
        Pattern pattern = (Pattern) analyzer.getListOfPatterns(path).get(0);
        assertEquals(4, analyzer.getAppearance(pattern, word));
    }

}