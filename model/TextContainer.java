package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;


/**
 * Created by Admin on 05.04.16.
 */
public class TextContainer {

    String entryText;

    public TextContainer(String entryText) {
        this.entryText = entryText;
    }


    public String getWarpedText() {
        StringTokenizer wordsFinder = new StringTokenizer(entryText);
        StringBuilder wordsBuffer = new StringBuilder();

        while (wordsFinder.hasMoreElements()) {
            String word = wordsFinder.nextToken();
            int minWordLong = 3;
            if (isHaveSymbolOfStringEnd(word))
                minWordLong = 4;
            if (word.length() > minWordLong)
                wordsBuffer.append(getOutWordWithMixedLetter(word)).append(" ");
            else
                wordsBuffer.append(word).append(" ");
        }
        return wordsBuffer.toString();
    }

    private boolean isHaveSymbolOfStringEnd(String word) {
        return word.contains(",") || word.contains(".") || word.contains("!") || word.contains("?") || word.contains(":") || word.contains(";");
    }

    private String getOutWordWithMixedLetter(String wordToMix) {
        char[] inWord = wordToMix.toCharArray();
        return getImplodedOutWord(getCharArrayOfOutWordLetters(inWord, getNewCharIndexes(inWord,wordToMix),wordToMix));
    }

    private List<Integer> getNewCharIndexes(char[] inWord, String wordToMix) {
        Random random = new Random();
        List<Integer> charIndexes = new ArrayList<>();
        int workArea = inWord.length;
        if(isHaveSymbolOfStringEnd(wordToMix))
            --workArea;
        do {
            int newIndex = random.nextInt(workArea - 1);
            if (newIndex == 0)
                continue;
            else if (!charIndexes.contains(newIndex))
                charIndexes.add(newIndex);
        } while (charIndexes.size() != workArea - 2);

        return charIndexes;
    }

    private char[] getCharArrayOfOutWordLetters(char[] inWord, List<Integer> charIndexes, String wordToMix) {
        char[] outWord = new char[inWord.length];
        int nextChar = 1;
        for (int charIndex : charIndexes) {
            outWord[nextChar++] = inWord[charIndex];
        }
        outWord[0] = inWord[0];
        outWord[outWord.length - 1] = inWord[inWord.length - 1];
        if (isHaveSymbolOfStringEnd(wordToMix))
            outWord[outWord.length - 2] = inWord[inWord.length - 2];
        return outWord;
    }

    private String getImplodedOutWord(char[] outWord) {
        StringBuilder buildWord = new StringBuilder();
        for (char character : outWord)
            buildWord.append(character);
        return buildWord.toString();
    }


}
