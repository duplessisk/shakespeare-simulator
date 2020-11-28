import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ProcessFileTest {

    ProcessFile pf;
    List<String> badSymbols;
    List<String> wordList;

    @BeforeAll
    void init() {
        pf = new ProcessFile();
        badSymbols = Arrays.asList("(");
    }

    @Test
    void initWordList_empty_returnFalse() throws FileNotFoundException {
        assertFalse(pf.initWordList("lib/corpora/Hamlet.txt").isEmpty());
    }

    @Test
    void initWordList_containsBadWords_returnFalse() throws FileNotFoundException {
        wordList = pf.initWordList("lib/corpora/Hamlet.txt");
        for (String word : wordList) {
            for (String symbol : badSymbols) {
                assertFalse(word.contains(symbol));
            }
        }
    }

}