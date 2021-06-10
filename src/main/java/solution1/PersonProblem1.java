package solution1;

public class PersonProblem1 {

  public static void main(String[] args) {
    Contacts contacts = new Contacts();

    Phone phone = new Phone(contacts);
    Tablet tablet = new Tablet(contacts);

    // we are able to create other instantiations of Contacts
    // which load the same contacts again
    Contacts contacts1 = new Contacts();
    Contacts contacts2 = new Contacts();

    System.out.println(contacts);
  }
}
