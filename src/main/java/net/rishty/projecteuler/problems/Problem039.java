package net.rishty.projecteuler.problems;

import com.google.common.base.Stopwatch;
import com.google.common.collect.*;
import com.google.common.primitives.Ints;
import net.rishty.projecteuler.util.PythagoreanTriple;

import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.google.common.primitives.Ints.compare;
import static java.util.stream.Collectors.toList;

/**
 * Integer right triangles
 * Problem 39
 * If p is the perimeter of a right angle triangle with integral length sides, {a,b,c}, there are exactly three solutions for p = 120.
 * <p>
 * {20,48,52}, {24,45,51}, {30,40,50}
 * <p>
 * For which value of p ≤ 1000, is the number of solutions maximised?
 */
public class Problem039 {
    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        new Problem039().run();
        System.out.println(stopwatch.stop());
    }

    private void run() {
        Multiset.Entry<Integer> max = getMostFrequentPerimeter(1000);

        System.out.printf("%s: %s%n", max.getElement(), max.getCount());
    }

    private Multiset.Entry<Integer> getMostFrequentPerimeter(int maxPerimeter) {
        int[] collect = new TreeTraverser<PythagoreanTriple>() {
            @Override
            public Iterable<PythagoreanTriple> children(PythagoreanTriple triple) {
                return
                        (Iterable<PythagoreanTriple>) triple.children().stream()
                                .filter(child -> child.perimeter() <= maxPerimeter)
                                .collect(toList());
            }
        }.preOrderTraversal(new PythagoreanTriple(3, 4, 5))
                .toList()
                .stream()
                .flatMap(triple -> triple.multiples().limit(200).filter(multiple -> multiple.perimeter() <= maxPerimeter))
                .mapToInt(PythagoreanTriple::perimeter)
                .toArray();
        HashMultiset<Integer> perimeters = HashMultiset.create();
        for (int perimeter : collect) {
            perimeters.add(perimeter);
        }

        return Ordering
                .<Multiset.Entry<Integer>>from((entry1, entry2) -> Ints.compare(entry1.getCount(), entry2.getCount()))
                .max(perimeters.entrySet());
    }
}
