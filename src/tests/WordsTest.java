import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class WordsTest {

    Words words;
    Map<String,Double> mockInnerMap;

    @BeforeEach
    void init() throws FileNotFoundException {
        List<String> wordList =
                ProcessFile.initWordList("lib/corpora/Hamlet.txt");
        words = new Words();
        words.initWordMap(wordList);
        mockInnerMap = new HashMap();
        buildMockInnerMap();
    }

    @Test
    void initwords_empty_returnFalse() {
        assertFalse(words.getKeySet().isEmpty());
    }

    @Test
    void initwords_emptyKeySet_returnFalse() {
        for (String key : words.getKeySet()) {
            assertFalse(words.getKey(key).keySet().isEmpty());
        }
    }

    @Test
    void initwords_correctNumValues_returnTrue() {
        int totalNumValues = 0;
        for (String key : words.getKeySet()) {
            Map<String,Double> subMap = words.getKey(key);
            for (String subKey : subMap.keySet()) {
                totalNumValues += subMap.get(subKey);
            }
        }
        assertTrue(totalNumValues == wordList.size() - 1);
    }

    @Test
    void setWeightedCounts_weightBoundariesAreCorrect_returnTrue() {
        words.setWeightedCounts();
        for (String outerKey : words.getKeySet()) {
            Iterator<String> subKeysItr = words.getKey(outerKey).keySet().iterator();
            Double previousKeyWeight = 0.0;
            while (subKeysItr.hasNext()) {
                String nextKey = subKeysItr.next();
                Double nextKeyWeight = words.getKey(outerKey).get(nextKey);
                assertTrue(previousKeyWeight < nextKeyWeight);
                if (!subKeysItr.hasNext()) {
                    assertTrue((nextKeyWeight > 0.999) &&
                            (nextKeyWeight < 1.001));
                } else {
                    previousKeyWeight = nextKeyWeight;
                }
            }
        }
    }

    @Test
    void setWeightedCounts_correctWeights_returnTrue() {
        String[] keys = {"one","two","three"};
        double[] values = {0.17,0.5,1.0};
        Words.weightedCounts(mockInnerMap);
        for(int i = 0; i <= 2; i++) {
            assertTrue(round(mockInnerMap.get(keys[i])) == values[i]);
        }
    }

    @Test
    void nextWord_correctWord_returnTrue() {
        String[] keys = {"one","two","three"};
        double[] values = {0,0.1,0.15,0.25,0.35,0.50,0.75,0.80,0.95};
        Words.weightedCounts(mockInnerMap);
        for (int i = 0; i <= 2; i++) {
            assertTrue(words.getNextWord(mockInnerMap,values[i]).equals(keys[i/3]));
        }
    }

    @Test
    void totalCount_countEquals6_returnTrue() {
        assertTrue(Words.totalCount(mockInnerMap) == 6);
    }

    @Test
    void nextWord_correctNextWord_returnTrue() {
    }

    private double round(double num) {
        num = (Math.round(num*100));
        return num/100;
    }

    private void buildMockInnerMap() {
        mockInnerMap.put("one",1.0);
        mockInnerMap.put("two",2.0);
        mockInnerMap.put("three",3.0);
    }
}