import java.io.*;
import java.util.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("master branch");
        Scanner hamlet = new Scanner(new File("lib/corpora/Hamlet.txt"));
        Play play = new Play();
        List<String> wordList = play.initWordList(hamlet);
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
            } else if (rootWord.equals("Q") || rootWord.equals("q")) {
                break;
            } else {
                initSentences(play, rootWord);
            }
        }
    }

    public static String getUserInput (Scanner userInput) {
        System.out.println();
        System.out.print("Here is a possible list of root words: ");
        System.out.println("Hamlet, thy, devil, hitherto, behooves");
        System.out.print("Please select a root word (q to quit): ");
        return userInput.nextLine();
    }

    public static void initSentences(Play play, String rootWord) {
        while (true) {
            String sentence = play.initSentence(rootWord);
            StringTokenizer sentenceTokens = new StringTokenizer(sentence);
            int wordCount = 0;
            while (sentenceTokens.hasMoreTokens()) {
                sentenceTokens.nextToken();
                wordCount += 1;
            }
            if (wordCount >= 3 && wordCount <= 10) {
                System.out.println();
                System.out.println(sentence);
                break;
            }
        }
    }
}
