package tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class WordParser {
    private HashMap<String, Integer> wordMap;
    public static final String wordSeparators = "[.,\\$:;()?!\"\\s]+";
    // mongod illegal keyNames /\. "$
    private long wordsCount = 0;
    public WordParser() {
        wordMap = new HashMap<>();

    }

    public void parseDoc(String doc) {
        Scanner input = new Scanner(doc);
        input.useDelimiter(wordSeparators);
        while (input.hasNext()) {
            String word = input.next().toLowerCase();
            wordsCount++;
            if (wordMap.containsKey(word)) {
                Integer count = wordMap.get(word);
                wordMap.put(word, count+1);
            } else {
                wordMap.put(word, 1);
            }
        }
    }

    public HashMap<String, Integer> getWordMap() {
        return wordMap;
    }

}
