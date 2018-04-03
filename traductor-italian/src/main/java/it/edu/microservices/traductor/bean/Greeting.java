package it.edu.microservices.traductor.bean;

public class Greeting {

	public String greeting;
	
	public int counter;
	
	public String language;

	public Greeting(String greeting, int counter, String language) {
		super();
		this.greeting = greeting;
		this.counter = counter;
		this.language = language;
	}

	public Greeting() {
	}

	public String getGreeting() {
		return greeting;
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	@Override
	public String toString() {
		return "Greeting [greeting=" + greeting + ", counter=" + counter + ", language=" + language + "]";
	}

}
