package singletonThreadSafe;

public class Money {

  private int money;

  private Money() {
    money = 300;
  }

  private static class Cage {
    private static final Money INSTANCE = new Money();
  }

  public static Money getInstance() {
    return Money.Cage.INSTANCE;
  }

  public synchronized void addMoney(int value) {
    getInstance().money += value;
  }

  public synchronized void spendMoney(int value) {
    getInstance().money -= value;
  }

  public int getMoney() {
    return getInstance().money;
  }

  @Override
  public String toString() {
    final String CURRENCY = "$";
    return "" + getMoney() + CURRENCY;
  }
}
