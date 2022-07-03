class ClueBuilder {
    /*
     * Configures a Clue object from solution and guess words.
     */

    private byte[] solutionCount = new byte[Word.N_LETTERS];
    private Word solution;

    byte[] changes = new byte[Word.LENGTH];
    byte nChanges = 0;

    private void resetSolution() {
        if (solution == null)
            return;
        for (byte i = 0; i < Word.LENGTH; ++i)
            --solutionCount[solution.letterAt(i)];
        solution = null;
    }

    public void initSolution(Word solution) {
        resetSolution();
        this.solution = solution;
        for (byte i = 0; i < Word.LENGTH; ++i)
            ++solutionCount[solution.letterAt(i)];
    }

    public void buildClue(Clue clue, Word guess) {
        // solution != null

        clue.resetClue();

        for (byte i = 0; i < Word.LENGTH; ++i) {
            byte letter = guess.letterAt(i);
            if (letter == solution.letterAt(i)) {
                clue.addCorrectLetter(i, letter);
                --solutionCount[letter];
                changes[nChanges++] = letter;
            }
        }

        for (byte i = 0; i < Word.LENGTH; ++i) {
            byte letter = guess.letterAt(i);
            if (letter != solution.letterAt(i)) {
                if (solutionCount[letter] > 0) {
                    clue.addMisplacedLetter(i, letter);
                    --solutionCount[letter];
                    changes[nChanges++] = letter;
                } else {
                    clue.setLetterMax(letter);
                }
            }
        }

        for (byte i = 0; i < nChanges; ++i) {
            ++solutionCount[changes[i]];
        }
        nChanges = 0;
    }
}