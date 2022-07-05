class ProfileGuesser extends Guesser {

    private double[] scores = new double[Words.N_WORDS];

    public Word getGuess() {
        WordsProfile profile = new WordsProfile(validIndices);
        for (short i = 0; i < Words.N_WORDS; ++i)
            scores[i] = profile.scoreWord(Words.at(i));

        short highest = 0;
        for (short i = 1; i < Words.N_WORDS; ++i) {
            if (scores[i] > scores[highest]) {
                highest = i;
            }
        }
        return Words.at(highest);
    }
}