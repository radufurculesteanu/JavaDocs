package ro.teamnet.zth.appl.dao;

import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.appl.domain.Department;

import java.util.List;

/**
 * Created by Radu.Furculesteanu on 7/14/2017.
 */
public class DepartmentDao {
    EntityManagerImpl entityManager = new EntityManagerImpl();

    public Department findById(Class<Department> dptClass, Long id)
    {
        return entityManager.findById(dptClass,id);
    }

    public Long getNextIdVal(String columnIdName)
    {
        return entityManager.getNextIdVal("departments", columnIdName);
    }

    public List<Department> findAll(Class<Department> deptClass)
    {
        return entityManager.findAll(deptClass);
    }

    public Department update (Department dept)
    {
        return entityManager.update(dept);
    }

    public void delete(Department dept)
    {
        entityManager.delete(dept);
    }
}
