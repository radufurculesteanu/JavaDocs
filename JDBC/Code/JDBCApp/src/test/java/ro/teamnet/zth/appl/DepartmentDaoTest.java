package ro.teamnet.zth.appl;

import org.junit.Assert;
import org.junit.Test;
import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.appl.dao.DepartmentDao;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Location;

import java.util.List;

/**
 * Created by Radu.Furculesteanu on 7/14/2017.
 */
public class DepartmentDaoTest {
    @Test
    public void findByIdTest()throws Exception
    {
        DepartmentDao dptDao = new DepartmentDao();
        Department department = dptDao.findById(Department.class,new Long(50));
        Assert.assertEquals("Object should be department", Department.class,department.getClass());
    }

    @Test
    public void getNextIdValTest() throws Exception
    {
        DepartmentDao dptDao = new DepartmentDao();
        long result = dptDao.getNextIdVal("department_id");
        Assert.assertEquals("Result should be 272", 272l,result);
    }

    @Test
    public void findAllTest() throws Exception
    {
        DepartmentDao dptDao = new DepartmentDao();
        List<Department> deptList = dptDao.findAll(Department.class);
        Assert.assertEquals("Result should be 272", 28,deptList.size());
    }

    @Test
    public void updateTest() throws Exception
    {
        DepartmentDao dptDao = new DepartmentDao();
        Department dpt = new Department();
        dpt.setId(271l);
        dpt.setLocation(1700l);
        dpt.setDepartmentName("Radu_Dept2");
        Department dept = dptDao.update(dpt);
        Assert.assertEquals("Result should be Radu_Dept2", "Radu_Dept2",dept.getDepartmentName());
    }

    @Test
    public void deleteTest() throws Exception
    {
        DepartmentDao dptDao = new DepartmentDao();
        Department dpt = new Department();
        dpt.setId(271l);
        dptDao.delete(dpt);
        Department dptTest = dptDao.findById(Department.class,dpt.getId());
        Assert.assertEquals("dpt should be null", null, dptTest);
    }
}
