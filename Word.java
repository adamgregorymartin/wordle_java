class Word implements Comparable<Word> {
    /*
     * Represents a Wordle word
     * Letters are stored as their lowercase index. ('a' -> 0)
     */

    // Class variables & methods

    public static final byte LENGTH = 5;
    public static final byte N_LETTERS = 26;
    public static final byte MAX_LETTER_COUNT = 3; // maximum times the same letter occurs in any word

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
    private byte[] counts = new byte[N_LETTERS];
    private byte[] occurrences = new byte[LENGTH];
    /*
     * occurrences = occurence index of letter up to and including current position
     * e.g. "abbcd" -> {0, 0, 1, 0, 0}
     */

    public Word(String string) {
        // isValid(string) == true

        for (byte i = 0; i < LENGTH; ++i) {
            byte letter = (byte) (string.charAt(i) - 'a');
            letters[i] = letter;
            occurrences[i] = counts[letter];
            ++counts[letter];
        }
    }

    public byte letterAt(byte index) {
        // 0 <= index < LENGTH

        return letters[index];
    }

    public byte countOf(byte letter) {
        // 0 <= index < N_LETTERS

        return counts[letter];
    }

    public byte letterOccurrenceAt(byte index) {
        // 0 <= index < LENGTH

        return occurrences[index];
    }

    @Override
    public int compareTo(Word otherWord) {
        // alphabetical comparison

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
