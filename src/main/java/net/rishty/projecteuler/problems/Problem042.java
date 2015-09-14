package net.rishty.projecteuler.problems;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Multiset;
import com.google.common.io.Resources;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Coded triangle numbers
 * Problem 42
 * The nth term of the sequence of triangle numbers is given by, tn = ½n(n+1); so the first ten triangle numbers are:
 * <p>
 * 1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...
 * <p>
 * By converting each letter in a word to a number corresponding to its alphabetical position and adding these values we
 * form a word value. For example, the word value for SKY is 19 + 11 + 25 = 55 = t_10. If the word value is a triangle
 * number then we shall call the word a triangle word.
 * <p>
 * Using words.txt (right click and 'Save Link/Target As...'), a 16K text file containing nearly two-thousand common
 * English words, how many are triangle words?
 */
public class Problem042 {
    public static void main(String[] args) throws IOException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        new Problem042().run();
        System.out.println(stopwatch);
    }

    void run() throws IOException {
        List<String> words = readFile();
        Multiset<Integer> values = wordsToValues(words);
        int count = countTriangleWords(values);
        System.out.println(count);

    }

    List<String> readFile() throws IOException {
        String contents = Resources.toString(Resources.getResource("p042_words.txt"), StandardCharsets.UTF_8);
        return Splitter
                .on(',')
                .trimResults(CharMatcher.is('\"'))
                .splitToList(contents);
    }

    int wordToNumberValue(String word) {
        int sum = 0;
        for (char letter : word.toCharArray()) {
            sum += (int) letter - (int) 'A' + 1;
        }

        return sum;
    }

    Multiset<Integer> wordsToValues(List<String> words) {
        Integer[] values = words.stream()
                .map(this::wordToNumberValue)
                .toArray(Integer[]::new);
        return ImmutableMultiset.copyOf(values);
    }

    int countTriangleWords(Multiset<Integer> values) {
        int max = values.stream().mapToInt(Integer::intValue).max().getAsInt();
        int count = 0;
        int triangular = 0;
        int diff = 1;
        while (triangular <= max) {
            triangular += diff;
            diff++;
            count += values.count(triangular);
        }

        return count;
    }
}
