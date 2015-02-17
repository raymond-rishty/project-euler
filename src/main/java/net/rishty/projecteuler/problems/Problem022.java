package net.rishty.projecteuler.problems;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Ordering;
import com.google.common.io.Resources;
import com.google.common.primitives.Chars;
import gnu.trove.list.array.TIntArrayList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import static com.google.common.base.Throwables.propagate;
import static com.google.common.io.Resources.*;

/**
 * Using names.txt (right click and 'Save Link/Target As...'), a 46K text file containing over five-thousand first names, begin by sorting it into alphabetical order. Then working out the alphabetical value for each name, multiply this value by its alphabetical position in the list to obtain a name score.
 * <p>
 * For example, when the list is sorted into alphabetical order, COLIN, which is worth 3 + 15 + 12 + 9 + 14 = 53, is the 938th name in the list. So, COLIN would obtain a score of 938 × 53 = 49714.
 * <p>
 * What is the total of all the name scores in the file?
 */
public class Problem022 {
    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        new Problem022().run();
        System.out.println(stopwatch.stop());
    }

    private void run() {
        ImmutableList<String> names = readAndParseFile();
        ImmutableList<String> sortedNames = sortNames(names);
        long totalScore = scoreNames(sortedNames);
        System.out.println(totalScore);

    }

    private long scoreNames(ImmutableList<String> sortedNames) {
        return sortedNames
                .stream()
                .mapToLong(new ToLongFunction<String>() {
                    int index = 0;

                    @Override
                    public long applyAsLong(String name) {
                        int value = getTextValue(name) * (++index);
                        //System.out.printf("%s %s:\t%s%n", index, name, value);
                        return value;
                    }
                })
                .sum();
    }

    private int getTextValue(String name) {
        return Chars
                .asList(name.toCharArray())
                .stream()
                .mapToInt(chr -> 1 + chr - 'A')
                .sum();
    }

    private ImmutableList<String> sortNames(ImmutableList<String> names) {
        return Ordering
                .natural()
                .immutableSortedCopy(names);
    }

    private ImmutableList<String> readAndParseFile() {
        String contents = readFile();
        Iterable<String> names = Splitter
                .on(",")
                .trimResults(CharMatcher.is('"'))
                .split(contents);
        return ImmutableList.copyOf(names);
    }

    private String readFile() {
        try {
            return Resources.toString(getResource("p022_names.txt"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw propagate(e);
        }
    }
}
