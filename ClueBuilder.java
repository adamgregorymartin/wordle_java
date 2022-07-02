class ClueBuilder {
    private byte[] solutionCount;
    private Word solution;

    public ClueBuilder() {
        solutionCount = new byte[Word.N_LETTERS]; // initialized to 0
    }

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

    public void buildClue(Word guess, Clue clue) {
        // solution != null

        // clue.reset()

        for (byte i = 0; i < Word.LENGTH; ++i) {
            byte letter = guess.letterAt(i);
            if (letter == solution.letterAt(i)) {
                clue.addCorrectLetter(i, letter);
                --solutionCount[letter];
            }
        }

        for (byte i = 0; i < Word.LENGTH; ++i) {
            if (!clue.isCorrectAt(i)) {
                byte letter = guess.letterAt(i);
                if (solutionCount[letter] > 0) {
                    clue.addMisplacedLetter(i, letter);
                    --solutionCount[letter];
                } else {
                    clue.setCountMax(letter);
                }
            }
        }

        // reset ClueBuilder
    }
}