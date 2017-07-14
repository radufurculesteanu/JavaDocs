package ro.teamnet.zth.appl.dao;

import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.appl.domain.Location;

import java.util.List;
import java.util.Map;

/**
 * Created by Radu.Furculesteanu on 7/14/2017.
 */
public class LocationDao {
    EntityManagerImpl entityManager = new EntityManagerImpl();
    public Location findById(Class<Location> locationClass, Long id)
    {
        return entityManager.findById(locationClass,id);
    }

    public Long getNextIdVal(String columnIdName)
    {
        return entityManager.getNextIdVal("locations", columnIdName);
    }

    public List<Location> findAll(Class<Location> locationClass)
    {
        return entityManager.findAll(locationClass);
    }

    public Location update (Location location)
    {
        return entityManager.update(location);
    }

    public List<Location> findByParams(Class<Location> locationClass, Map<String, Object> params)
    {
        return entityManager.findByParams(locationClass,params);
    }
}
