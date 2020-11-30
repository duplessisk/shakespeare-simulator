import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcessFile {

    List<String> wordList;

    public ProcessFile() {
        wordList = new ArrayList();
    }

    public List<String> initWordList(String fileName) throws FileNotFoundException {
        Scanner inputFile = new Scanner(new File(fileName));
        while (inputFile.hasNext()) {
            String word = inputFile.next();
            if (filterWords(word)) {
                wordList.add(word);
            }
        }
        return wordList;
    }

    public boolean filterWords(String word) {
        String r1 = ".*[\\(].*";
        String r2 = ".*[\\)].*";

        Pattern pt1 = Pattern.compile(r1);
        Pattern pt2 = Pattern.compile(r2);

        Matcher mt1 = pt1.matcher(word);
        Matcher mt2 = pt2.matcher(word);

        return !mt1.matches() && !mt2.matches();
    }
}
