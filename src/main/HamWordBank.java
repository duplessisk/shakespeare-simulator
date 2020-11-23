import java.util.*;

public class HamWordBank {

    private List<String> hamWordList;

    public HamWordBank(Scanner inputFile) {
        hamWordList = initHamList(inputFile);
    }

    private List<String> initHamList(Scanner inputFile) {
        List<String> hamWordList = new ArrayList();
        while (inputFile.hasNext()) {
            hamWordList.add(inputFile.next());
        }
        return hamWordList;
    }

    public int getSize() {
        return hamWordList.size();
    }

    public String getNextWord(int i) {
        return hamWordList.get(i + 1);
    }
}
