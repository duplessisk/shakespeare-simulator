import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.StringTokenizer;

import static org.junit.jupiter.api.Assertions.*;

class SentenceTest {

    Sentence sen;
    String[] mockWords;

    @BeforeEach
    void init() {
        sen = new Sentence();
        mockWords = new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven"};
    }

    void addWords(String[] mockWords) {
        for (String word : mockWords) {
            sen.addWord(word);
        }
    }

    @Test
    void addWord_correctNumWords_returnTrue() {
        addWords(mockWords);
        assertTrue(sen.getLen() == mockWords.length);
    }

    @Test
    void returnSen_stringMatchesMockWords_returnTrue() {
        addWords(mockWords);
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
    void abort_senHasTooManyWords_returnTrue() {
        for (int i = 0; i < 10; i++) {
            sen.addWord(mockWords[i]);
            assertFalse(sen.abort());
        }
        sen.addWord(mockWords[mockWords.length - 1]);
        assertTrue(sen.abort());
    }
}