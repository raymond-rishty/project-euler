package net.rishty.projecteuler.problems;

import com.google.common.base.Joiner;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.TreeTraverser;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 Lexicographic permutations
 Problem 24
 Published on Friday, 16th August 2002, 01:00 pm; Solved by 64855
 A permutation is an ordered arrangement of objects. For example, 3124 is one possible permutation of the digits 1, 2, 3 and 4. If all of the permutations are listed numerically or alphabetically, we call it lexicographic order. The lexicographic permutations of 0, 1 and 2 are:

 012   021   102   120   201   210

 What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?
 */
public class Problem024 {
    public class PermutationNode {
        private ArrayList<Integer> used;
        private List<Integer> remaining;

        PermutationNode(Integer... elements) {
            this.used = Lists.newArrayList();
            this.remaining = Lists.newArrayList(elements);
        }

        PermutationNode(ArrayList<Integer> prevUsed, List<Integer> prevRemaining, Integer chosen) {
            this.used = new ArrayList<>(prevUsed);
            this.used.add(chosen);
            this.remaining = new LinkedList<>(prevRemaining);
            this.remaining.remove(chosen);
        }

        public List<PermutationNode> children() {
            if (isLeaf()) {
                return ImmutableList.of();
            }

            return remaining
                    .stream()
                    .map(choice -> new PermutationNode(used, remaining, choice))
                    .collect(Collectors.toList());
        }

        public boolean isLeaf() {
            return remaining.isEmpty();
        }

        @Override
        public String toString() {
            return Joiner.on("").join(used);
        }
    }

    public String findNodeNumber(int number) {
        TreeTraverser<PermutationNode> treeTraverser = new TreeTraverser<PermutationNode>() {
            @Override
            public Iterable<PermutationNode> children(PermutationNode root) {
                return root.children();
            }
        };

        PermutationNode permutationNode = treeTraverser
                .preOrderTraversal(new PermutationNode(0, 1, 2, 3, 4, 5, 6, 7, 8, 9))
                .filter(PermutationNode::isLeaf)
                .skip(number)
                .get(1);


        return permutationNode.toString();
    }

    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        new Problem024().run();
        System.out.println(stopwatch.stop());
    }

    private void run() {
        System.out.println(findNodeNumber(1_000_000));
    }
}
