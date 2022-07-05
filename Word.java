class Word implements Comparable<Word> {
    /*
     * Represents a Wordle word
     * Letters are stored as their lowercase index. ('a' -> 0)
     */

    // Class variables & methods

    public static final byte LENGTH = 5;
    public static final byte N_LETTERS = 26;
    public static final byte MAX_LETTER_COUNT = 3; // maximum times the same letter occurs in any word

    private static byte[] counter = new byte[N_LETTERS];

    public static boolean isValid(String string) {
        // string is trimmed and lowercase

        if (string.length() != LENGTH)
            return false;
        for (byte i = 0; i < LENGTH; ++i) {
            if (!Character.isLetter(string.charAt(i)))
                return false;
        }
        return Words.contains(new Word(string));
    }

    // Instance variables & methods

    private byte[] letters = new byte[LENGTH];
    private byte[] occurances = new byte[LENGTH];
    /*
     * occurances[i] = occurence index of letter up to and including position i
     * e.g. "abcde" -> {0, 0, 0, 0, 0}; "aaaaa" -> {0, 1, 2, 3, 4}
     */

    public Word(String string) {
        // isValid(string) == true

        for (byte i = 0; i < LENGTH; ++i) {
            byte letter = (byte) (string.charAt(i) - 'a');
            letters[i] = letter;
            occurances[i] = counter[letter];
            ++counter[letter];
        }

        for (byte i = 0; i < LENGTH; ++i)
            counter[letters[i]] = 0;
    }

    public byte letterAt(byte index) {
        // 0 <= index < LENGTH

        return letters[index];
    }

    public byte letterOccuranceAt(byte index) {
        // 0 <= index < LENGTH

        return occurances[index];
    }

    @Override
    public int compareTo(Word otherWord) {
        for (byte i = 0; i < LENGTH; ++i) {
            byte thisLetter = this.letters[i];
            byte otherLetter = otherWord.letters[i];
            if (thisLetter < otherLetter)
                return -1;
            else if (thisLetter > otherLetter)
                return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (byte i = 0; i < LENGTH; ++i) {
            if (i > 0)
                sb.append(' ');
            sb.append((char) (letters[i] + 'A'));
        }
        return sb.toString();
    }
}
