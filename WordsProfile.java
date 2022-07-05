import java.util.List;

class WordsProfile {

    private double[][] positions = new double[Word.LENGTH][Word.N_LETTERS];
    private double[][] occurrences = new double[Word.N_LETTERS][Word.MAX_LETTER_COUNT];

    public WordsProfile(List<Short> wordIndices) {
        for (short wordIndex : wordIndices) {
            Word word = Words.at(wordIndex);
            double weight = Words.weightOf(wordIndex);
            for (byte i = 0; i < Word.LENGTH; ++i) {
                byte letter = word.letterAt(i);
                positions[i][letter] += weight;
                occurrences[letter][word.letterOccurrenceAt(i)] += weight;
            }
        }
    }

    public double scoreWord(Word word) {
        final double POSITION_WEIGHT = 1.0;
        final double OCCURRENCE_WEIGHT = 1.0;

        double score = 0.0;
        for (byte i = 0; i < Word.LENGTH; ++i) {
            byte letter = word.letterAt(i);
            score += POSITION_WEIGHT * positions[i][letter];
            score += OCCURRENCE_WEIGHT * occurrences[letter][word.letterOccurrenceAt(i)];
        }
        return score;
    }
}