package exercise3;

/**
 * Created by Radu.Furculesteanu on 7/7/2017.
 */
public class StudentTest {
    private String firstName;
    private String lastName;

    public StudentTest(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public void setFirstName(String value)
    {
        this.firstName = value;
    }

    public void setLastName(String value)
    {
        this.lastName = value;
    }

    @Override
    public String toString()
    {
        return new String("First name: " + this.getFirstName() + ", Last Name: " + this.getLastName());
    }
}
