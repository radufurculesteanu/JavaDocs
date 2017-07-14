package ro.teamnet.zth.api.em;

import com.sun.org.apache.xpath.internal.functions.FuncUnparsedEntityURI;
import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;
import ro.teamnet.zth.api.database.DBManager;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Employee;
import ro.teamnet.zth.appl.domain.Location;

import javax.swing.plaf.nimbus.State;
import javax.swing.text.html.parser.Entity;
import javax.xml.transform.Result;
import java.lang.reflect.Field;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
                con.close();
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
            con.close();
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

    @Override
    public <T> T update (T entity)
    {
        try
        {
            Connection con = DBManager.getConnection();
            String tableName = EntityUtils.getTableName(entity.getClass());
            List<ColumnInfo> columnList = EntityUtils.getColumns(entity.getClass());
            for(ColumnInfo column : columnList)
            {
                Field field = entity.getClass().getDeclaredField(column.getColumnName());
                field.setAccessible(true);
                column.setValue(field.get(entity));
            }
            Condition cond = new Condition();
            Long id = (Long)columnList.get(0).getValue();
            cond.setColumnName(columnList.get(0).getDbColumnName());
            cond.setValue(id);
            QueryBuilder qb = new QueryBuilder();
            qb.setTableName(tableName);
            qb.addQueryColumns(columnList);
            qb.addCondition(cond);
            qb.setQueryType(QueryType.UPDATE);
            String sql = qb.createQuery();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            return (T)findById(entity.getClass(),id);

        }
        catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(Object entity) {
        try
        {
            Connection con = DBManager.getConnection();
            String tableName = EntityUtils.getTableName(entity.getClass());
            List<ColumnInfo> columnList = EntityUtils.getColumns(entity.getClass());
            for (ColumnInfo column : columnList)
            {
                Field field = entity.getClass().getDeclaredField(column.getColumnName());
                field.setAccessible(true);
                column.setValue(field.get(entity));
            }
            Condition cond = new Condition();
            Long id = (Long)columnList.get(0).getValue();
            cond.setColumnName(columnList.get(0).getDbColumnName());
            cond.setValue(id);

            QueryBuilder qb = new QueryBuilder();
            qb.setTableName(tableName);
            qb.addQueryColumns(columnList);
            qb.addCondition(cond);
            qb.setQueryType(QueryType.DELETE);
            String sql = qb.createQuery();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            con.close();
        }
        catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public <T> List<T> findByParams(Class<T> entityClass, Map<String, Object> params)
    {
        try
        {
            Connection con = DBManager.getConnection();
            String tableName = EntityUtils.getTableName(entityClass);
            List<ColumnInfo> columnList = EntityUtils.getColumns(entityClass);
            /*for (ColumnInfo column : columnList)
            {
                Field field = entityClass.getDeclaredField(column.getColumnName());
                field.setAccessible(true);
                column.setValue(field.get(entityClass));
            }*/
            QueryBuilder qb = new QueryBuilder();
            qb.setTableName(tableName);
            qb.addQueryColumns(columnList);
            qb.setQueryType(QueryType.SELECT);
            Set<Map.Entry<String,Object>> entrySet = params.entrySet();
            for (Map.Entry<String, Object> entry : entrySet) {
                Condition condition = new Condition();
                condition.setColumnName(entry.getKey());
                condition.setValue(entry.getValue());
                qb.addCondition(condition);
            }
            String sql = qb.createQuery();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ArrayList<T> list = new ArrayList<T>();
            while(rs.next())
            {
                T instance = entityClass.newInstance();
                for (ColumnInfo column : columnList)
                {
                    Field field = instance.getClass().getDeclaredField(column.getColumnName());
                    field.setAccessible(true);
                    field.set(instance,EntityUtils.castFromSqlType(rs.getObject(column.getDbColumnName()),field.getType()));
                }
                list.add(instance);
            }
            con.close();
            return list;

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return null;
    }

    public <T> List<T> insertMultiple(List<T> entityList)
    {
        List<T> insertedElements = new ArrayList<T>();
        Connection con = DBManager.getConnection();
        try
        {
            con.setAutoCommit(false);
            for (T entity : entityList)
            {
                String tableName = EntityUtils.getTableName(entity.getClass());
                List<ColumnInfo> columnList = EntityUtils.getColumns(entity.getClass());
                for (ColumnInfo column : columnList)
                {
                    if(column.isId())
                    {
                        Long id = getNextIdVal(tableName,column.getDbColumnName());
                        column.setValue(id);
                        Field field = entity.getClass().getDeclaredField(column.getColumnName());
                        field.setAccessible(true);
                        field.set(entity,id);
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
                qb.addQueryColumns(columnList);
                qb.setQueryType(QueryType.INSERT);
                String sql = qb.createQuery();
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql);
                insertedElements.add(entity);
            }
            con.commit();
            con.close();
            return insertedElements;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            try
            {
                con.rollback();
            }
            catch (SQLException sqle)
            {
                sqle.printStackTrace();
            }
            e.printStackTrace();
        }

        return null;
    }


    public static void main(String[] args) throws ParseException {
        EntityManagerImpl entityManager = new EntityManagerImpl();
        Department dpt = new Department();
        dpt.setDepartmentName("Radu Department"); dpt.setLocation(1700l); dpt.setId(301l);
        Location loc = new Location();
        loc.setStreetAddress("Aleea glumelor nr. 99"); loc.setStateProvince("Haha"); loc.setPostalCode("085471");
        loc.setCity("Bucuresti"); loc.setId(3500l);
        List<Object> elements = new ArrayList<Object>();
        elements.add(dpt); elements.add(loc);

        List<Object> resultList = entityManager.insertMultiple(elements);
        for (Object obj : resultList)
        {
            if(obj instanceof Department)
            {
                Department depRes = (Department)obj;
                System.out.println(depRes);
            }
            if(obj instanceof Location)
            {
                Location locRes = (Location)obj;
                System.out.println(locRes);
            }
        }
    }
}
