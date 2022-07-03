class Word implements Comparable<Word> {
    /*
     * Represents a Wordle word
     * Letters are stored as their lowercase index. ('a' -> 0)
     */

    // Class variables & methods

    public final static byte LENGTH = 5;
    public final static byte N_LETTERS = 26;

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

    public Word(String string) {
        // isValid(string) == true

        for (byte i = 0; i < LENGTH; ++i)
            letters[i] = (byte) (string.charAt(i) - 'a');
    }

    public byte letterAt(byte index) {
        // 0 <= index < LENGTH

        return letters[index];
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
