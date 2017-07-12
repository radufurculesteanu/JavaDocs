package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.appl.domain.Department;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Radu.Furculesteanu on 7/12/2017.
 */
public class EntityUtilsTest {

    @Test
    public void testGetTableNameMethod2() {
        String tableName = EntityUtils.getTableName(Department.class);
        assertEquals("Table name should be DEPARTMENTS!", "DEPARTMENTS", tableName);
    }

    @Test
    public void testGetColumns() {
        ArrayList<ColumnInfo> columnList = EntityUtils.getColumns(Department.class);
        assertEquals("The size of the list should be 3!", 3, columnList.size());
    }

}
