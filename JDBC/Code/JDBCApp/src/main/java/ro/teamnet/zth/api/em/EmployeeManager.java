package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.database.DBManager;
import ro.teamnet.zth.appl.domain.Employee;

import javax.swing.plaf.nimbus.State;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Radu.Furculesteanu on 7/14/2017.
 */
public class EmployeeManager {
    public List<Employee> findEmpByDept(Class<Employee> employeeClass, String string)
    {
        try
        {
            Connection con = DBManager.getConnection();
            String tableName = EntityUtils.getTableName(employeeClass);
            List<ColumnInfo> columnList = EntityUtils.getColumns(employeeClass);
            String sql = new String("select * from " + tableName + " where department_id in (select department_id from " +
                    "departments where lower(department_name) like '%" + string + "%')");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ArrayList<Employee> employeeList = new ArrayList<Employee>();
            while(rs.next())
            {
                Employee emp = employeeClass.newInstance();
                for (ColumnInfo column : columnList)
                {
                    Field field = emp.getClass().getDeclaredField(column.getColumnName());
                    field.setAccessible(true);
                    field.set(emp,EntityUtils.castFromSqlType(rs.getObject(column.getDbColumnName()),field.getType()));
                }
                employeeList.add(emp);
            }
            return employeeList;
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        EmployeeManager employeeManager = new EmployeeManager();
        List<Employee> employeeList = employeeManager.findEmpByDept(Employee.class,"co");
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }
}
