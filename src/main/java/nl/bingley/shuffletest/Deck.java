package nl.bingley.shuffletest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Deck {

  private List<Integer> cards;

  public Deck(int deckSize) {
    cards = new ArrayList<>();
    for (int i = 1; i <= deckSize; i++) {
      cards.add(i);
    }
  }

  public IntStream stream() {
    return cards.stream().mapToInt(Integer::intValue);
  }

  public List<Integer> getCards() {
    return cards;
  }

  public int getCard(int index) {
    return cards.get(index);
  }

  public int size() {
    return cards.size();
  }

  public Deck trueShuffle() {
    Collections.shuffle(cards);
    return this;
  }

  public Deck overhandShuffle(int times, int maxSet) {
    Random random = new Random();
    for (int i = 0; i < times; i++) {
      List<Integer> shuffled = new ArrayList<>();
      while (!cards.isEmpty()) {
        int rint = random.nextInt(maxSet);
        rint = Math.min(rint, cards.size());
        while (rint > 0) {
          shuffled.add(cards.remove(cards.size() - rint));
          rint--;
        }
      }
      cards = shuffled;
    }
    return this;
  }

  public Deck overUnderShuffle(int times, int maxSet) {
    Random random = new Random();
    for (int i = 0; i < times; i++) {
      List<Integer> shuffled = new ArrayList<>();
      while (!cards.isEmpty()) {
        int rint = 1 + random.nextInt(maxSet);
        rint = Math.min(rint, cards.size());
        while (rint > 0) {
          shuffled.add(cards.remove(cards.size() - rint));
          rint--;
        }
        rint = 1 + random.nextInt(maxSet);
        rint = Math.min(rint, cards.size());
        while (rint > 0) {
          shuffled.add(cards.remove(0));
          rint--;
        }
      }
      cards = shuffled;
    }
    return this;
  }

  public Deck riffleShuffle(int times, int maxSet) {
    Random random = new Random();
    for (int i = 0; i < times; i++) {
      int split = ((cards.size() - 1) / 2) - 2 + random.nextInt(5);
      List<Integer> shuffled = new ArrayList<>(cards.subList(split, cards.size() - 1));
      cards.removeAll(shuffled);
      int index = 0;
      while (cards.size() > 0) {
        index += 1 + random.nextInt(maxSet);
        index = Math.min(index, shuffled.size());
        shuffled.add(index, cards.remove(0));
      }
      cards = shuffled;
    }
    return this;
  }
}
