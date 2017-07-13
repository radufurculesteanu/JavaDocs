package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;
import ro.teamnet.zth.api.database.DBManager;
import ro.teamnet.zth.appl.domain.Department;

import javax.swing.plaf.nimbus.State;
import javax.xml.transform.Result;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Radu.Furculesteanu on 7/13/2017.
 */
public class EntityManagerImpl implements EntityManager{
    @Override
    public <T> T findById(Class<T> entityClass, Long id)
    {
        Connection con = DBManager.getConnection();
        String tableName = EntityUtils.getTableName(entityClass);
        List<ColumnInfo> columnList = EntityUtils.getColumns(entityClass);
        //Field idField = EntityUtils.getFieldsByAnnotations(entityClass, Id.class).get(0);
        //List<Field> fieldList = EntityUtils.getFieldsByAnnotations(entityClass, Column.class);
        Condition condition = new Condition();
        for (ColumnInfo column : columnList) {
            if(column.isId())
            {
                condition.setColumnName(column.getDbColumnName());
                break;
            }
        }
        condition.setValue(id);
        QueryBuilder qb = new QueryBuilder();
        qb.setTableName(tableName);
        qb.addQueryColumns(columnList);
        qb.addCondition(condition);
        qb.setQueryType(QueryType.SELECT);
        String sql = qb.createQuery();

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next())
            {
                T instance = entityClass.newInstance();
                for(ColumnInfo column : columnList)
                {
                    Field field = instance.getClass().getDeclaredField(column.getColumnName());
                    field.setAccessible(true);
                    field.set(instance,EntityUtils.castFromSqlType(rs.getObject(column.getDbColumnName()),field.getType()));
                }
                return instance;
            }
            con.close();
        }
        catch (SQLException e) {
            System.out.println("Unable to create statement!");
            System.exit(1);
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (InstantiationException e) {
            e.printStackTrace();
        }
        catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Long getNextIdVal(String tableName, String columnIdName) {
        Connection con = DBManager.getConnection();
        try
        {
            Statement stmt = con.createStatement();
            String sql = "SELECT MAX( " + columnIdName + ")+1 FROM " + tableName + "";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next())
            {
                Long result = rs.getLong(1);
                return result;
            }
            con.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> Object insert(T entity) {
        try
        {
            Connection con = DBManager.getConnection();
            String tableName = EntityUtils.getTableName(entity.getClass());
            List<ColumnInfo> columnList = EntityUtils.getColumns(entity.getClass());
            for(ColumnInfo column : columnList)
            {
                if(column.isId())
                {
                    column.setValue(getNextIdVal(tableName,column.getDbColumnName()));
                }
                else
                {
                    Field field = entity.getClass().getDeclaredField(column.getColumnName());
                    field.setAccessible(true);
                    column.setValue(field.get(entity));
                }
            }
            QueryBuilder qb = new QueryBuilder();
            qb.setTableName(tableName);
            qb.setQueryType(QueryType.INSERT);
            qb.addQueryColumns(columnList);
            String sql = qb.createQuery();
            Statement stmt = con.createStatement();
            stmt.executeQuery(sql);
            return findById(entity.getClass(),(Long)columnList.get(0).getValue());
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> List<T> findAll(Class<T> entityClass) {
        try
        {
            Connection con = DBManager.getConnection();
            String tableName = EntityUtils.getTableName(entityClass);
            List<ColumnInfo> columnList = EntityUtils.getColumns(entityClass);
            QueryBuilder qb = new QueryBuilder();
            qb.setTableName(tableName);
            qb.addQueryColumns(columnList);
            qb.setQueryType(QueryType.SELECT);
            String sql = qb.createQuery();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ArrayList<T> list = new ArrayList<T>();
            while(rs.next())
            {
                T instance = entityClass.newInstance();
                for(ColumnInfo column : columnList)
                {
                    Field field = instance.getClass().getDeclaredField(column.getColumnName());
                    field.setAccessible(true);
                    field.set(instance,EntityUtils.castFromSqlType(rs.getObject(column.getDbColumnName()),field.getType()));
                }
                list.add(instance);

            }
            return list;
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
        EntityManagerImpl entityManager = new EntityManagerImpl();
        Long id = new Long(50);
        System.out.println(entityManager.getNextIdVal("employees","employee_id"));
        /*Department dpt = new Department();
        dpt.setDepartmentName("Accounting2");
        System.out.println(entityManager.insert(dpt));*/
        List<Department> departments = entityManager.findAll(Department.class);
        for(Department department : departments)
        {
            System.out.println(department);
        }
    }
}
