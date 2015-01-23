package com.lss.xyinclucas.controllers;

import com.lss.xyinclucas.entities.PointOfInterest;
import com.lss.xyinclucas.repositories.POIRepository;
import java.security.InvalidParameterException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.mockito.Mockito;

/**
 *
 * @author lucka3s
 */
public class POIControllerTest {

    POIController controller;
    POIRepository repository;

    public POIControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        repository = Mockito.mock(POIRepository.class);
        controller = new POIController(repository);
    }

    @After
    public void tearDown() {
        repository = null;
        controller = null;
    }

    @org.junit.Test(expected = InvalidParameterException.class)
    public void createWithNullName() throws InvalidParameterException {
        controller.create(null, 0, 0);
    }

    @org.junit.Test(expected = InvalidParameterException.class)
    public void createWithNegativeXValue() throws InvalidParameterException {
        controller.create("not null", -1, 0);
    }

    @org.junit.Test(expected = InvalidParameterException.class)
    public void createWithNegativeYValue() throws InvalidParameterException {
        controller.create("not null", 2, -2);
    }

    @org.junit.Test
    public void createValidObject() throws InvalidParameterException {
        controller.create("Valid name", 1, 2);
        Mockito.verify(repository).save(Mockito.any(PointOfInterest.class));
    }

    @org.junit.Test(expected = InvalidParameterException.class)
    public void nearestPoisWithInvalidMaxDistance() throws InvalidParameterException {
        controller.nearest(1, 1, -2);
    }

    @org.junit.Test(expected = InvalidParameterException.class)
    public void nearestPoisWithInvalidXValue() throws InvalidParameterException {
        controller.nearest(-11, 1, 2);
    }

    @org.junit.Test(expected = InvalidParameterException.class)
    public void nearestPoisWithInvalidYValue() throws InvalidParameterException {
        controller.nearest(1, -2, 2);
    }

    @org.junit.Test
    public void nearestPoisWithValidParams() {
        controller.nearest(1, 2, 3);
        Mockito.verify(repository).nearest(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt());
    }

    @org.junit.Test
    public void listPois() {
        controller.list();
        Mockito.verify(repository).list();
    }
}
