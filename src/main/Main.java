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
        boolean continueSim = true;
        String rootWord;
        do {
            rootWord = getUserInput(userInput);
            System.out.println();
            if (rootWord.equals("Q") || rootWord.equals("q")) {
                continueSim = false;
            } else if (!wordList.contains(rootWord)) {
                System.out.println("Word doesn't exist in corpora");
            } else {
                System.out.println(play.getSentence(rootWord));
            }
        } while (continueSim);
    }

    public static String getUserInput (Scanner userInput) {
        System.out.println();
        System.out.print("Here is a possible list of root words: ");
        System.out.println("Hamlet, thy, devil, hitherto, behooves, thou, O,");
        System.out.print("Please select a root word (q to quit): ");
        return userInput.nextLine();
    }
}
