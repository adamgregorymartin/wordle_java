class IO {
    /*
     * This class wraps command line interface IO.
     */

    public void print(String string) {
        System.out.println(string);
    }

    public String requestWord() {
        while (true) {
            String word = System.console().readLine();
            word = word.toLowerCase().trim();
            boolean allLetters = true;
            for (int i = 0; i < word.length(); ++i) {
                if (!Character.isLetter(word.charAt(i))) {
                    allLetters = false;
                    break;
                }
            }
            if (allLetters && word.length() == 5) {
                return word;
            }
        }
    }
}