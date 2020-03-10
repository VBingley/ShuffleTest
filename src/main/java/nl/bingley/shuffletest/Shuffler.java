package nl.bingley.shuffletest;

import java.util.ArrayList;
import java.util.List;

public class Shuffler {

  private final int deckSize;

  public Shuffler(int deckSize) {
    this.deckSize = deckSize;
  }

  public void testTrueShuffle() {
    List<Deck> decks = new ArrayList<>();
    for (int j = 0; j < 5000; j++) {
      Deck deck = new Deck(deckSize);
      decks.add(deck.trueShuffle());
    }
    logDeckState(-1, decks);
  }

  public void testOverhandShuffleBestSet() {
    for (int maxSet = 2; maxSet < 51; maxSet++) {
      List<Deck> decks = new ArrayList<>();
      for (int j = 0; j < 5000; j++) {
        Deck deck = new Deck(deckSize);
        decks.add(deck.overhandShuffle(20, maxSet));
      }
      logDeckState(maxSet, decks);
    }
  }

  public void testOverhandShuffleTimes() {
    for (int times = 20; times <= 500; times += 20) {
      List<Deck> decks = new ArrayList<>();
      for (int j = 0; j < 5000; j++) {
        Deck deck = new Deck(deckSize);
        decks.add(deck.overhandShuffle(times, 6));
      }
      logDeckState(times, decks);
    }
  }

  public void testRiffleShuffleBestSet() {
    for (int maxSet = 1; maxSet <= 20; maxSet++) {
      List<Deck> decks = new ArrayList<>();
      for (int i = 0; i < 5000; i++) {
        Deck deck = new Deck(deckSize);
        decks.add(deck.riffleShuffle(10, maxSet));
      }
      logDeckState(maxSet, decks);
    }
  }

  public void testRiffleShuffleTimes() {
    for (int times = 1; times <= 20; times++) {
      List<Deck> decks = new ArrayList<>();
      for (int i = 0; i < 5000; i++) {
        Deck deck = new Deck(deckSize);
        decks.add(deck.riffleShuffle(times, 3));
      }
      logDeckState(times, decks);
    }
  }

  public void testOverUnderShuffleBestSet() {
    for (int maxSet = 1; maxSet <= 20; maxSet++) {
      List<Deck> decks = new ArrayList<>();
      for (int i = 0; i < 5000; i++) {
        Deck deck = new Deck(deckSize);
        decks.add(deck.overUnderShuffle(10, maxSet));
      }
      logDeckState(maxSet, decks);
    }
  }

  public void testOverUnderShuffleTimes() {
    for (int times = 1; times <= 20; times++) {
      List<Deck> decks = new ArrayList<>();
      for (int i = 0; i < 5000; i++) {
        Deck deck = new Deck(deckSize);
        decks.add(deck.overUnderShuffle(times, 4));
      }
      logDeckState(times, decks);
    }
  }

  private void logDeckState(int counter, List<Deck> decks) {
    System.out.println(
        "counter: " + counter
            + " ordered: " + Verifier.countOrdered(decks)
            + " deterministic1: " + Verifier.calculateDeterministicValue(decks)
            + " deterministic2: " + Verifier.calculateDeckAverageDeviation(decks));
  }
}
