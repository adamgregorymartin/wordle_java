import java.io.*;
import java.util.Scanner;

import javax.lang.model.util.ElementScanner14;

class Words {
    /*
     * Contains a list of valid wordle words
     * from https://github.com/tabatkins/wordle-list
     */

    private static final String PATH = "./words.csv";
    public static final short N_WORDS = 12947;

    private static Words singleton = new Words();

    private Word[] words = new Word[N_WORDS];

    private Words() {
        try (Scanner scanner = new Scanner(new File(PATH))) {
            short nWords = 0;
            while (scanner.hasNext()) {
                words[nWords++] = new Word(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
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
}