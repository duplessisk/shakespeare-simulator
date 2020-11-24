import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class PlayTest {

    Scanner playFile;
    Play play;

    @BeforeEach
    void init() throws FileNotFoundException {
        Scanner playFile = new Scanner(new File("lib/corpora/Hamlet.txt"));
        play = new Play(playFile);
    }

    @Test
    void initWordList_isEmpty_returnFalse() {
        assertFalse(play.initWordList(playFile).isEmpty());
    }

    @Test

}