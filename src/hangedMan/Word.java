package hangedMan;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class Word {
    private char[] actualState;
    private String selectedWord;


    protected Word(String path) throws IOException {
        this.setSelectedWord(this.getRandomWord(path));
        this.initActualState();
    }


    private char[] getActualState() {
        return this.actualState;
    }

    private void setActualState(char[] actualState) {
        this.actualState = actualState;
    }


    public String getActualStateString() {
        String str = "";
        char[] characters = this.getActualState();
        for (int i = 0; i < this.getWordLength(); i++)
            str += characters[i];
        return str;
    }

    public String getSelectedWord() {
        return this.selectedWord;
    }

    private void setSelectedWord(String selectedWord) {
        this.selectedWord = selectedWord;
    }

    private char[] getSelectedWordCharArray() {
        return this.getSelectedWord().toCharArray();
    }

    public int getWordLength() {
        return this.getSelectedWord().length();
    }

    public boolean isFinished() {
        return this.getActualStateString().equals(this.getSelectedWord());
    }

    private String getRandomWord(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String str;
            ArrayList<String> words = new ArrayList<String>();
            while ((str = br.readLine()) != null)
                words.add(str);

            int position = (int) (Math.random() * words.size());
            return words.get(position);
        }
    }


    public int numDifferentLetters() {
        String word = this.getSelectedWord();
        IntStream chars = word.chars();
        IntStream disticts = chars.distinct();
        return (int) disticts.count();
    }


    private void initActualState() {
        char[] chars = new char[this.getSelectedWord().length()];
        for (int i = 0; i < chars.length; i++)
            chars[i] = '_';
        setActualState(chars);
    }

    public boolean addLetter(char letter) {
        boolean found = false;
        char[] letters = this.getSelectedWordCharArray();
        char[] state = this.getActualState();
        for (int i = 0; i < this.getWordLength(); i++)
            if (letters[i] == letter) {
                if (!found) found = true;
                state[i] = letter;
            }
        this.setActualState(state);
        return found;
    }
}