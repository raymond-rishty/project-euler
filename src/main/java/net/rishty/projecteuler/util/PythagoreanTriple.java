package net.rishty.projecteuler.util;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by raymondrishty on 2/21/15.
 */
public class PythagoreanTriple {
        private final int a;
        private final int b;
        private final int c;

        public static final int[][] U = {{1, 2, 2}, {-2, -1, -2}, {2, 2, 3}};
        public static final int[][] A = {{1, 2, 2}, {2, 1, 2}, {2, 2, 3}};
        public static final int[][] D = {{-1, -2, -2}, {2, 1, 2}, {2, 2, 3}};

    public PythagoreanTriple(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public Set<PythagoreanTriple> children() {
            List<PythagoreanTriple> collect = Stream.of(U, A, D).map(matrix -> {
                int newA = a * matrix[0][0] + b * matrix[1][0] + c * matrix[2][0];
                int newB = a * matrix[0][1] + b * matrix[1][1] + c * matrix[2][1];
                int newC = a * matrix[0][2] + b * matrix[1][2] + c * matrix[2][2];

                return new PythagoreanTriple(newA, newB, newC);
            })
                    .collect(toList());
            return new LinkedHashSet<>(collect);

        }

        public PythagoreanTriple add(PythagoreanTriple other) {
            int sumA = this.a + other.a;
            int sumB = this.b + other.b;
            int sumC = this.c + other.c;
            return new PythagoreanTriple(sumA, sumB, sumC);
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

    public List<Integer> getSides() {
        return ImmutableList.of(a, b, c);
    }

        public Stream<PythagoreanTriple> multiples() {
            return Stream.iterate(this, triple -> triple.add(this));
        }
    }
