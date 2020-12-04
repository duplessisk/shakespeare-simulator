import java.util.*;

/**
This defines a class of Words objects that keeps track of two items: words from
the processed file and frequency of the next words that directly follow them
 */
public class Words {

    private Map<String, Map<String, Double>> wordMap;

    /**
     * Instantiates a Words object
     */
    public Words() {
        wordMap = new HashMap();
    }

    /**
     * Returns a map containing a list of words from input file and the
     * frequency of the words that directly follow them
     * @param wordList - list containing all relevant words from input file
     * @return - map containing list of relevant words from input file
     *           and the next words that directly follow them
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

    /**
     * Calculates the relative frequency of each next word for the
     * specific word that proceeds it
     * @param innerMap - maps a word to the frequencies of its next words
     */
    public static void relativeFrequencies(Map<String,Double> innerMap) {
        int totalWordCount = totalCount(innerMap);
        double previousWeightedCount = 0;
        for (String innerKey : innerMap.keySet()) {
            double weightedCount = innerMap.get(innerKey)/totalWordCount +
                    previousWeightedCount;
            innerMap.put(innerKey,weightedCount);
            previousWeightedCount = weightedCount;
        }
    }

    /**
     * Sets tConverts the frequency of next words to a relative frequency
     */
    public void setRelativeFrequencies() {
        for (String word : wordMap.keySet()) {
            relativeFrequencies(wordMap.get(word));
        }
    }

    /**
     * Retrieves the total frequency for the words the follow a specific word
     * @param innerMap - maps a word to the frequencies of its next words
     * @return - total number of words that follow a specific word
     */
    public static int totalCount(Map<String,Double> innerMap) {
        int totalWordFreq = 0;
        for (String innerKey : innerMap.keySet()) {
            totalWordFreq += innerMap.get(innerKey);
        }
        return totalWordFreq;
    }

    /**
     * Selects next word by via its probability of being selected
     * @param innerMap - maps a word to the frequencies of its next words
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

    /**
     * Returns map key set
     * @return map key set
     */
    public Set<String> getKeySet() {
        return wordMap.keySet();
    }

    /**
     * Returns inner dict corresponding to key of interest
     * @param key - key of interest
     * @return inner dict corresponding to key of interest
     */
    public Map<String,Double> getKey(String key) {
        return wordMap.get(key);
    }
}
