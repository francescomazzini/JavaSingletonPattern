package solution2;

public class Person {
  public static void main(String[] args) {

    Phone phone = new Phone();
    Tablet tablet = new Tablet();

    // Not possible to override toString().
    System.out.println(Contacts.stringify());
  }
}
