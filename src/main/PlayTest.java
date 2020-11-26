import org.junit.jupiter.api.*;

import java.io.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PlayTest {

    Scanner playFile;
    Play play;
    List<String> wordList;

    @BeforeEach
    void init() throws FileNotFoundException {
        playFile = new Scanner(new File("lib/corpora/Hamlet.txt"));
        play = new Play();
        wordList = play.initWordList(playFile);

    }

    @Test
    void initWordList_isEmpty_returnFalse() {
        assertFalse(wordList.isEmpty());
    }

    @Test
    void initWordDict_noKeys_returnFalse() {
        assertFalse(play.initWordDict(wordList).keySet().isEmpty());
    }

    @Test
    void initWordDict_emptyKeySet_returnFalse() {
        // create sample data set
        Map<String, Map<String,Integer>> wordDict = play.initWordDict(wordList);
        assertFalse(wordDict.keySet().isEmpty());
        for (String key : wordDict.keySet()) {
            assertFalse(wordDict.get(key).keySet().isEmpty());
        }
    }

    @Test
    void initWordDict_correctNumValues_returnTrue() {
        Map<String, Map<String,Integer>> wordDict = play.initWordDict(wordList);
        assertFalse(wordDict.keySet().isEmpty());
        int totalNumValues = 0;
        for (String key : wordDict.keySet()) {
            Map<String,Integer> subDict = wordDict.get(key);
            for (String subKey : subDict.keySet()) {
                totalNumValues += subDict.get(subKey);
            }
        }
        System.out.println(wordDict.get("'Tis").toString());
        System.out.println(wordDict.get("a").toString());
        assertFalse(totalNumValues == 0);
        assertTrue(totalNumValues == wordList.size() - 1);
    }

    @Test
    void nextWordsWeighted_maxSumIsOne_returnTrue() {
        Map<String, Map<String, Integer>> wordDict = play.initWordDict(wordList);
        Set<String> keySet = wordDict.keySet();
        assertFalse(wordDict.keySet().isEmpty());
        for (String outerKey : keySet) {
            Iterator<String> subKeysItr = wordDict.get(outerKey).keySet().iterator();
            assertTrue(wordDict.get(outerKey).get(subKeysItr.next()) == 0);
            while (subKeysItr.hasNext()) {
                String nextKey = subKeysItr.next();
                if (!subKeysItr.hasNext()) {
                    assertTrue(wordDict.get(outerKey).get(nextKey) == 1);
                }
            }
        }
    }
}