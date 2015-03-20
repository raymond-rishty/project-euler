package net.rishty.projecteuler.problems;

import com.google.common.collect.TreeTraverser;
import net.rishty.projecteuler.util.PythagoreanTriple;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/*A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,

a^2 + b^2 = c^2
For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.

There exists exactly one Pythagorean triplet for which a + b + c = 1000.
Find the product abc.*/
public class Problem009 {

    public static void main(String[] args) {
        TreeTraverser<PythagoreanTriple> treeTraverser = new TreeTraverser<PythagoreanTriple>() {
            @Override
            public Iterable<PythagoreanTriple> children(PythagoreanTriple triple) {
                return
                        (Iterable<PythagoreanTriple>) triple.children().stream()
                                .filter(child -> child.perimeter() <= 1000)
                                .collect(toList());

            }
        };
        PythagoreanTriple root = new PythagoreanTriple(3, 4, 5);

        Optional<PythagoreanTriple> foundTriple = treeTraverser.breadthFirstTraversal(root)
                .toList()
                .stream()
                .flatMap((t) -> t.multiples().limit(200))
                .filter(triple -> triple.perimeter() == 1000).findAny();

        System.out.printf("The product of the triple %s is %s.", foundTriple, foundTriple.map(PythagoreanTriple::product).get());

    }
}
