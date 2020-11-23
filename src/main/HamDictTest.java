import org.junit.jupiter.api.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class HamDictTest {

    Scanner hamFile;
    HamWordBank hamWordList;
    HamDict hamWordDict;

    @BeforeEach
    void init() throws FileNotFoundException {
        hamFile = new Scanner(new File("lib/corpora/Hamlet.txt"));
        hamWordList = new HamWordBank(hamFile);
    }

    @Test
    void keySet_notEmpty_returnTrue() {
        hamWordDict = new HamDict();
        assertFalse(hamWordDict.keySet(hamWordList).isEmpty());
    }

    @Test
    void getNextWords_notEmpty_returnTrue() {
        hamWordDict = new HamDict();
        Set<String> keySet = hamWordDict.keySet(hamWordList);
        for (String key : keySet) {
            assertFalse(hamWordDict.getNextWords().isEmpty());
        }
    }

    @Test
    void getNextWords_noRedundantWords_returnTrue() {

    }
}