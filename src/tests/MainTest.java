import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    Play play;
    ProcessFile pf;
    List<String> wordList;


    @BeforeEach
    void init() throws FileNotFoundException {
        play = new Play();
        pf = new ProcessFile();
        wordList = pf.initWordList("lib/corpora/Hamlet.txt");
        play.initWordMap(wordList);
        play.setWeightedCounts();
    }

    @Test
    void initSentences_noInfiniteLoop_returnTrue() {
        assertTimeoutPreemptively(Duration.ofMillis(3000), () -> {
            Main.initSentences(play,"Hamlet");
        });
    }

    @Test
    void initSentences_infinteLoop_returnTrue() {
        for (int i = 0; i < wordList.size() - 1; i++) {
            String word = wordList.get(i);
            assertTimeoutPreemptively(Duration.ofMillis(3000), () -> {
                Main.initSentences(play,word);
            });
        }
    }

}