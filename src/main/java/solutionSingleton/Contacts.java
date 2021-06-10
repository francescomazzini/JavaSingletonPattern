package solutionSingleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Bill Pugh genericSingleton.Singleton Implementation. The object is instantiated inside a "Cage"
 * and its instance can be accessed from getInstance.
 */
public class Contacts {

  private final List<String> names;
  private final int MAX_CONTACTS = 125;

  private Contacts() {
    names = new ArrayList<>();
    names.add("Morty");
    names.add("President");
    names.add("Rick");
  }

  /** Ensures INSTANCE remains in a private context, like it was in a cage. */
  private static class ContactsCage {
    private static final Contacts INSTANCE = new Contacts();
  }

  /**
   * Returns singleton instance.
   *
   * @return singleton INSTANCE.
   */
  public static Contacts getInstance() {
    return ContactsCage.INSTANCE;
  }

  public List<String> getNames() {
    return getInstance().names;
  }

  public void addName(String name) {
    getInstance().names.add(name);
  }

  @Override
  public String toString() {
    return "Contact saved: "
        + Contacts.getInstance().names
        + "\nYou can save up to "
        + (Contacts.getInstance().MAX_CONTACTS - Contacts.getInstance().names.size())
        + " contacts";
  }
}
