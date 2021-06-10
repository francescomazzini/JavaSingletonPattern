package solution2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PersonProblem1 {

  public static void main(String[] args) {

    Phone phone = new Phone();
    Tablet tablet = new Tablet();

    // Dangerous! The constructor must not be instantiable.
    Contacts contacts = new Contacts();

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    String json = gson.toJson(contacts);

    System.out.println(json);
  }
}
