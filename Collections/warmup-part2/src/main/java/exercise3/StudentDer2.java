package exercise3;

/**
 * Created by Radu.Furculesteanu on 7/7/2017.
 */
public class StudentDer2 extends StudentTest{
    public StudentDer2(String firstName, String lastName)
    {
        super(firstName,lastName);
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj == null)
            return false;
        else
        if(obj.getClass().equals(this.getClass()))
        {
            StudentDer2 other = (StudentDer2)obj;
            if(this.getFirstName().equals(other.getFirstName()) && this.getLastName().equals(other.getLastName()))
                return true;
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        int result = 0;
        result += this.getFirstName().hashCode();
        return result;
    }
}
