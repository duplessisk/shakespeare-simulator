import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ProcessFileTest {

    ProcessFile pf;
    List<String> badSymbols;
    List<String> wordList;

    @BeforeEach
    void init() {
        pf = new ProcessFile();
        badSymbols = Arrays.asList("(",")");
    }

    @Test
    void initWordList_empty_returnFalse() throws FileNotFoundException {
        assertFalse(pf.initWordList("lib/corpora/Hamlet.txt").isEmpty());
    }

    @Test
    void filterWords_testBadChars_returnFalse() {
        List<String> mockWordList = Arrays.asList("(For","For)","(For)","(",")");
        for (String word : mockWordList) {
            System.out.println(word);
            assertFalse(pf.filterWords(word));
        }
    }

    @Test
    void filterWords_testGoodChars_returnTrue() {
        List<String> mockWordList = Arrays.asList("For");
        for (String word : mockWordList) {
            assertTrue(pf.filterWords(word));
        }
    }

    @Test
    void initWordList_containsBadWords_returnFalse() throws FileNotFoundException {
        wordList = pf.initWordList("lib/corpora/Hamlet.txt");
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