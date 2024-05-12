package s24_IgnoreEmptyNullDefaultValuesInPayload;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

//@JsonInclude(JsonInclude.Include.NON_DEFAULT) // Ignore default values
//@JsonInclude(JsonInclude.Include.NON_NULL) // Ignore null values
@JsonInclude(JsonInclude.Include.NON_EMPTY)  // Ignore empty values
public class EmployeePOJOClass {
	
	private String firstName;
	private String lastName;
	private String gender;
	private int age;
	private double salary;
	private boolean isMarried;
	private String[] hobbies;
	private List<String> degrees;
	private Map<String, String> familyMembers;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public boolean isMarried() {
		return isMarried;
	}
	public void setMarried(boolean isMarried) {
		this.isMarried = isMarried;
	}
	public String[] getHobbies() {
		return hobbies;
	}
	public void setHobbies(String[] hobbies) {
		this.hobbies = hobbies;
	}
	public List<String> getDegrees() {
		return degrees;
	}
	public void setDegrees(List<String> degrees) {
		this.degrees = degrees;
	}
	public Map<String, String> getFamilyMembers() {
		return familyMembers;
	}
	public void setFamilyMembers(Map<String, String> familyMembers) {
		this.familyMembers = familyMembers;
	}
	
	
}
