package ro.teamnet.zth.api.database;

import org.junit.Assert;
import org.junit.Test;
import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.api.em.EntityUtils;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Location;

import java.util.*;

/**
 * Created by Radu.Furculesteanu on 7/14/2017.
 */
public class EntityManagerImplTest {
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

        Assert.assertEquals("List size should be 29",29,departments.size());
    }

    @Test
    public void updateTest() throws Exception
    {
        EntityManagerImpl entityManager = new EntityManagerImpl();
        Department dpt = new Department();
        dpt.setId(272l);
        dpt.setDepartmentName("Mircea_Dept");
        dpt.setLocation(1700l);
        Department dpt2 = entityManager.update(dpt);
        Department dpt3 = new Department();
        dpt3.setId(273l);
        dpt3.setDepartmentName("Maria_Dept");
        dpt3.setLocation(1700l);
        Department dpt4 = entityManager.update(dpt3);
        Assert.assertEquals("Dept. name should be Mircea_Dept", "Mircea_Dept",dpt2.getDepartmentName());
        Assert.assertEquals("Dept. name should be Maria_Dept", "Maria_Dept",dpt4.getDepartmentName());
    }

    @Test
    public void deleteTest() throws Exception
    {
        EntityManagerImpl entityManager = new EntityManagerImpl();
        Department dpt = new Department();
        dpt.setId(273l);
        entityManager.delete(dpt);
        Department dptTest = entityManager.findById(Department.class,dpt.getId());
        Assert.assertEquals("dpt should be null", null, dptTest);
    }

    @Test
    public void findByParamstest()throws Exception
    {
        EntityManagerImpl entityManager = new EntityManagerImpl();
        Map<String,Object> hashmap = new HashMap<String,Object>();
        hashmap.put("city", "Roma");
        hashmap.put("postal_code", "00989");

        List<Location> locationList = entityManager.findByParams(Location.class,hashmap);
        Assert.assertEquals("List size should be 2",2,locationList.size());
    }
}
