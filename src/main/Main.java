import java.io.*;
import java.util.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ProcessFile pf = new ProcessFile();
        Words w = new Words();
        Sentence s = new Sentence();
        List<String> wordList = pf.initWordList("lib/corpora/Hamlet.txt");
        w.initWordMap(wordList);
        w.setRelativeFrequencies();
        Scanner rootWordInput = new Scanner(System.in), continueInput = new Scanner(System.in);
        boolean keepSim = true;
        while (keepSim) {
            String rootWord = getUserInput(rootWordInput,w);
            System.out.println(returnSentence(rootWord,s,w));
            System.out.println();
            System.out.print("play again(y/n)? ");
            String p = continueInput.next();
            keepSim = p.equals("y");
        }
    }

    /**
     * Returns valid root word that user selects
     * @param userInput - Scanner object that will obtain user input
     * @param w - Words object
     * @return - valid root word
     */
    public static String getUserInput (Scanner userInput, Words w) {
        System.out.println();
        String rootWord = "";
        do {
            System.out.print("Here is a possible list of root words: ");
            System.out.println("Hamlet, thy, devil, hitherto, behooves, thou, O,");
            System.out.print("Please select a root word: ");
            rootWord = userInput.nextLine();
        } while (invalidUserInput(w,rootWord));
        return rootWord;
    }


    /**
     * Returns the a valid sentence beginning with the user's root word
     * @param rootWord - root word that user selected
     * @param s - sentence object
     * @param w - Words object
     * @return - sentence
     */
    public static String returnSentence(String rootWord, Sentence s, Words w) {
        System.out.println();
        Random rand = new Random();
        do {
            s.clear();
            s.buildSentence(w,rootWord,rand);
        } while(!s.correctLen());
        return s.toString();
    }

    /**
     * Returns whether or not the user's root word is valid
     * @param w - Words object
     * @param rootWord - root word that user selected
     * @return - whether or not the user's root word is valid
     */
    public static boolean invalidUserInput(Words w, String rootWord) {
        boolean input = false;
        if (rootWord.equals("Q") || rootWord.equals("q")) {
            System.exit(0);
        } else if (rootWord.contains(".") || rootWord.contains("?") || rootWord.contains("!")) {
            System.out.println();
            System.out.println("root word cannot contain a special character.");
            System.out.println();
            input = true;
        } else if (rootWord.contains(" ")) {
            System.out.println();
            System.out.println("root word must be a single word.");
            System.out.println();
            input = true;
        } else if (!w.getKeySet().contains(rootWord)) {
            System.out.println();
            System.out.println("root word not contained in corpora.");
            System.out.println();
            input = true;
        }
        return input;
    }
}
