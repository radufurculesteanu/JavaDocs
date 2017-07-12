package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

import javax.naming.OperationNotSupportedException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by Radu.Furculesteanu on 7/12/2017.
 */
public class EntityUtils {
    private EntityUtils() throws OperationNotSupportedException
    {
        throw new OperationNotSupportedException();
    }

    public static String getTableName(Class entity)
    {
        Table annotation = (Table)entity.getAnnotation(Table.class);
        if(annotation.name() != null)
            return annotation.name();
        else return entity.getName();
    }

    public static ArrayList<ColumnInfo> getColumns(Class entity)
    {
        ArrayList<ColumnInfo> columnList = new ArrayList<ColumnInfo>();
        Field[] fields = entity.getDeclaredFields();
        for(Field field : fields)
        {
            if(field.isAnnotationPresent(Id.class))
            {
                Id annotation = (Id)entity.getAnnotation(Id.class);
                ColumnInfo ci = new ColumnInfo();
                ci.setColumnName(field.getName());
                ci.setColumnType(field.getClass());
                ci.setId(true);
                ci.setDbColumnName(annotation.name());
                columnList.add(ci);
            }
            else if(field.isAnnotationPresent(Column.class))
            {
                Column annotation = (Column)entity.getAnnotation(Column.class);
                ColumnInfo ci = new ColumnInfo();
                ci.setColumnName(field.getName());
                ci.setColumnType(field.getClass());
                ci.setId(false);
                ci.setDbColumnName(annotation.name());
                columnList.add(ci);
            }
        }
        return columnList;
    }

    public static Object castFromSqlType(Object value, Class wantedType)
    {
        if (value.getClass().equals(BigInteger.class))
        {
            if(wantedType.getClass().equals(Integer.class))
                return (Integer)value;
            else if(wantedType.getClass().equals(Long.class))
                return (Long)value;
            else if(wantedType.getClass().equals(Float.class))
                return (Float)value;
            else if(wantedType.getClass().equals(Double.class))
                return (Double)value;
            else return null;
        }
        else
            return value;

    }

    public static ArrayList<Field> getFieldsByAnnotations(Class clazz, Class annotation)
    {
        ArrayList<Field> fieldList = new ArrayList<Field>();

        Field[] fields = clazz.getDeclaredFields();
        for( Field field : fields)
        {
            if(field.getAnnotation(annotation) != null)
                fieldList.add(field);
        }
        return fieldList;
    }

    public static Object getSqlValue(Object object) throws IllegalAccessException
    {
        if(object.getClass().getAnnotation(Table.class) != null)
        {
            Field[] fields = object.getClass().getDeclaredFields();
            for(Field field : fields)
            {
                if(field.getAnnotation(Id.class) != null)
                {
                    field.setAccessible(true);
                    field.get(object);
                    return field;
                }
            }
        }
        return object;
    }
}
