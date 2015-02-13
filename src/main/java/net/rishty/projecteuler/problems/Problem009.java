package net.rishty.projecteuler.problems;

import com.google.common.collect.TreeTraverser;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/*A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,

a^2 + b^2 = c^2
For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.

There exists exactly one Pythagorean triplet for which a + b + c = 1000.
Find the product abc.*/
public class Problem009 {

    public static class Triple {
        private final int a;
        private final int b;
        private final int c;

        public static final int[][] U = {{1, 2, 2}, {-2, -1, -2}, {2, 2, 3}};
        public static final int[][] A = {{1, 2, 2}, {2, 1, 2}, {2, 2, 3}};
        public static final int[][] D = {{-1, -2, -2}, {2, 1, 2}, {2, 2, 3}};

        Triple(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public Set<Triple> children() {
            List<Triple> collect = Stream.of(U, A, D).map(matrix -> {
                int newA = a * matrix[0][0] + b * matrix[1][0] + c * matrix[2][0];
                int newB = a * matrix[0][1] + b * matrix[1][1] + c * matrix[2][1];
                int newC = a * matrix[0][2] + b * matrix[1][2] + c * matrix[2][2];

                return new Triple(newA, newB, newC);
            })
                    .collect(toList());
            return new LinkedHashSet<>(collect);

        }

        public Triple add(Triple other) {
            int sumA = this.a + other.a;
            int sumB = this.b + other.b;
            int sumC = this.c + other.c;
            return new Triple(sumA, sumB, sumC);
        }

        @Override
        public String toString() {
            return String.format("(%s, %s, %s)", a, b, c);
        }

        public int perimeter() {
            return a + b + c;
        }

        public int product() {
            return a * b * c;
        }

        public Stream<Triple> multiples() {
            return Stream.iterate(this, triple -> triple.add(this));
        }
    }

    public static void main(String[] args) {
        TreeTraverser<Triple> treeTraverser = new TreeTraverser<Triple>() {
            @Override
            public Iterable<Triple> children(Triple triple) {
                return
                        (Iterable<Triple>) triple.children().stream()
                                .filter(child -> child.perimeter() <= 1000)
                                .collect(toList());

            }
        };
        Triple root = new Triple(3, 4, 5);

        Optional<Triple> foundTriple = treeTraverser.breadthFirstTraversal(root)
                .toList()
                .stream()
                .flatMap((t) -> t.multiples().limit(200))
                .filter(triple -> triple.perimeter() == 1000).findAny();

        System.out.printf("The product of the triple %s is %s.", foundTriple, foundTriple.map(Triple::product).get());

    }
}
