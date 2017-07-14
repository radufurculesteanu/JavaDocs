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
    public void getConnectionTest() throws Exception
    {
        int result = DBManager.checkConnection(DBManager.getConnection());
        Assert.assertEquals("Result should be 1", 1,result);

    }
}
