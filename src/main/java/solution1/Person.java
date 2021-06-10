package solution1;


public class Person {
  public static void main(String[] args) {
    Contacts contacts = new Contacts();

    Phone phone = new Phone(contacts);
    Tablet tablet = new Tablet(contacts);

    System.out.println(contacts);
  }
}
