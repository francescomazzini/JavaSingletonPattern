package solutionSingleton;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Person {
  public static void main(String[] args) {

    Phone phone = new Phone();
    Tablet tablet = new Tablet();

    Contacts.getInstance().addName("Squanchy");

    System.out.println(Contacts.getInstance() + "\n");

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    String json = gson.toJson(Contacts.getInstance());

    System.out.println(json + "\n");

    Contacts contact1 = Contacts.getInstance();
    Contacts contact2 = Contacts.getInstance();

    System.out.println(
        "Hash Code of 1: " + contact1.hashCode() + "\nHash Code of 2: " + contact2.hashCode());

    /*

    we can try to instantiate it more than once but it is not possible

    Contacts contacts = new Contacts();
    System.out.println(contacts.getNames()); */

  }
}
