import org.junit.jupiter.api.*;

import java.io.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PlayTest {

    Scanner playFile;
    Play play;
    List<String> mockWordList;

    @BeforeEach
    void init() throws FileNotFoundException {
        playFile = new Scanner(new File("lib/corpora/Hamlet.txt"));
        play = new Play();
        mockWordList = play.initWordList(playFile).subList(0,100);
    }

    @Test
    void initWordList_isEmpty_returnFalse() {
        assertFalse(mockWordList.isEmpty());
    }

    @Test
    void initWordDict_noKeys_returnFalse() {
        assertFalse(play.initWordDict(mockWordList).keySet().isEmpty());
    }

    @Test
    void initWordDict_emptyKeySet_returnFalse() {
        // create sample data set
        Map<String, Map<String,Integer>> mockWordDict = play.initWordDict(mockWordList);
        assertFalse(mockWordDict.keySet().isEmpty());
        for (String key : mockWordDict.keySet()) {
            assertFalse(mockWordDict.get(key).keySet().isEmpty());
        }
    }

    @Test
    void initWordDict_correctNumValues_returnTrue() {
        Map<String, Map<String,Integer>> mockWordDict = play.initWordDict(mockWordList);
        assertFalse(mockWordDict.keySet().isEmpty());
        int totalNumValues = 0;
        for (String key : mockWordDict.keySet()) {
            Map<String,Integer> subDict = mockWordDict.get(key);
            for (String subKey : subDict.keySet()) {
                totalNumValues += subDict.get(subKey);
            }
        }
        assertFalse(totalNumValues == 0);
        System.out.println("totalNumValues: " + totalNumValues);
        System.out.println("mockWordList size: " + (mockWordList.size() - 1));
        assertTrue(totalNumValues == mockWordList.size() - 1);
    }
}