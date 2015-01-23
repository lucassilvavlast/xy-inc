package com.lss.xyinclucas.repositories;

import com.lss.xyinclucas.entities.PointOfInterest;
import java.security.InvalidParameterException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author lucka3s
 */
public class POIRepository 
{
    
    private final EntityManagerFactory entityManagerFactory;

    public POIRepository() 
    {
        this.entityManagerFactory = Persistence.createEntityManagerFactory( "factory" );
    }
    
    //Constructor used by the tests
    public POIRepository(EntityManagerFactory entityManagerFactory)
    {
        this.entityManagerFactory = entityManagerFactory;
    }
    
    public void save(final PointOfInterest poi)
    {
        if(poi == null)
        {
            throw new InvalidParameterException();
        }
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist( poi );
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    
    public List<PointOfInterest> list()
    {
        return poiQuery("SELECT p FROM PointOfInterest p");
    }
    
    public List<PointOfInterest> nearest(int x, int y, int maxDistance)
    {
        //could not find the pow function, so multiplied the (p.x + x) and (p.y + y) twice
        return poiQuery("SELECT p FROM PointOfInterest p WHERE SQRT ((p.x + " + x + ")*(p.x + " + x +  ") + (p.y + " + y + ")*(p.y + " + y + ")) <= " + maxDistance);//POW(p.x + " + x +  ",2) + POW(p.y + " + y + ",2)
    }
    
    private List<PointOfInterest> poiQuery(String query)
    {
        
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        final List<PointOfInterest> result = entityManager.createQuery( query, PointOfInterest.class ).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }
}
