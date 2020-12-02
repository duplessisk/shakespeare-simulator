import java.util.*;

public class Words {

    private Map<String, Map<String, Double>> wordMap;

    public Words() {
        wordMap = new HashMap();
    }

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

    public void setWeightedCounts() {
        for (String word : wordMap.keySet()) {
            weightedCounts(wordMap.get(word));
        }
    }

    public static void weightedCounts(Map<String,Double> innerMap) {
        int totalWordCount = totalCount(innerMap);
        double previousWeightedCount = 0;
        for (String innerKey : innerMap.keySet()) {
            double weightedCount = innerMap.get(innerKey)/totalWordCount + previousWeightedCount;
            innerMap.put(innerKey,weightedCount);
            previousWeightedCount = weightedCount;
        }
    }

    public static int totalCount(Map<String,Double> innerMap) {
        int totalWordCount = 0;
        for (String innerKey : innerMap.keySet()) {
            totalWordCount += innerMap.get(innerKey);
        }
        return totalWordCount;
    }

    public String nextWord(Map<String,Double> innerMap, double ran) {
        for (String innerKey : innerMap.keySet()) {
            if (ran <= innerMap.get(innerKey)) {
                return innerKey;
            }
        }
        return "ERROR";
    }

    public Set<String> getKeySet() {
        return wordMap.keySet();
    }

    public Map<String,Double> getKey(String key) {
        return wordMap.get(key);
    }
}
