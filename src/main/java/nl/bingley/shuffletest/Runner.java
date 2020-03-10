package nl.bingley.shuffletest;

public class Runner {

  public static void main(String[] args) {
    final Shuffler shuffler = new Shuffler(52);
    shuffler.testTrueShuffle();
    shuffler.testRiffleShuffleTimes();
  }
}
