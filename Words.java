import java.io.*;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

class Words {
    /*
     * Contains a list of valid wordle words
     * from https://github.com/tabatkins/wordle-list
     */

    private static final String PATH = "./words.csv";
    public static final short N_WORDS = 12947;

    private static Words singleton = new Words();

    private Word[] words = new Word[N_WORDS];
    private double[] weights = new double[N_WORDS];

    private Words() {
        try (Scanner scanner = new Scanner(new File(PATH))) {
            short nWords = 0;
            while (scanner.hasNext()) {
                words[nWords++] = new Word(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        for (short i = 0; i < N_WORDS; ++i)
            weights[i] = 1.0; // / N_WORDS; // replace with log normal probability
    }

    public static Set<Short> getAllIndices() {
        Set<Short> indices = new HashSet<>();
        for (short i = 0; i < N_WORDS; ++i)
            indices.add(i);
        
        return indices;
    }

    public static Word at(short index) {
        // 0 <= index < N_WORDS

        return singleton.words[index];
    }

    public static boolean contains(Word word) {
        // simple binary search

        int left = 0, right = N_WORDS - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = word.compareTo(Words.at((short) mid));
            if (comparison == 0)
                return true;
            else if (comparison < 0)
                right = mid - 1;
            else
                left = mid + 1;
        }
        return false;
    }

    public static double weightOf(short index) {
        // 0 <= index < N_WORDS

        return singleton.weights[index];
    }
}