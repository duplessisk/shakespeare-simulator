import java.io.*;
import java.util.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ProcessFile pf = new ProcessFile();
        Play play = new Play();
        List<String> wordList = pf.initWordList("lib/corpora/Hamlet.txt");
        play.initWordMap(wordList);
        play.setWeightedCounts();
        Scanner userInput = new Scanner(System.in);
        String rootWord;
        while (true) {
            rootWord = getUserInput(userInput);
            if (rootWord.equals("Q") || rootWord.equals("q")) {
                break;
            } else if (!wordList.contains(rootWord)) {
                System.out.println();
                System.out.println("Word doesn't exist in corpora");
            } else {
                String sentence = initSentences(play, rootWord);
                System.out.println();
                System.out.println(sentence);
            }
        }
    }

    public static String getUserInput (Scanner userInput) {
        System.out.println();
        System.out.print("Here is a possible list of root words: ");
        System.out.println("Hamlet, thy, devil, hitherto, behooves, thou, O,");
        System.out.print("Please select a root word (q to quit): ");
        return userInput.nextLine();
    }

    public static String initSentences(Play play, String rootWord) {
        if (rootWord.contains(".") || rootWord.contains("?") || rootWord.contains("!")) {
            return "Sentence can't be started with special character";
        } else {
            while (true) {
                String sentence = play.initSentence(rootWord);
                StringTokenizer sentenceTokens = new StringTokenizer(sentence);
                int wordCount = 0;
                while (sentenceTokens.hasMoreTokens()) {
                    sentenceTokens.nextToken();
                    wordCount += 1;
                }
                if (wordCount >= 2 && wordCount <= 10) {
                    return sentence;
                }
            }
        }
    }
}
