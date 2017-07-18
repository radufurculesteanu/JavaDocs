package ro.teamnet.zth.web;

import java.util.Comparator;

/**
 * Created by Radu.Furculesteanu on 7/18/2017.
 */
public class Person implements Comparable{
    private String firstName;
    private String lastName;
    private Long age;
    private boolean married;

    public Person(String firstName, String lastName, Long age, boolean married) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.married = married;
    }

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

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    @Override
    public String toString()
    {
        return "First name: " + this.firstName + ", Last name: " + this.lastName + ", Age: " + this.age + ", is married: " + this.married;
    }


    @Override
    public int compareTo(Object o) {
        Person other = (Person)o;
        if(this.age > other.age)
            return 1;
        else if (this.age < other.age)
            return -1;
        else
            return this.firstName.compareTo(other.firstName);
    }
}
