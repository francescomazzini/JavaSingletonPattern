package solution1;

public class PersonProblem2 {

  public static void main(String[] args) {
    Contacts contacts = new Contacts();

    Phone phone = new Phone(contacts);
    Tablet tablet = new Tablet(contacts);

    Contacts contacts1 = new Contacts();

    // we are able to modify the list from other instantiations
    contacts1.addName("Squanchy");

    System.out.println(contacts);
  }
}
