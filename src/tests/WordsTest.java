import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class WordsTest {

    Words words;
    List<String> wordList;
    Map<String,Double> mockInnerMap;

    @BeforeEach
    void init() throws FileNotFoundException {
        wordList = ProcessFile.initWordList("lib/corpora/Hamlet.txt");
        words = new Words();
        words.initWordMap(wordList);
        mockInnerMap = new HashMap();
        buildMockInnerMap();
    }

    @Test
    void initwords_emptyKeySet_returnFalse() {
        assertFalse(words.getKeySet().isEmpty());
    }

    @Test
    void initwords_emptyInnerKeySet_returnFalse() {
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
        words.setRelativeFrequencies();
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
        Words.relativeFrequencies(mockInnerMap);
        for(int i = 0; i <= 2; i++) {
            assertTrue(round(mockInnerMap.get(keys[i])) == values[i]);
        }
    }

    @Test
    void nextWord_correctWord_returnTrue() {
        String[] keys = {"one","two","three"};
        double[] values = {0,0.1,0.15,0.25,0.35,0.50,0.75,0.80,0.95};
        Words.relativeFrequencies(mockInnerMap);
        for (int i = 0; i <= 2; i++) {
            assertTrue(words.getNextWord(mockInnerMap,values[i]).equals(keys[i/3]));
        }
    }

    @Test
    void totalCount_countEquals6_returnTrue() {
        assertTrue(Words.totalCount(mockInnerMap) == 6);
    }

    private double round(double num) {
        num = (Math.round(num*100));
        return num/100;
    }

    private void buildMockInnerMap() {
        List<String> keys = Arrays.asList("one","two","three");
        List<Double> values = Arrays.asList(1.0,2.0,3.0);
        for (int i = 0; i <= 2; i++) {
            mockInnerMap.put(keys.get(i),values.get(i));
        }
    }
}