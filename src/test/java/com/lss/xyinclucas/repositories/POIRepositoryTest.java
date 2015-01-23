package com.lss.xyinclucas.repositories;

import com.lss.xyinclucas.entities.PointOfInterest;
import java.security.InvalidParameterException;
import javax.persistence.EntityManagerFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author lucka3s
 */
public class POIRepositoryTest {

    private POIRepository repository;
    private EntityManagerFactory mockFactory;
    private EntityManagerTestAdapter mockEntityManager;

    public POIRepositoryTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        mockFactory = Mockito.mock(EntityManagerFactory.class);
        mockEntityManager = Mockito.mock(EntityManagerTestAdapter.class);
        repository = new POIRepository(mockFactory);

        Mockito.when(mockFactory.createEntityManager()).thenReturn(mockEntityManager);
        Mockito.when(mockEntityManager.getTransaction()).thenReturn(new TransactionTestAdapter());
    }

    @After
    public void tearDown() {
    }

    @Test(expected = InvalidParameterException.class)
    public void testSaveWithNullPOI() {
        PointOfInterest poi = null;
        repository.save(poi);
    }

    @Test
    public void testSaveValidPoi() {
        PointOfInterest poi = new PointOfInterest("name", 1, 2);
        repository.save(poi);
        Mockito.verify(mockEntityManager).persist(poi);
    }

    @Test
    public void testListPois() {
        repository.list();
    }

    @Test
    public void testNearestPois() {
        repository.nearest(1, 2, 3);
    }

}
