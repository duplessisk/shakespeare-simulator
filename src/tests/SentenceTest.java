import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import static org.junit.jupiter.api.Assertions.*;

class SentenceTest {

    Sentence sen;
    String[] mockWords;
    ProcessFile pf;
    Random rand;
    List<String> wordList;
    Words w;

    @BeforeEach
    void init() throws FileNotFoundException {
        sen = new Sentence();
        mockWords = new String[]{"one", "two", "three", "four", "five", "six",
                "seven", "eight", "nine", "ten", "eleven"};
        pf = new ProcessFile();
        rand = new Random();
        wordList = pf.initWordList("lib/corpora/Hamlet.txt");
        w = new Words();
        w.initWordMap(wordList);
        w.setRelativeFrequencies();
    }

    void addWordsToSen(String[] mockWords) {
        for (String word : mockWords) {
            sen.addWord(word);
        }
    }

    @Test
    void addWord_correctNumWords_returnTrue() {
        addWordsToSen(mockWords);
        assertTrue(sen.getLen() == mockWords.length);
    }

    @Test
    void returnSen_stringMatchesMockWords_returnTrue() {
        addWordsToSen(mockWords);
        StringTokenizer tokens = new StringTokenizer(sen.toString());
        for (String word : mockWords) {
            String token = tokens.nextToken();
            assertTrue(word.equals(token));
        }
    }

    @Test
    void sentenceCorrectLen_correctLenObserved_returnTrue() {
        for (int i = 0; i < 1; i++) {
            sen.addWord(mockWords[i]);
            assertFalse(sen.correctLen());
        }
        for (int i = 1; i < 10; i++) {
            sen.addWord(mockWords[i]);
            assertTrue(sen.correctLen());
        }
        sen.addWord(mockWords[mockWords.length - 1]);
        assertFalse(sen.correctLen());
    }

    @Test
    void endSentence_onSpecialCharacter_returnTrue() {
        String[] specialWords = {"Hamlet.","Hamlet!","Hamlet?"};
        for (int i = 0; i <= 2; i++) {
            assertTrue(sen.endSentence(specialWords[i]));
        }
        assertFalse(sen.endSentence("Hamlet"));
    }

    @Test
    void buildSentence_infiniteLoop_returnFalse() {
        for (String rootWord : w.getKeySet()) {
            assertTimeoutPreemptively(Duration.ofMillis(3000), () -> {
                sen.buildSentence(w,rootWord,rand);
                sen.clear();
            });
        }
    }

    @Test
    void buildSentence_nullPointer_returnFalse() {
        for (int i = 0; i < 1000; i++) {
            assertTimeoutPreemptively(Duration.ofMillis(3000), () -> {
                sen.buildSentence(w,"hitherto",rand);
            });
        }
    }
}