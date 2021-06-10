package genericSingleton;

/**
 * Bill Pugh genericSingleton.Singleton Implementation. The object is instantiated inside a "Cage"
 * and its instance can be accessed from getInstance.
 */
public class Singleton {
  private Singleton() {}

  /** Ensures INSTANCE remains in a private context, like it was in a cage. */
  private static class Cage {
    private static final Singleton INSTANCE = new Singleton();
  }

  /**
   * Returns singleton instance.
   *
   * @return singleton INSTANCE.
   */
  public static Singleton getInstance() {
    return Cage.INSTANCE;
  }
}
