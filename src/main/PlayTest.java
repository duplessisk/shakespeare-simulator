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
    void initWordMap_noKeys_returnFalse() {
        assertFalse(play.initWordMap(wordList).keySet().isEmpty());
    }

    @Test
    void initWordMap_emptyKeySet_returnFalse() {
        // create sample data set
        Map<String, Map<String,Double>> wordMap = play.initWordMap(wordList);
        assertFalse(wordMap.keySet().isEmpty());
        for (String key : wordMap.keySet()) {
            assertFalse(wordMap.get(key).keySet().isEmpty());
        }
    }

    @Test
    void initWordMap_correctNumValues_returnTrue() {
        Map<String, Map<String,Double>> wordMap = play.initWordMap(wordList);
        assertFalse(wordMap.keySet().isEmpty());
        int totalNumValues = 0;
        for (String key : wordMap.keySet()) {
            Map<String,Double> subMap = wordMap.get(key);
            for (String subKey : subMap.keySet()) {
                totalNumValues += subMap.get(subKey);
            }
        }
        assertFalse(totalNumValues == 0);
        assertTrue(totalNumValues == wordList.size() - 1);
    }

    @Test
    void totalCount_notZero_returnTrue() {
        Map<String, Map<String, Double>> wordMap = play.initWordMap(wordList);
        assertFalse(wordMap.keySet().isEmpty());
        for (String outerKey : wordMap.keySet()) {
            Map<String,Double> innerMap = wordMap.get(outerKey);
            assertFalse(play.totalCount(innerMap) == 0);
        }
    }

    @Test
    void nextWordsWeighted_weightsAreCorrect_returnTrue() {
        Map<String, Map<String, Double>> wordMap = play.initWordMap(wordList);
        play.setWeightedCounts();
        Set<String> keySet = wordMap.keySet();
        assertFalse(wordMap.keySet().isEmpty());
        for (String outerKey : keySet) {
            System.out.println(wordMap.get(outerKey).toString());
            Iterator<String> subKeysItr = wordMap.get(outerKey).keySet().iterator();
            while (subKeysItr.hasNext()) {
                String nextKey = subKeysItr.next();
                if (!subKeysItr.hasNext()) {
                    assertTrue((wordMap.get(outerKey).get(nextKey) > 0.999) &&
                            (wordMap.get(outerKey).get(nextKey) < 1.001));
                } else {
                    assertTrue(wordMap.get(outerKey).get(nextKey) < 1);
                }
            }
        }
    }

    @Test
    void initSentence_isEmpty_returnFalse() {
        for (int i = 0; i < 1000; i++) {
            List<String> sentence = play.initSentence();
            assertFalse(sentence.isEmpty());
        }
    }

    @Test
    void initSentence_noVoid_returnTrue() {
        for (int i = 0; i < 1000; i++) {
            List<String> sentence = play.initSentence();
            assertFalse(sentence.contains("ERROR"));
        }
    }
}