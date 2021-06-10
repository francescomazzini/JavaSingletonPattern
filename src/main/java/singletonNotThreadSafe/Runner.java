package singletonNotThreadSafe;

public class Runner {

  public static void main(String[] args) throws InterruptedException {

    final int CYCLES = 15;

    Thread t1 =
        new Thread(
            () -> {
              for (int i = 0; i < CYCLES; i++) {
                Money.getInstance().addMoney(50);
                System.out.println("Just added 50$. Balance: " + Money.getInstance().toString());

                try {
                  Thread.sleep(10);
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
              }
            });

    Thread t2 =
        new Thread(
            () -> {
              for (int i = 0; i < CYCLES; i++) {
                Money.getInstance().spendMoney(20);
                System.out.println("Just removed 20$. Balance: " + Money.getInstance().toString());

                try {
                  Thread.sleep(10);
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
              }
            });

    t1.start();
    t2.start();

    t1.join();
    t2.join();

    System.out.println("\n");

    System.out.println(
        "First Thread has added "
            + (50 * CYCLES)
            + "$ to 300$, result: "
            + (50 * CYCLES + 300)
            + "$"
            + "\nSecondThread has removed "
            + (20 * CYCLES)
            + "$ to "
            + (50 * CYCLES + 300)
            + "$, result: "
            + ((50 * CYCLES + 300) - (20 * CYCLES))
            + "$"
            + "\n\nExpected balance to be "
            + ((50 * CYCLES + 300) - (20 * CYCLES))
            + "$\nReal value: "
            + Money.getInstance().toString());
  }
}
