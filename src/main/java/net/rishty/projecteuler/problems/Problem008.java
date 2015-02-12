package net.rishty.projecteuler.problems;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * The four adjacent digits in the 1000-digit number that have the greatest product are 9 × 9 × 8 × 9 = 5832.
 * <p/>
 * 73167176531330624919225119674426574742355349194934
 * 96983520312774506326239578318016984801869478851843
 * 85861560789112949495459501737958331952853208805511
 * 12540698747158523863050715693290963295227443043557
 * 66896648950445244523161731856403098711121722383113
 * 62229893423380308135336276614282806444486645238749
 * 30358907296290491560440772390713810515859307960866
 * 70172427121883998797908792274921901699720888093776
 * 65727333001053367881220235421809751254540594752243
 * 52584907711670556013604839586446706324415722155397
 * 53697817977846174064955149290862569321978468622482
 * 83972241375657056057490261407972968652414535100474
 * 82166370484403199890008895243450658541227588666881
 * 16427171479924442928230863465674813919123162824586
 * 17866458359124566529476545682848912883142607690042
 * 24219022671055626321111109370544217506941658960408
 * 07198403850962455444362981230987879927244284909188
 * 84580156166097919133875499200524063689912560717606
 * 05886116467109405077541002256983155200055935729725
 * 71636269561882670428252483600823257530420752963450
 * Find the thirteen adjacent digits in the 1000-digit number that have the greatest product. What is the value of this product?
 */
public class Problem008 {
    public static void main(String[] args) {
        StringBuilder list = new StringBuilder();
        list.append("73167176531330624919225119674426574742355349194934");
        list.append("96983520312774506326239578318016984801869478851843");
        list.append("85861560789112949495459501737958331952853208805511");
        list.append("12540698747158523863050715693290963295227443043557");
        list.append("66896648950445244523161731856403098711121722383113");
        list.append("62229893423380308135336276614282806444486645238749");
        list.append("30358907296290491560440772390713810515859307960866");
        list.append("70172427121883998797908792274921901699720888093776");
        list.append("65727333001053367881220235421809751254540594752243");
        list.append("52584907711670556013604839586446706324415722155397");
        list.append("53697817977846174064955149290862569321978468622482");
        list.append("83972241375657056057490261407972968652414535100474");
        list.append("82166370484403199890008895243450658541227588666881");
        list.append("16427171479924442928230863465674813919123162824586");
        list.append("17866458359124566529476545682848912883142607690042");
        list.append("24219022671055626321111109370544217506941658960408");
        list.append("07198403850962455444362981230987879927244284909188");
        list.append("84580156166097919133875499200524063689912560717606");
        list.append("05886116467109405077541002256983155200055935729725");
        list.append("71636269561882670428252483600823257530420752963450");
        int[] array = parseArray(list.toString());
        long product = getLargestProduct(array, 13);

        printMatrix(array);
        System.out.println(product);

    }

    private static long getLargestProduct(int[] array, int n) {
        long maxProduct = 0;

            for (int colIndex = 0; colIndex < array.length ; colIndex++) {
                long product = 1;
                for (int offset = 0; offset < n; offset++) {
                    product *= array[colIndex + offset];
                    if (product == 0) {
                        break;
                    }
                }

                if (product > maxProduct) {
                    maxProduct = product;
                }
            }


        return maxProduct;
    }

    private static int[] parseArray(String list) {
        int[] array = new int[1000];
        {
            for (int colIndex = 0; colIndex < 1000; colIndex++) {
                array[colIndex] = Character.getNumericValue(list.charAt(colIndex));
            }
        }
        return array;
    }

    private static void printMatrix(int[] matrix) {
        for (int rowIndex = 0; rowIndex < 20; rowIndex++) {
            for (int colIndex = 0; colIndex < 50; colIndex++) {
                System.out.print(matrix[rowIndex * 50 + colIndex]);
            }
            System.out.println();
        }
    }

    @FunctionalInterface
    interface IntTernaryOperator {
        int applyAsInt(int row, int col, int offset);
    }
}
