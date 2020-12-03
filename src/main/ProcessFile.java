import java.io.*;
import java.util.*;
import java.util.regex.*;

/*
 This defines a class that reads in an input file and provides a list containing
 the input file's desired elements
 */
public class ProcessFile {
    /*
     * Reads in input file, returns list containing relevant contents
     * @param fileName - name of the file to be read in
     * @return - list containing the content of interest from the input file
     * @throws - FileNotFoundException if the file name doesn't exist
     */
    public static List<String> initWordList(String fileName) throws FileNotFoundException {
        List<String> wordList = new ArrayList();
        Scanner inputFile = new Scanner(new File(fileName));
        while (inputFile.hasNext()) {
            String word = inputFile.next();
            if (filterWords(word)) {
                wordList.add(word);
            }
        }
        return wordList;
    }

    /*
     * Determines if word from the input file should be included
     * @param word - word from input file
     * @return - whether or not the word should be included
     */
    public static boolean filterWords(String word) {
        String r1 = ".*[\\(].*";
        String r2 = ".*[\\)].*";

        Pattern pt1 = Pattern.compile(r1);
        Pattern pt2 = Pattern.compile(r2);

        Matcher mt1 = pt1.matcher(word);
        Matcher mt2 = pt2.matcher(word);

        return !mt1.matches() && !mt2.matches();
    }
}
