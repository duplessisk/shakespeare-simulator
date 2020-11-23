import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.*;

class HamWordBankTest {

    HamWordBank hamWordList;
    Scanner hamFile;

    @BeforeEach
    void init() throws FileNotFoundException {
        hamFile = new Scanner(new File("lib/corpora/Hamlet.txt"));
        hamWordList = new HamWordBank(hamFile);
    }

    @Test
    void getSize_isEmpty_ReturnFalse() throws FileNotFoundException {
        assertFalse(hamWordList.getSize() == 0);
    }

    @Test
    void getSize_isFull_ReturnTrue() throws FileNotFoundException {
        hamFile = new Scanner(new File("lib/corpora/Hamlet.txt"));
        int numWords = 0;
        while (hamFile.hasNext()) {
            hamFile.next();
            numWords += 1;
        }
        assertTrue(hamWordList.getSize() == numWords);
    }

    @Test
    void getNextWord_isNotNull_ReturnTrue() {
        for (int i = 0; i < hamWordList.getSize() - 1; i++) {
            assertFalse(hamWordList.getNextWord(i) == null);
        }
    }
}