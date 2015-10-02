package net.rishty.projecteuler.problems;

import com.google.common.base.Splitter;
import com.google.common.base.Stopwatch;
import com.google.common.collect.*;
import com.google.common.io.Resources;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Poker hands
 * Problem 54
 * <p>
 * In the card game poker, a hand consists of five cards and are ranked, from lowest to highest, in the following way:
 * <p>
 * - High Card: Highest value card.
 * - One Pair: Two cards of the same value.
 * - Two Pairs: Two different pairs.
 * - Three of a Kind: Three cards of the same value.
 * - Straight: All cards are consecutive values.
 * - Flush: All cards of the same suit.
 * - Full House: Three of a kind and a pair.
 * - Four of a Kind: Four cards of the same value.
 * - Straight Flush: All cards are consecutive values of same suit.
 * - Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.
 * <p>
 * The cards are valued in the order:
 * 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King, Ace.
 * <p>
 * If two players have the same ranked hands then the rank made up of the highest value wins; for example, a pair of
 * eights beats a pair of fives (see example 1 below). But if two ranks tie, for example, both players have a pair of
 * queens, then highest cards in each hand are compared (see example 4 below); if the highest cards tie then the next
 * highest cards are compared, and so on.
 * <p>
 * Consider the following five hands dealt to two players:
 * <p>
 * Hand	 	Player 1	 	                              Player 2	 	                        Winner
 * 1	 	5H 5C 6S 7S KD                                 2C 3S 8S 8D TD
 * Pair of Fives                                 Pair of Eights                          Player 2
 * 2	 	5D 8C 9S JS AC                                  2C 5C 7D 8S QH
 * Highest card Ace                             Highest card Queen                         Player 1
 * 3	 	2D 9C AS AH AC                                  3D 6D 7D TD QD
 * Three Aces                                  Flush with Diamonds                       Player 2
 * 4	 	4D 6S 9H QH QC                                  3D 6D 7H QD QS
 * Pair of Queens Highest card Nine           Pair of Queens Highest card Seven               Player 1
 * 5	 	2H 2D 4C 4D 4S                                  3C 3D 3S 9S 9D
 * Full House With Three Fours                  Full House with Three Threes                  Player 1
 * <p>
 * The file, poker.txt, contains one-thousand random hands dealt to two players. Each line of the file contains ten cards
 * (separated by a single space): the first five are Player 1's cards and the last five are Player 2's cards. You can
 * assume that all hands are valid (no invalid characters or repeated cards), each player's hand is in no specific order,
 * and in each hand there is a clear winner.
 * <p>
 * How many hands does Player 1 win?
 */
public class Problem054 {
    public static void main(String[] args) throws IOException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        new Problem054().run();
        System.out.println(stopwatch);
    }

    private void run() throws IOException {
        List<String> lines = Resources.readLines(Resources.getResource("p054_poker.txt"), StandardCharsets.UTF_8);
        long player1Hands = lines.stream()
                .map(game -> compare(game.substring(0, 14), game.substring(15)))
                .filter(winner -> winner == 1)
                .count();
        System.out.println(player1Hands);
    }

    public int compare(String hand1Str, String hand2Str) {
        Hand hand1 = Hand.toHand(hand1Str);
        Hand hand2 = Hand.toHand(hand2Str);

        int hand1Class = hand1.getHandType();
        int hand2Class = hand2.getHandType();
        if (hand1Class > hand2Class) {
            return 1;
        } else if (hand2Class > hand1Class) {
            return 2;
        } else {
            int count = 1;

            if (hand1Class == 8) {
                count = 1;
            } else if (hand1Class == 7) {
                count = 4;
            } else if (hand1Class == 6) {
                count = 3;
            } else if (hand1Class == 5) {
                count = 1;
            } else if (hand1Class == 4) {
                count = 1;
            } else if (hand1Class == 3) {
                count = 3;
            } else if (hand1Class == 2) {
                count = 2;
            } else if (hand1Class == 1) {
                count = 2;
            }

            Card highCardInClass1 = hand1.highCard(count);
            Card highCardInClass2 = hand2.highCard(count);

            int highCardInClassCompare = highCardInClass1.compareTo(highCardInClass2);

            if (highCardInClassCompare > 0) {
                return 1;
            } else if (highCardInClassCompare < 0) {
                return 2;
            }

            if (count == 2) { // check lower of two pairs
                Card lowCardInClass1 = hand1.lowCard(count);
                Card lowCardInClass2 = hand2.lowCard(count);

                int lowCardInClassCompare = lowCardInClass1.compareTo(lowCardInClass2);

                if (lowCardInClassCompare > 0) {
                    return 1;
                } else if (lowCardInClassCompare < 0) {
                    return 2;
                }
            }

            if (hand1Class == 6) {
                Card highCardInSecondardClass1 = hand1.highCard(2);
                Card highCardInSecondardClass2 = hand2.highCard(2);

                int highCardInSecondardClassCompare = highCardInSecondardClass1.compareTo(highCardInSecondardClass2);

                if (highCardInSecondardClassCompare > 0) {
                    return 1;
                } else if (highCardInSecondardClassCompare < 0) {
                    return 2;
                }
            }

            Card highCard1 = hand1.highCard(1);
            Card highCard2 = hand2.highCard(1);

            int highCardCompare = highCard1.compareTo(highCard2);

            if (highCardCompare > 0) {
                return 1;
            } else if (highCardCompare < 0) {
                return 2;
            }

            throw new IllegalArgumentException();
        }
    }

    private static class Hand {
        private List<Card> cards;

        public Hand(List<Card> collect) {
            this.cards = collect;
        }

        public boolean isFlush() {
            return cards.stream().map(card -> card.suit).distinct().count() == 1;
        }

        public boolean isStraight() {
            int minIndex = Card.values.indexOf(Card.ordering.min(cards).getValue());
            Set<Character> straightCards = ImmutableSet.copyOf(Iterables.limit(Iterables.skip(Iterables.cycle(Card.values), minIndex), 5));
            Set<Character> handCards = cards.stream().map(Card::getValue).collect(Collectors.toSet());
            return Sets.symmetricDifference(straightCards, handCards).isEmpty();
        }

        public Multiset<Card> asMultiset() {
            TreeMultiset<Card> multiset = TreeMultiset.create();
            for (Card card : cards) {
                multiset.add(card);
            }

            return multiset;
        }

        public static Hand toHand(String handStr) {
            return new Hand(Splitter.on(' ').splitToList(handStr).stream().map(Card::toCard).collect(Collectors.toList()));
        }

        /**
         * - High Card: Highest value card.
         * - One Pair: Two cards of the same value.
         * - Two Pairs: Two different pairs.
         * - Three of a Kind: Three cards of the same value.
         * - Straight: All cards are consecutive values.
         * - Flush: All cards of the same suit.
         * - Full House: Three of a kind and a pair.
         * - Four of a Kind: Four cards of the same value.
         * - Straight Flush: All cards are consecutive values of same suit.
         * - Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.
         */
        public int getHandType() {
            if (isStraight()) {
                if (isFlush()) {
                    if (highCard(1).getValue() == 'A') {
                        return 9; // royal flush
                    } else {
                        return 8; // straight flush;
                    }
                }
            }

            Multiset<Card> multiset = asMultiset();

            Multiset<Integer> countSet = TreeMultiset.create();
            for (Multiset.Entry<Card> entry : multiset.entrySet()) {
                countSet.add(entry.getCount());
            }

            if (countSet.contains(4)) {
                return 7; // four of a kind;
            }

            if (countSet.contains(3) && countSet.contains(2)) {
                return 6; // full house
            }

            if (isFlush()) {
                return 5; // flush
            }

            if (isStraight()) {
                return 4; // straight
            }

            if (countSet.contains(3)) {
                return 3; // three of a kind;
            }

            if (countSet.contains(2)) {
                if (countSet.count(2) == 2) {
                    return 2; // two pair
                } else {
                    return 1; // one pair
                }
            }

            return 0;
        }

        private Card highCard(int withCount) {
            Card high = new Card('S', '2');

            Multiset<Card> characters = asMultiset();
            for (Multiset.Entry<Card> entry : characters.entrySet()) {
                if (entry.getCount() == withCount && entry.getElement().compareTo(high) > 0) {
                    high = entry.getElement();
                }
            }

            return high;
        }

        private Card lowCard(int withCount) {
            Card low = new Card('S', 'A');

            Multiset<Card> characters = asMultiset();
            for (Multiset.Entry<Card> entry : characters.entrySet()) {
                if (entry.getCount() == withCount && entry.getElement().compareTo(low) < 0) {
                    low = entry.getElement();
                }
            }

            return low;
        }
    }

    private static class Card implements Comparable<Card> {
        private final char suit;
        private final char value;
        private static final ImmutableList<Character> values = ImmutableList.of('2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A');
        private static final Ordering<Character> valueOrder = Ordering.explicit(values);
        private static final Ordering<Card> ordering = valueOrder.onResultOf(Card::getValue);

        private char getValue() {
            return value;
        }

        public Card(char suit, char value) {
            this.suit = suit;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Card card = (Card) o;

            return value == card.value;

        }

        @Override
        public int hashCode() {
            return (int) value;
        }

        @Override
        public int compareTo(Card that) {
            return ordering.compare(this, that);
        }

        public static Card toCard(String s) {
            return new Card(s.charAt(1), s.charAt(0));
        }
    }
}
