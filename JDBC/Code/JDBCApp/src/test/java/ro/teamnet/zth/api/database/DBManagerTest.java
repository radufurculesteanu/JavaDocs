package ro.teamnet.zth.api.database;

import org.junit.Assert;
import org.junit.Test;
import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.appl.domain.Department;

import java.util.List;

/**
 * Created by Radu.Furculesteanu on 7/13/2017.
 */
public class DBManagerTest {
    @Test
    public void findByIdTest()throws Exception
    {
        EntityManagerImpl entityManager = new EntityManagerImpl();
        Object department = entityManager.findById(Department.class,new Long(50));
        Assert.assertEquals("Object should be department", Department.class,department.getClass());
    }

    @Test
    public void getNextIdValTest() throws Exception
    {
        EntityManagerImpl entityManager = new EntityManagerImpl();
        long id = entityManager.getNextIdVal("locations","location_id");
        Assert.assertEquals("ID should be 3201",3201l,id);
    }

    @Test
    public void getConnectionTest() throws Exception
    {
        int result = DBManager.checkConnection(DBManager.getConnection());
        Assert.assertEquals("Result should be 1", 1,result);

    }

    @Test
    public void insertTest() throws Exception
    {
        EntityManagerImpl entityManager = new EntityManagerImpl();
        Department dept = new Department();
        dept.setDepartmentName("Radu_Dept");
        dept.setId(300l);
        dept.setLocation(1700l);

        Department department = (Department)entityManager.insert(dept);

        Assert.assertEquals("Obj should be department","Radu_Dept",department.getDepartmentName());
    }

    @Test
    public void findAllTest()throws Exception
    {
        EntityManagerImpl entityManager = new EntityManagerImpl();
        List<Department> departments = entityManager.findAll(Department.class);

        Assert.assertEquals("List size should be 27",27,departments.size());
    }
}
