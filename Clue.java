class Clue {
    public static final byte UNKNOWN = -1;

    private byte[] correct, misplaced;
    private byte[] countMin, countMax;

    public Clue() {
        correct = new byte[Word.LENGTH];
        misplaced = new byte[Word.LENGTH];
        for (byte i = 0; i < Word.LENGTH; ++i) {
            correct[i] = UNKNOWN;
            misplaced[i] = UNKNOWN;
        }

        countMin = new byte[Word.N_LETTERS]; // initialized to 0
        countMax = new byte[Word.N_LETTERS];
        for (byte i = 0; i < Word.N_LETTERS; ++i)
            countMax[i] = Word.LENGTH;
    }

    public void addCorrectLetter(byte index, byte letter) {
        correct[index] = letter;
        ++countMin[letter];
    }

    public boolean isCorrectAt(byte index) {
        return correct[index] != UNKNOWN;
    }

    public void addMisplacedLetter(byte index, byte letter) {
        misplaced[index] = letter;
        ++countMin[letter];
    }

    public void setCountMax(byte letter) {
        countMax[letter] = countMin[letter];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (byte i = 0; i < Word.LENGTH; ++i) {
            if (i > 0)
                sb.append(' ');

            if (correct[i] != UNKNOWN)
                sb.append('G');
            else if (misplaced[i] != UNKNOWN)
                sb.append('Y');
            else
                sb.append('_');
        }
        return sb.toString();
    }
}