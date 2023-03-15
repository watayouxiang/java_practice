package com.watayouxiang.myjava.reflect;

public class Person {
	public int age;
	private String name;

	public Person() {
		super();
	}

	public Person(int age, String name) {
		super();
		this.age = age;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [age=" + age + ", name=" + name + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private void say(String txt) {
		System.out.println(txt);
	}

}
