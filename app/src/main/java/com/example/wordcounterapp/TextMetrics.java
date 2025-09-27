package com.example.wordcounterapp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextMetrics {

    public static int countWords(String text) {
        if(text.isEmpty()) return 0;
        return text.split("[\\s,.]+").length;
    }

    public static int countSentences(String text) {
        if(text.isEmpty()) return 0;
        return text.split("[.!?]").length;
    }

    public static int countChars(String text) {
        return text.length();
    }

    public static int countNumbers(String text) {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(text);
        int count = 0;
        while(m.find()) count++;
        return count;
    }
}