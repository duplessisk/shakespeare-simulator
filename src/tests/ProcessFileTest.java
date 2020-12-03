import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ProcessFileTest {

    List<String> badSymbols;

    @BeforeEach
    void init() {
        badSymbols = Arrays.asList("(",")");
    }

    @Test
    void initWordList_empty_returnFalse() throws FileNotFoundException {
        assertFalse(ProcessFile.initWordList("lib/corpora/Hamlet.txt").isEmpty());
    }

    @Test
    void filterWords_testBadChars_returnFalse() {
        List<String> mockWordList = Arrays.asList("(For","For)","(For)","(",")");
        for (String word : mockWordList) {
            assertFalse(ProcessFile.filterWords(word));
        }
    }

    @Test
    void filterWords_testGoodChars_returnTrue() {
        List<String> mockWordList = Arrays.asList("For");
        for (String word : mockWordList) {
            assertTrue(ProcessFile.filterWords(word));
        }
    }

    @Test
    void initWordList_containsBadWords_returnFalse() throws FileNotFoundException {
        List<String> wordList = ProcessFile.initWordList("lib/corpora/Hamlet.txt");
        for (String word : wordList) {
            for (String symbol : badSymbols) {
                if (word.contains(symbol)) {
                    System.out.println(symbol);
                }
                assertFalse(word.contains(symbol));
            }
        }
    }
}