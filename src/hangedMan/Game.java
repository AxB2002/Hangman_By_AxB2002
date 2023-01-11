package hangedMan;

import java.io.IOException;

public class Game {
    private final static int NUM_LETTERS = 27;
    private final static char[] ALPHABET = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    private Word word;
    private int attempts;
    private int initialAttempts;
    private boolean[] usedLetters = new boolean[NUM_LETTERS];

    public Game(String path) throws IOException {
        this.setWord(new Word(path));
        int numAttempts = Math.min(this.getWord().getWordLength(), NUM_LETTERS / 2);
        this.setAttempts(numAttempts);
        this.setInitialAttempts(numAttempts);
        this.initUsedLetters();
    }

    private Word getWord() {
        return this.word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    private int getAttempts() {
        return this.attempts;
    }

    private void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    private boolean[] getUsedLetters() {
        return this.usedLetters;
    }

    private void setUsedLetters(boolean[] usedLetters) {
        this.usedLetters = usedLetters;
    }

    public int getInitialAttempts() {
        return initialAttempts;
    }

    public void setInitialAttempts(int initialAttempts) {
        this.initialAttempts = initialAttempts;
    }

    private int getNumFailed() {
        return this.getInitialAttempts() - this.getAttempts();
    }

    private String getUsedLettersString() {
        String str = "";
        boolean insertComma = false;
        boolean[] used = this.getUsedLetters();
        for (int i = 0; i < NUM_LETTERS; i++)
            if (used[i]) {
                if (!insertComma) insertComma = true;
                else str += ',';
                str += ALPHABET[i];
            }
        return str;
    }

    private void initUsedLetters() {
        boolean[] bool = new boolean[NUM_LETTERS];
        for (int i = 0; i < NUM_LETTERS; i++)
            bool[i] = false;
        this.setUsedLetters(bool);
    }

    private boolean invalidLetter(char letter) {
        boolean[] used = this.getUsedLetters();
        for (int i = 0; i < NUM_LETTERS; i++) {
            if (ALPHABET[i] == letter)
                return used[i];
        }
        return false;
    }

    private void addUsedLetter(char letter) {
        boolean[] used = this.getUsedLetters();
        for (int i = 0; i < NUM_LETTERS; i++)
            if (ALPHABET[i] == letter)
                used[i] = true;
        this.setUsedLetters(used);
    }

    public void setDifficulty() {
        TerminalPrinter.clearLines(4);
        TerminalPrinter.println("====== Difficult ======\n" +
                "\t1- Easy\n" +
                "\t2- Medium (Default))\n" +
                "\t3- Difficult\n" +
                "\t4- HARDCORE\n");
        int select = TerminalReader.readInt("Select the difficulty (1-4): ");
        int numAttempts = 0;
        switch (select) {
            case 1:
                numAttempts = this.getWord().numDifferentLetters() + 5;
                break;
            // case 2 = default
            case 3:
                numAttempts = this.getWord().numDifferentLetters() / 2;
                break;
            case 4:
                numAttempts = 2;
                break;
            default:
                numAttempts = this.getWord().numDifferentLetters();
        }
        this.setInitialAttempts(numAttempts);
        this.setAttempts(numAttempts);
    }

    public void start() {
        TerminalPrinter.clearLines(4);
        TerminalPrinter.printTitle("HANGMAN");

        do {
            TerminalPrinter.println("\n\tCurrent status: "
                    + this.getWord().getActualStateString() + "\t\tRemaining attempts: " + this.getAttempts());
            TerminalPrinter.println("\tLetters used: " + this.getUsedLettersString());

            char letter;
            do {
                letter = TerminalReader.readChar("\t\tEnter a letter: ");
            } while (invalidLetter(letter));

            this.addUsedLetter(letter);
            boolean wrongLetter = !this.getWord().addLetter(letter);
            if (wrongLetter) {
                this.setAttempts(this.getAttempts() - 1);
                HangedManPrinter.printHangedMan((double) this.getNumFailed() / this.getInitialAttempts());
            }
        } while (!this.getWord().isFinished() && this.getAttempts() > 0);

        if (this.getWord().isFinished())
            TerminalPrinter.showWin(this.getWord().getSelectedWord(), this.getAttempts(), this.getUsedLettersString());
        else
            TerminalPrinter.showGameOver(this.getWord().getSelectedWord(),
                    this.getWord().getActualStateString(), this.getUsedLettersString());
    }
}


