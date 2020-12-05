import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
This defines a class of Sentence objects that represents a sentence similar to
 one Shakespeare would have written.
 */
public class Sentence {

    private List<String> senList;

    /**
     * Initializes the sentence object
     */
    public Sentence() {
        senList = new LinkedList();
    }

    /**
     * Adds a word to the sentence
     * @param word - word to be added to the sentence
     */
    public void addWord(String word) {
        senList.add(word.toLowerCase());
    }

    /**
     * Returns whether or not the sentence is a valid length
     * @return - if sentence is a valid length
     */
    public boolean correctLen() {
        return senList.size() >= 2 && senList.size() <= 10;
    }

    /**
     * Determines when the end of a sentence has been reached
     * @param nextKey - next work to be added to the sentence
     * @return - whether or not the next word to be added will end the sentence
     */
    public boolean endSentence(String nextKey) {
        return nextKey.contains(".") || nextKey.contains("?") ||
                nextKey.contains("!");
    }

    /**
     * Creates a sentence
     * @param w - Words object
     * @param nextWord - next word to be added to the sentence
     * @param rand - random number used to find next word to be added to
     *               the sentence
     */
    public void buildSentence(Words w, String nextWord, Random rand) {
        addWord(nextWord);
        while (!endSentence(nextWord)) {
            nextWord = w.getNextWord(w.getKey(nextWord), rand.nextDouble());
            addWord(nextWord);
        }
    }

    /**
     * Returns the length of the sentence
     * @return - the length of the sentence
     */
    public int getLen() {
        return senList.size();
    }

    /**
     * Converts the sentence to a string that can be printed to the console
     * @return - a string representation of the sentence
     */
    public String toString() {
        String sen = "";
        Iterator<String> itr = senList.iterator();
        while (itr.hasNext()) {
            sen += itr.next() + " ";
        }
        return sen;
    }

    /**
     * Removes all the words from the sentence
     */
    public void clear() {
        senList.clear();
    }
}
