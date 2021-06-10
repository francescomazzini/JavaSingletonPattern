package solutionSingleton;

public class Phone {
  public Phone() {
    Contacts.getInstance().addName("Jerry");
  }
}
