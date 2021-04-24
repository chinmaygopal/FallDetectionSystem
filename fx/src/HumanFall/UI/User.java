package HumanFall.UI;

import java.util.ArrayList;

public class User {
	String id;
	String name;
	String age;
	String height;
	String weight;
	String gender;
	ArrayList<String> dependents;
	ArrayList<String> doctors;

	public String getId() {
		return id;
	}

	public ArrayList<String> getDoctors() {
		return dependents;
	}

	public void setDoctors(ArrayList<String> doctors) {
		this.doctors = doctors;
	}

	public ArrayList<String> getDependents() {
		return dependents;
	}

	public void setDependents(ArrayList<String> dependents) {
		this.dependents = dependents;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
