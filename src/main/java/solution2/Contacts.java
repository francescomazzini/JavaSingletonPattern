package solution2;

import java.util.ArrayList;
import java.util.List;

public class Contacts {

  private static List<String> names = new ArrayList<>(List.of("Morty", "President", "Rick"));
  private static int MAX_CONTACTS = 125;

  public static void addName(String name) {
    names.add(name);
  }

  public static List<String> getNames() {
    return names;
  }

  public static int getContactsNumber() {
    return names.size();
  }

  public static String stringify() {
    return "Contact saved: "
        + Contacts.getNames()
        + "\nYou can still save up to "
        + (MAX_CONTACTS - getContactsNumber())
        + " contacts.";
  }
}
