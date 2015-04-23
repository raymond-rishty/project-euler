package net.rishty.projecteuler.problems;

import java.util.stream.IntStream;

/**
 * Created by raymondrishty on 4/23/15.
 */
public class Problem017 {
    int[] primitives = new int[]{3, 3, 5, 4, 4, 3, 5, 5, 4, 3, 6, 6, 8, 8, 7, 7, 9, 8, 8};
    int[] ty = new int[]{4, 6, 6, 5, 5, 5, 7, 6, 6};

    public static void main(String[] args) {
        Problem017 app = new Problem017();
        app.run(1000);
    }

    private void run(int upper) {
        System.out.println(
                IntStream.rangeClosed(1, upper)
                        .parallel()
                        .map(this::getLengthForNumber)
                        .sum());
    }

    public int getLengthForNumber(int num) {
        if (num < 20) {
            return primitives[num - 1];
        } else if (num < 100) {
            int twos = num / 10;
            int ty = this.ty[twos - 1];
            int remainder = num % 10;
            return remainder == 0 ? ty : ty + primitives[remainder - 1];
        } else if (num < 1000) {
            int hundred = primitives[num / 100 - 1] + 7;
            int remainder = num % 100;
            if (remainder == 0) {
                return hundred;
            } else {
                return hundred + 3 + getLengthForNumber(remainder);
            }
        } else if (num < 10000) {
            int thousand = primitives[num / 1000 - 1] + 8;
            int remainder = num % 1000;
            if (remainder == 0) {
                return thousand;
            } else {
                return thousand + getLengthForNumber(remainder);
            }
        }

        return 0;

    }
}
