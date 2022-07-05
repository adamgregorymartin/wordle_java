import java.util.Set;

class WordsProfile {

    private double[][] positions = new double[Word.LENGTH][Word.N_LETTERS];
    private double[][] occurances = new double[Word.N_LETTERS][Word.MAX_LETTER_COUNT];

    public WordsProfile(Set<Short> wordIndices) {
        for (short wordIndex : wordIndices) {
            Word word = Words.at(wordIndex);
            double weight = Words.weightOf(wordIndex);
            for (byte i = 0; i < Word.LENGTH; ++i) {
                byte letter = word.letterAt(i);
                positions[i][letter] += weight;
                occurances[letter][word.letterOccuranceAt(i)] += weight;
            }
        }
    }

    public double getLetterPositionWeight(byte position, byte letter) {
        /*
         * sum of weights of words with letter at position
         * 0 <= position < Word.LENGTH
         * 0 <= letter < Word.N_LETTERS
         */

        return positions[position][letter];
    }

    public double getLetterOccuranceWeight(byte letter, byte occurance) {
        /*
         * sum of weights of words with letter occurring at least occurance times
         * 0 <= letter < Word.N_LETTERS
         * 0 <= count < Word.LENGTH
         */

        return occurances[letter][occurance];
    }
}