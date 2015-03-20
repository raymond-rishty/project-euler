package net.rishty.projecteuler.problems;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Ordering;
import com.google.common.collect.TreeTraverser;
import net.rishty.projecteuler.util.LongPythagoreanTriple;
import net.rishty.projecteuler.util.PythagoreanTriple;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 Almost equilateral triangles
 Problem 94
 It is easily proved that no equilateral triangle exists with integral length sides and integral area. However, the almost equilateral triangle 5-5-6 has an area of 12 square units.

 We shall define an almost equilateral triangle to be a triangle for which two sides are equal and the third differs by no more than one unit.

 Find the sum of the perimeters of all almost equilateral triangles with integral side lengths and area and whose perimeters do not exceed one billion (1,000,000,000).
 */
public class Problem094 {
    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        new Problem094().run();
        System.out.println(stopwatch.stop());
    }

    private void run() {
        System.out.println(getSumOfPerimetersOfAlmostEquilateralTriangles(1_000_000_000L));
    }

    private long getSumOfPerimetersOfAlmostEquilateralTriangles(long upperLimit) {
        Stream<LongPythagoreanTriple> triples = getPythagoreanTriples(upperLimit);
        return triples.filter(this::isAlmostEquilateral)
                .mapToLong(LongPythagoreanTriple::perimeter)
                .sum();
    }

    private Stream<LongPythagoreanTriple> getPythagoreanTriples(long maxPerimeter) {
        return new TreeTraverser<LongPythagoreanTriple>() {
            @Override
            public Iterable<LongPythagoreanTriple> children(LongPythagoreanTriple root) {
                return
                        (Iterable<LongPythagoreanTriple>) root.children().stream()
                                .filter(child -> child.perimeter() <= maxPerimeter)
                                .collect(toList());

            }
        }.preOrderTraversal(new LongPythagoreanTriple(3, 4, 5))
                .toList()
                .stream();
    }

    private boolean isAlmostEquilateral(LongPythagoreanTriple triple) {
        List<Long> sides = Ordering.natural().sortedCopy(triple.getSides());
        long twiceShort = sides.get(0) * 2;
        return Math.abs(sides.get(2) - twiceShort) <= 1;
    }
}
