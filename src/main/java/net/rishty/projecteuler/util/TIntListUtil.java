package net.rishty.projecteuler.util;

import gnu.trove.iterator.TIntIterator;
import gnu.trove.list.TIntList;

import java.util.Spliterator;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

public class TIntListUtil {
    public static IntStream toStream(TIntList intList) {
        return IntStream.of(intList.toArray());
    }
}
