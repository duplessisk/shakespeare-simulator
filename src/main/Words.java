import java.util.*;

/*
This defines a class of Words objects that maps words from the processed file
to the words that follow them.
 */
public class Words {

    private Map<String, Map<String, Double>> wordMap;

    /*
     * Instantiates a Words object
     */
    public Words() {
        wordMap = new HashMap();
    }

    /*
     * Returns a map containing a list of relevant words from input file and
     * the words they map to
     * @param wordList - list containing all relevant words from input file
     * @return - map containing list of relevant words from input file
     *           and the next words they map to
     */
    public Map<String, Map<String, Double>> initWordMap(List<String> wordList) {
        for (int i = 0; i < wordList.size() - 1; i++) {
            String word = wordList.get(i), nextWord = wordList.get(i + 1);
            if (wordMap.keySet().contains(word)) {
                Map<String, Double> innerMap = wordMap.get(word);
                if (wordMap.get(word).keySet().contains(nextWord)) {
                    innerMap.put(nextWord, innerMap.get(nextWord) + 1);
                } else {
                    innerMap.put(nextWord,1.0);
                }
            } else {
                wordMap.put(word,new HashMap());
                Map<String, Double> innerMap = wordMap.get(word);
                innerMap.put(nextWord,1.0);
            }
        }
        return wordMap;
    }

    /*
     * Sets the frequency of the words that follow all the words contained
     * within the word map.
     */
    public void setWeightedCounts() {
        for (String word : wordMap.keySet()) {
            weightedCounts(wordMap.get(word));
        }
    }

    /*
     * Calculates and sets the frequency for words that follow a specific word
     * contained within a word.
     * @param innerMap - map for a specific word containing a the next
     *                   possible words and their probabilities of being
     *                   selected next.
     */
    public static void weightedCounts(Map<String,Double> innerMap) {
        int totalWordCount = totalCount(innerMap);
        double previousWeightedCount = 0;
        for (String innerKey : innerMap.keySet()) {
            double weightedCount = innerMap.get(innerKey)/totalWordCount + previousWeightedCount;
            innerMap.put(innerKey,weightedCount);
            previousWeightedCount = weightedCount;
        }
    }

    /*
     * Retrieves the total count for the words the follow a specific word
     * @param innerMap - map for a specific word containing a the next
     *                   possible words and their probabilities of being
     *                   selected next.
     * @return - total number of words that follow a specific word
     */
    public static int totalCount(Map<String,Double> innerMap) {
        int totalWordCount = 0;
        for (String innerKey : innerMap.keySet()) {
            totalWordCount += innerMap.get(innerKey);
        }
        return totalWordCount;
    }

    /*
     * Selects next word by via its probability of being selected
     * @param innerMap - map for a specific word containing a the next
     *                   possible words and their probabilities of being
     *                   selected next.
     * @param ran - random double [0,1) used to select next word
     * @return - next word
     */
    public String getNextWord(Map<String,Double> innerMap, double ran) {
        for (String innerKey : innerMap.keySet()) {
            if (ran <= innerMap.get(innerKey)) {
                return innerKey;
            }
        }
        return "ERROR";
    }

    /*
     * Returns map key set
     * @return map key set
     */
    public Set<String> getKeySet() {
        return wordMap.keySet();
    }

    /*
     * Returns inner dict corresponding to key of interest
     * @param key - key of interest
     * @return inner dict ccorresponding to key of interest
     */
    public Map<String,Double> getKey(String key) {
        return wordMap.get(key);
    }
}
