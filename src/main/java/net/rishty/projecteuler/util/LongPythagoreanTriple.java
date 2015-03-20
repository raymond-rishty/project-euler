package net.rishty.projecteuler.util;

import com.google.common.collect.ImmutableList;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class LongPythagoreanTriple {
        private final long a;
        private final long b;
        private final long c;

        public static final long[][] U = {{1, 2, 2}, {-2, -1, -2}, {2, 2, 3}};
        public static final long[][] A = {{1, 2, 2}, {2, 1, 2}, {2, 2, 3}};
        public static final long[][] D = {{-1, -2, -2}, {2, 1, 2}, {2, 2, 3}};

    public LongPythagoreanTriple(long a, long b, long c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public Set<LongPythagoreanTriple> children() {
            List<LongPythagoreanTriple> collect = Stream.of(U, A, D).map(matrix -> {
                long newA = a * matrix[0][0] + b * matrix[1][0] + c * matrix[2][0];
                long newB = a * matrix[0][1] + b * matrix[1][1] + c * matrix[2][1];
                long newC = a * matrix[0][2] + b * matrix[1][2] + c * matrix[2][2];

                return new LongPythagoreanTriple(newA, newB, newC);
            })
                    .collect(toList());
            return new LinkedHashSet<>(collect);

        }

        public LongPythagoreanTriple add(LongPythagoreanTriple other) {
            long sumA = this.a + other.a;
            long sumB = this.b + other.b;
            long sumC = this.c + other.c;
            return new LongPythagoreanTriple(sumA, sumB, sumC);
        }

        @Override
        public String toString() {
            return String.format("(%s, %s, %s)", a, b, c);
        }

        public long perimeter() {
            return a + b + c;
        }

        public long product() {
            return a * b * c;
        }

    public List<Long> getSides() {
        return ImmutableList.of(a, b, c);
    }

        public Stream<LongPythagoreanTriple> multiples() {
            return Stream.iterate(this, triple -> triple.add(this));
        }
    }
