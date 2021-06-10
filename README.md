# First, what is the Singleton pattern?

The term "Singleton" comes from mathematics and means: a set with only an object.

Before we introduce you the Singleton pattern, you should know that this pattern has a very bad reputation among programmers, in a sense that is considered an anti-Pattern because it is frequently used in scenarios where it is not beneficial, causing unnecessary restrictions in situations where it is not actually required.
With the present, we would like to show you the common situations where a singleton is or not needed, providing some example on how to implement it.

# Which problem aims to resolve?
So what Singleton pattern does is to make a class having a single instance, managed by the class itself and easily accessed by all the other classes.

# When Singleton Pattern is needed
Before showing the singleton, we would like to show the common problems that this pattern aims to resolve.

## Scenario
Imagine having a data resource, like Contacts, which has to be shared by different classes (Phone and Tablets, we will refer to those as client classes).
Here we have our contacts class, that holds a list of contacts.

```java
// Contacts.java

public class Contacts {
	public List<String> names = new ArrayList<>();

	public Contacts() {
		names.add("Morty");
		names.add("President");
		names.add("Rick");
	}
}

```
Now the clients. As in real life, these clients can belong to a person.

```java
// Phone.java
public class Phone {
	public Phone(Contacts contacts) {
		contacts.names.add("Jerry");
	}
}


// Tablet.java
public class Tablet {
	public Tablet(Contacts contacts) {
		contacts.names.add("Beth");
	}
}

// Person.java
public class Person {
	public static void main(String[] args) {
		Contacts contacts = new Contacts();

		Phone phone = new Phone(contacts);
		Tablet tablet = new Tablet(contacts);
	}
}
```

## Problem

Now what we want to do is to put this scenario satisfying these 3 needs:
- we want the class Contacts to have only 1 instance for the whole program.
- we want this instance to be accessed easily by other classes.
- we want that the class is able to control this instantiation.


## Solution 1

The first solution tries to put the Contacts List as static with a public access.

[Solution 1](src/main/java/solution1/Person.java)

This is not a good solution because we are not sure whether another Contacts (able to modify the contact list)
object may be instantiated somewhere.
It is hard to reuse it and test it, because a Contacts instance is needed or we need to create a "unique" instantiation that is passed to each class constructor that need that instance.

[Problem 1.1](src/main/java/solution1/PersonProblem1.java)  
[Problem 1.2](src/main/java/solution1/PersonProblem2.java)

## Solution 2

The second solution is to create a utility class which manage Contacts List as static.

[Solution 2](src/main/java/solution2/Person.java)

### Pitfalls
#### You stop thinking in terms of object
Global Variables broke the aspect of Object Oriented language, so they should not be a solution.

Typically, in a Java application, an utility class should provide services to be used and not
actions on a single static variable that pretends to be unique, like an object would do. So for example if we would like to serialize the Contacts list?

#### The empty constructor is still existing
Our utility class was designed to be non instantiable, by making all its methods static. However, its constructor is still accessible and instantiable.
This class can be unintentionally instantiable.

The singleton enforces non-instantiability by making the constructor  private.

[Problem 2.1](src/main/java/solution2/PersonProblem1.java)


## Solution Singleton
With the Singleton all the requirements are satisfied.

[Solution Singleton](src/main/java/solutionSingleton/Person.java)

An object Contacts cannot be instantiated, meaning that there will always only exist the instance created in the Class accessible through getInstance() method. This also means that each class can access to it using this method but calling the Class.
Also the serialization work because an actual instance of Contacts class does exist.


# Singleton Pattern
We have seen how this pattern can be a solution and when this is the case. But how does it work?
How is it possible to write a general Singleton Pattern? What are the advantages of this pattern
in terms of performance? Is the Singleton okay also for Multithreading?

### How a generic Singleton is written
The Singleton we chose to show you is the so called "Bill Pugh" implementation.
We chose it for its simplicity and because it is natively thread-safe. However, there are different other implementations (won't be discussed here) of this pattern and each one is meant for a specific job.



A generic Bill Pugh Singleton class is written as following:
```java
public class Singleton {
	private Singleton() { }

	private static class Cage {
		private static final Singleton INSTANCE = new Singleton();
	}

	public static Singleton getInstance() {
		return Cage.INSTANCE;
	}

}
```
You can also check [Example Generic Singleton](src/main/java/genericSingleton/Singleton.java)

The constructor is set to private, allowing to not instantiate any object of it and the only instance is set as static and final and trapped in a inner static class called "Cage" (however, it is not mandatory to use this naming).
The instance is retrievable by calling getInstance() static method.


### Thread safety
The Bill Pugh singleton is already **thread safe** in the sense that Multithreading cannot break it, creating more instances, whereas some others implementations are not. This is possible because of the definition of 'static' in java. The inner class and the inner Object load once with the class loader in the JVM.
This avoids a null check before the instantiation of the singleton object as it may be seen in other implementations, which can be problematic in multithread envinroments.   

The advantage is not only that it allows to avoid the use of synchronized methods for instantiation as in a Thread-Safe implementation, but also it is very performant. Synchronizing a method which build instance could in some extreme cases decrease the performance by a factor of 100 or higher!

Unfortunately this does not mean that an attribute contained in our Singleton is Thread-Safe. The methods which access it should be synchronized. Like so:
```java
public synchronized void addMoney (int value) {getInstance().balance += value; }

public synchronized void spendMoney (int value) {getInstance().balance -= value; }
```

An example on the difference between putting this tag or not, can be seen here.

[Singleton Thread Safe](src/main/java/singletonThreadSafe/Runner.java)

[Singleton Not Thread Safe](src/main/java/singletonNotThreadSafe/Runner.java)

### Performance
Among the the different implementations of the singleton patterns, the Bill Pugh solution uses lazy initialized (while there are versions that use eager initializaiton). In this way, the inner class is loaded during runtime, when the getInstance() method is called. With this approach, resources are not wasted if the method is never called (for example, when a certain condition in the program is not met), and the JVM is not slowed during compile time; although, there may be situations where eager initialization suits better.

### Disadvantages
Singleton can stop classes from being reusable and testable.

Making a class as a singleton can make it difficult to test its clients because it’s impossible to substitute a mock implementation for a singleton unless it implements an interface that serves as its type.

Therefore it is very important:
- to reason if this pattern can fit the use case.
- to make the dependent classes methods testable independently of the singleton state.
- to consider dependency injection, either manually (passing an instance variable) or with a framework.
- to consider static methods.



# References
1. https://en.wikipedia.org/wiki/Singleton_pattern
2. http://w3sdesign.com/?gr=c05&ugr=proble#gf
3. Effective Java, third edition. J. Bloch.
4. https://medium.com/@cancerian0684/singleton-design-pattern-and-how-to-make-it-thread-safe-b207c0e7e368
5. https://stackoverflow.com/questions/228164/on-design-patterns-when-should-i-use-the-singleton
6. https://www.reddit.com/r/iOSProgramming/comments/9zxqj1/why_i_cant_use_singleton_why_it_is_bad_design/
7. https://www.geeksforgeeks.org/java-singleton-design-pattern-practices-examples/
