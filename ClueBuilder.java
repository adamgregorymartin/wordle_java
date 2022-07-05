class ClueBuilder {
    /*
     * Configures a Clue object from solution and guess words.
     */

    private byte[] counts = new byte[Word.N_LETTERS];
    private Word solution;

    byte[] changes = new byte[Word.LENGTH];
    byte nChanges = 0;

    private void reset() {
        if (solution == null)
            return;

        for (byte i = 0; i < Word.LENGTH; ++i)
            counts[solution.letterAt(i)] = 0;

        solution = null;
    }

    public void init(Word solution) {
        reset();
        this.solution = solution;
        for (byte i = 0; i < Word.LENGTH; ++i) {
            byte letter = solution.letterAt(i);
            counts[letter] = solution.countOf(letter);
        }
    }

    public void buildClue(Clue clue, Word guess) {
        // solution != null

        clue.reset();

        for (byte i = 0; i < Word.LENGTH; ++i) {
            byte letter = guess.letterAt(i);
            if (letter == solution.letterAt(i)) {
                clue.addCorrectLetter(i, letter);
                --counts[letter];
                changes[nChanges++] = letter;
            }
        }

        for (byte i = 0; i < Word.LENGTH; ++i) {
            byte letter = guess.letterAt(i);
            if (letter != solution.letterAt(i)) {
                if (counts[letter] > 0) {
                    clue.addMisplacedLetter(i, letter);
                    --counts[letter];
                    changes[nChanges++] = letter;
                } else {
                    clue.setLetterMax(letter);
                }
            }
        }

        for (byte i = 0; i < nChanges; ++i) {
            ++counts[changes[i]];
        }
        nChanges = 0;
    }
}