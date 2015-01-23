package com.lss.xyinclucas.controllers;

import com.lss.xyinclucas.entities.PointOfInterest;
import com.lss.xyinclucas.repositories.POIRepository;
import java.security.InvalidParameterException;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/poi")
public class POIController 
{
    private final POIRepository poiRepository;

    public POIController() {
        poiRepository = new POIRepository();
    }
    
    //Constructor used by the tests
    public POIController(POIRepository poiRepository)
    {
        this.poiRepository = poiRepository;
    }
    
    @PUT
    @Path("create/{name}/{x}/{y}")    
    public Boolean create(@PathParam(value = "name") String name, @PathParam(value = "x") int x, @PathParam(value = "y") int y) throws InvalidParameterException
    {
        if(name == null || x < 0 || y < 0)
        {
            throw new InvalidParameterException();
        }
        poiRepository.save(new PointOfInterest(name, x, y));
        return true;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PointOfInterest> list()
    {
        return poiRepository.list();
    }
    
    @GET
    @Path("nearest/{x}/{y}/{maxDistance}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PointOfInterest> nearest(@PathParam(value = "x") int x, @PathParam(value = "y") int y, @PathParam(value = "maxDistance") int maxDistance)
    {
        if(x < 0 || y < 0 || maxDistance < 0)
        {
            throw new InvalidParameterException();
        }
        return poiRepository.nearest(x, y, maxDistance);
    }  
    
}
