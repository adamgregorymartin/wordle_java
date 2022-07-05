class Clue {
    /*
     * Stores the information from a guess and feedback string
     */

    // class variables and methods

    private static final byte INDEX = 0, LETTER = 1;
    private static final char CORRECT = 'g', MISPLACED = 'y', INCORRECT = '_';

    public static boolean isValidFeedback(String feedback) {
        // feedback is lowercase and trimmed

        if (feedback.length() != Word.LENGTH)
            return false;

        for (byte i = 0; i < Word.LENGTH; ++i) {
            char c = feedback.charAt(i);
            if (c != INCORRECT && c != MISPLACED && c != CORRECT)
                return false;
        }
        return true;
    }

    // instance variables and methods

    private byte[][] correct = new byte[Word.LENGTH][2]; // index, letter
    private byte[][] misplaced = new byte[Word.LENGTH][2];
    private byte[] countMin = new byte[Word.N_LETTERS];
    private byte[] countMax = new byte[Word.N_LETTERS];

    private byte[] lettersWithMax = new byte[Word.LENGTH];
    private byte nCorrect = 0, nMisplaced = 0, nLettersWithMax = 0;

    public Clue() {
        // countMin initialized to 0
        for (byte i = 0; i < Word.N_LETTERS; ++i)
            countMax[i] = Word.LENGTH;
    }

    public void buildClue(Word guess, String feedback) {
        /*
         * Configures a clue object from a guess word and feedback string
         * isValidFeedback(feedback) == true
         */

        resetClue();

        for (byte i = 0; i < Word.LENGTH; ++i) {
            byte letter = guess.letterAt(i);
            if (feedback.charAt(i) == CORRECT)
                addCorrectLetter(i, letter);
            else if (feedback.charAt(i) == MISPLACED)
                addMisplacedLetter(i, letter);
            else
                setLetterMax(letter);
        }
    }

    public void resetClue() {
        for (byte i = 0; i < nCorrect; ++i)
            countMin[correct[i][LETTER]] = 0;

        for (byte i = 0; i < nMisplaced; ++i)
            countMin[misplaced[i][LETTER]] = 0;

        for (byte i = 0; i < nLettersWithMax; ++i)
            countMax[lettersWithMax[i]] = Word.LENGTH;

        nCorrect = 0;
        nMisplaced = 0;
        nLettersWithMax = 0;
    }

    public void addCorrectLetter(byte index, byte letter) {
        correct[nCorrect][INDEX] = index;
        correct[nCorrect][LETTER] = letter;
        ++nCorrect;
        ++countMin[letter];
    }

    public void addMisplacedLetter(byte index, byte letter) {
        misplaced[nMisplaced][INDEX] = index;
        misplaced[nMisplaced][LETTER] = letter;
        ++nMisplaced;
        ++countMin[letter];
    }

    public void setLetterMax(byte letter) {
        countMax[letter] = countMin[letter];
        lettersWithMax[nLettersWithMax++] = letter;
    }

    public boolean isCorrect() {
        return nCorrect == Word.LENGTH;
    }

    public boolean isConsistentWith(Word word) {
        for (byte i = 0; i < nCorrect; ++i) {
            if (word.letterAt(correct[i][INDEX]) != correct[i][LETTER])
                return false;
        }
        for (byte i = 0; i < nMisplaced; ++i) {
            byte letter = misplaced[i][LETTER];
            if (word.letterAt(misplaced[i][INDEX]) == letter || word.letterCount(letter) < countMin[letter])
                return false;
        }
        for (byte i = 0; i < nLettersWithMax; ++i) {
            if (word.letterCount(lettersWithMax[i]) > countMax[lettersWithMax[i]])
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String[] chars = new String[Word.LENGTH];
        for (byte i = 0; i < Word.LENGTH; ++i)
            chars[i] = Character.toString(INCORRECT);

        for (byte i = 0; i < nCorrect; ++i)
            chars[correct[i][INDEX]] = Character.toString(Character.toUpperCase(CORRECT));

        for (byte i = 0; i < nMisplaced; ++i)
            chars[misplaced[i][INDEX]] = Character.toString(Character.toUpperCase(MISPLACED));

        return String.join(" ", chars);
    }
}