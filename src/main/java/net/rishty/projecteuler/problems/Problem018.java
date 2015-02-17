package net.rishty.projecteuler.problems;

import com.google.common.base.Splitter;
import com.google.common.base.Stopwatch;
import com.google.common.collect.*;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * By starting at the top of the triangle below and moving to adjacent numbers on the row below, the maximum total from top to bottom is 23.
 * <p>
 * 3
 * 7 4
 * 2 4 6
 * 8 5 9 3
 * <p>
 * That is, 3 + 7 + 4 + 9 = 23.
 * <p>
 * Find the maximum total from top to bottom of the triangle below:
 * <p>
 * 75
 * 95 64
 * 17 47 82
 * 18 35 87 10
 * 20 04 82 47 65
 * 19 01 23 75 03 34
 * 88 02 77 73 07 63 67
 * 99 65 04 28 06 16 70 92
 * 41 41 26 56 83 40 80 70 33
 * 41 48 72 33 47 32 37 16 94 29
 * 53 71 44 65 25 43 91 52 97 51 14
 * 70 11 33 28 77 73 17 78 39 68 17 57
 * 91 71 52 38 17 14 91 43 58 50 27 29 48
 * 63 66 04 68 89 53 67 30 73 16 69 87 40 31
 * 04 62 98 27 23 09 70 98 73 93 38 53 60 04 23
 * <p>
 * NOTE: As there are only 16384 routes, it is possible to solve this problem by trying every route. However, Problem 67, is the same challenge with a triangle containing one-hundred rows; it cannot be solved by brute force, and requires a clever method! ;o)
 */
public class Problem018 {
    public Table<Integer, Integer, Integer> constructTriangle() {
        List<ImmutableList<String>> list = new ArrayList<>();
        String triangleStr = "" +
                "75\n" +
                "95 64\n" +
                "17 47 82\n" +
                "18 35 87 10\n" +
                "20 04 82 47 65\n" +
                "19 01 23 75 03 34\n" +
                "88 02 77 73 07 63 67\n" +
                "99 65 04 28 06 16 70 92\n" +
                "41 41 26 56 83 40 80 70 33\n" +
                "41 48 72 33 47 32 37 16 94 29\n" +
                "53 71 44 65 25 43 91 52 97 51 14\n" +
                "70 11 33 28 77 73 17 78 39 68 17 57\n" +
                "91 71 52 38 17 14 91 43 58 50 27 29 48\n" +
                "63 66 04 68 89 53 67 30 73 16 69 87 40 31\n" +
                "04 62 98 27 23 09 70 98 73 93 38 53 60 04 23";
        Table<Integer, Integer, Integer> table = TreeBasedTable.create();
        int rowNum = 0;
        for (String triangleRowStr : Splitter.on("\n").split(triangleStr)) {
            int colNum = 0;
            for (String triangleColStr : Splitter.on(' ').split(triangleRowStr)) {
                table.put(rowNum, colNum++, Integer.valueOf(triangleColStr));
            }
            rowNum++;
        }

        return table;
    }

    private void printTable(Table<Integer, Integer, Integer> table, PrintStream out) {
        for (Map.Entry<Integer, Map<Integer, Integer>> entry : table.rowMap().entrySet()) {
            Map<Integer, Integer> row = entry.getValue();
            for (Map.Entry<Integer, Integer> colEntry : row.entrySet()) {
                out.printf("%s\t", colEntry.getValue());
            }
            out.println();
        }
    }

    public TreeBasedTable<Integer, Integer, Integer> computeSums(Table<Integer, Integer, Integer> triangle) {
        TreeBasedTable<Integer, Integer, Integer> triangleSums = TreeBasedTable.create((TreeBasedTable<Integer, Integer, Integer>) triangle);
        Map<Integer, Integer> prevRow = triangleSums.row(0);
        for (int rowIndex = 1; rowIndex < triangleSums.rowKeySet().size(); rowIndex++) {
            Map<Integer, Integer> row = triangleSums.row(rowIndex);

            row.put(0, row.get(0) + prevRow.get(0));
            for (int colIndex = 1; colIndex < row.size() - 1; colIndex++) {
                int currentValue = row.get(colIndex);
                int max = Math.max(prevRow.get(colIndex), prevRow.get(colIndex - 1));
                row.put(colIndex, currentValue + max);
            }

            row.put(row.size() - 1, row.get(row.size() - 1) + prevRow.get(prevRow.size() - 1));

            prevRow = row;
        }

        return triangleSums;
    }

    public int findMaxPath(Table<Integer, Integer, Integer> triangleSums) {
        return Ordering.natural().max(triangleSums.row(triangleSums.rowKeySet().size() - 1).values());
    }

    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        new Problem018().run();
        System.out.println(stopwatch.stop());
    }

    private void run() {
        int maxPath = findMaxPath(computeSums(constructTriangle()));
        System.out.println(maxPath);
    }
}
