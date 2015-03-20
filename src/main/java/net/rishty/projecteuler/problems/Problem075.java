package net.rishty.projecteuler.problems;

import com.google.common.base.Stopwatch;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.TreeTraverser;
import net.rishty.projecteuler.util.PythagoreanTriple;

import static java.util.stream.Collectors.toList;

/**
 Singular integer right triangles
 Problem 75
 It turns out that 12 cm is the smallest length of wire that can be bent to form an integer sided right angle triangle in exactly one way, but there are many more examples.

 12 cm: (3,4,5)
 24 cm: (6,8,10)
 30 cm: (5,12,13)
 36 cm: (9,12,15)
 40 cm: (8,15,17)
 48 cm: (12,16,20)

 In contrast, some lengths of wire, like 20 cm, cannot be bent to form an integer sided right angle triangle, and other lengths allow more than one solution to be found; for example, using 120 cm it is possible to form exactly three different integer sided right angle triangles.

 120 cm: (30,40,50), (20,48,52), (24,45,51)

 Given that L is the length of the wire, for how many values of L ≤ 1,500,000 can exactly one integer sided right angle triangle be formed?
 */
public class Problem075 {
    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        new Problem075().run();
        System.out.println(stopwatch.stop());
    }

    private void run() {
        System.out.println(getCountOfPerimetersOfOneTriple(1500000));
    }

    private long getCountOfPerimetersOfOneTriple(long maxPerimeter) {
        PythagoreanTriple root = new PythagoreanTriple(3, 4, 5);
        int[] collect = new TreeTraverser<PythagoreanTriple>() {
            @Override
            public Iterable<PythagoreanTriple> children(PythagoreanTriple triple) {
                return
                        (Iterable<PythagoreanTriple>) triple.children().stream()
                                .filter(child -> child.perimeter() <= maxPerimeter)
                                .collect(toList());
            }
        }.preOrderTraversal(root)
                .toList()
                .stream()
                .flatMap(triple -> triple.multiples().limit(maxPerimeter / triple.perimeter()).filter(multiple -> multiple.perimeter() <= maxPerimeter))
                .mapToInt(PythagoreanTriple::perimeter)
                .toArray();
        HashMultiset<Integer> perimeters = HashMultiset.create();
        for (int perimeter : collect) {
            perimeters.add(perimeter);
        }

        return perimeters
                .stream()
                .filter(perimeter -> perimeters.count(perimeter) == 1)
                .count();
    }
}
