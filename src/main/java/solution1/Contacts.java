package solution1;

import java.util.ArrayList;
import java.util.List;

public class Contacts {
  private static List<String> names = new ArrayList<>();

  public Contacts() {
    names.add("Morty");
    names.add("President");
    names.add("Rick");
  }

  public void addName(String name) {
    names.add(name);
  }

  @Override
  public String toString() {
    return names.toString();
  }
}
