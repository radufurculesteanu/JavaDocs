package ro.teamnet.zth.appl;

import org.junit.Assert;
import org.junit.Test;
import ro.teamnet.zth.appl.dao.DepartmentDao;
import ro.teamnet.zth.appl.dao.LocationDao;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Location;

import java.util.List;

/**
 * Created by Radu.Furculesteanu on 7/14/2017.
 */
public class LocationDaoTest {
    @Test
    public void findByIdTest()throws Exception
    {
        LocationDao locDao = new LocationDao();
        Location location = locDao.findById(Location.class,new Long(1700));
        Assert.assertEquals("Object should be location", Location.class,location.getClass());
    }

    @Test
    public void getNextIdValTest() throws Exception
    {
        LocationDao locDao = new LocationDao();
        long result = locDao.getNextIdVal("location_id");
        Assert.assertEquals("Result should be 3301", 3301l,result);
    }

    @Test
    public void findAllTest() throws Exception
    {
        LocationDao locDao = new LocationDao();
        List<Location> loctList = locDao.findAll(Location.class);
        Assert.assertEquals("Result should be 24", 24,loctList.size());
    }

    @Test
    public void inserrTest() throws Exception
    {
        LocationDao locDao = new LocationDao();
        Location loc = new Location();
        loc.setId(3300l);
        loc.setCity("Roma");
        loc.setPostalCode("00989");
        loc.setStateProvince("blah blah");
        loc.setStreetAddress("1293 Via Cola di Rie");
        Location locTest = locDao.update(loc);
        Assert.assertEquals("Loc. address should be 1293 Via Cola di Rie", "1293 Via Cola di Rie", locTest.getStreetAddress());
    }
}
