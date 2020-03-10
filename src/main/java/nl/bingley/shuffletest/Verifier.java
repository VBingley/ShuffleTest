package nl.bingley.shuffletest;

import java.util.ArrayList;
import java.util.List;

public abstract class Verifier {

  private Verifier() {
    //Hide public constructor
  }

  /**
   * Count the amount of cards that are still in order after shuffling.
   *
   * @param decks The decks to compare
   * @return Lower is better
   */
  public static double countOrdered(List<Deck> decks) {
    List<Integer> ordered = new ArrayList<>();
    for (Deck deck : decks) {
      int previous = -1;
      int counter = 0;
      for (Integer card : deck.getCards()) {
        if (card == previous + 1) {
          counter++;
        }
        previous = card;
      }
      ordered.add(counter);
    }
    return ordered.stream()
        .mapToDouble(i -> i)
        .average().orElse(-1);
  }

  public static double calculateDeckAverageDeviation(List<Deck> decks) {
    List<Double> deviations = new ArrayList<>();
    for (Deck deck : decks) {
      double averageFirstHalf = deck.getCards().subList(0, deck.size() / 2 - 1).stream()
          .mapToInt(Integer::intValue)
          .average().orElse(-1);
      double averageSecondHalf = deck.getCards().subList(deck.size() / 2 - 1, deck.size() - 1).stream()
          .mapToInt(Integer::intValue)
          .average().orElse(-1);
      double averageDeviation = (Math.abs(deck.size() / (double) 2 - averageFirstHalf)
          + Math.abs(deck.size() / (double) 2 - averageSecondHalf)) / 2;
      deviations.add(averageDeviation);
    }
    return deviations.stream()
        .mapToDouble(Double::doubleValue)
        .average().orElse(-1);
  }

  /**
   * Calculate how deterministic a shuffling method is by calculating what the average card number is in each position
   * of the deck.
   * <p>
   * If a given position of the deck has a higher or lower average card number than the overall average card number
   * the deck isn't well randomized.
   *
   * @param decks The decks to compare
   * @return Lower is better
   */
  public static double calculateDeterministicValue(List<Deck> decks) {
    List<Integer> cardTotals = decks.remove(0).getCards();
    for (Deck deck : decks) {
      for (int i = 0; i < deck.size(); i++) {
        cardTotals.set(i, cardTotals.get(i) + deck.getCard(i));
      }
    }
    return cardTotals.stream()
        .map(integer -> (double) integer / decks.size())
        .map(number -> Math.abs((decks.get(0).size() / (double) 2) - number))
        .mapToDouble(value -> value)
        .average().orElse(-1);
  }
}
