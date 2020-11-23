import java.util.*;

public class HamWordBank {

    private List<String> hamWordList;

    /**
     * Constructs a HamWordBank object
     * @param inputFile - Reads hamlet.txt
     */
    public HamWordBank(Scanner inputFile) {
        hamWordList = initHamList(inputFile);
    }

    /**
     *
     * @param inputFile - Reads hamlet.txt
     * @return - list of all words in hamlet.txt
     */
    private List<String> initHamList(Scanner inputFile) {
        List<String> hamWordList = new ArrayList();
        while (inputFile.hasNext()) {
            hamWordList.add(inputFile.next());
        }
        return hamWordList;
    }

    /**
     * Returns the size of hamWordList
     * @return the size of hamWordList
     */
    public int getSize() {
        return hamWordList.size();
    }

    /**
     * Returns the word of interest
     * @param i - word index
     * @return word at specified index in hamWordList
     */
    public String getWord(int i) {
        return hamWordList.get(i);
    }

    /**
     * Returns the word directly following the word of interest in hamWordList
     * @param i - word index
     * @return word at specified index in hamWordList
     */
    public String getNextWord(int i) {
        return hamWordList.get(i + 1);
    }
}
