class Clue {
    private static final byte INDEX = 0, LETTER = 1;

    private byte[][] correct = new byte[Word.LENGTH][2]; // index, letter
    private byte[][] misplaced = new byte[Word.LENGTH][2];
    private byte[] countMin = new byte[Word.N_LETTERS];
    private byte[] countMax = new byte[Word.N_LETTERS];

    private byte[] lettersWithMax = new byte[Word.LENGTH];
    private byte nCorrect = 0, nMisplaced = 0, nLettersWithMax = 0;

    private byte[] changes = new byte[Word.LENGTH];
    private byte nChanges = 0;

    public Clue() {
        // countMin initialized to 0
        for (byte i = 0; i < Word.N_LETTERS; ++i)
            countMax[i] = Word.LENGTH;
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

    private void resetComparison() {
        for (byte i = 0; i < nChanges; ++i)
            ++countMax[changes[i]];

        nChanges = 0;
    }

    public boolean isConsistentWith(Word word) {
        for (byte i = 0; i < nCorrect; ++i) {
            if (word.letterAt(correct[i][INDEX]) != correct[i][LETTER])
                return false;
        }
        for (byte i = 0; i < nMisplaced; ++i) {
            if (word.letterAt(misplaced[i][INDEX]) == misplaced[i][LETTER])
                return false;
        }
        for (byte i = 0; i < Word.LENGTH; ++i) {
            byte letter = word.letterAt(i);
            --countMax[letter];
            changes[nChanges++] = letter;
            if (countMax[letter] < countMin[letter]) {
                resetComparison();
                return false;
            }
        }
        resetComparison();
        return true;
    }

    @Override
    public String toString() {
        String[] chars = new String[Word.LENGTH];
        for (byte i = 0; i < Word.LENGTH; ++i)
            chars[i] = "_";

        for (byte i = 0; i < nCorrect; ++i)
            chars[correct[i][INDEX]] = "G";

        for (byte i = 0; i < nMisplaced; ++i)
            chars[misplaced[i][INDEX]] = "Y";

        return String.join(" ", chars);
    }
}